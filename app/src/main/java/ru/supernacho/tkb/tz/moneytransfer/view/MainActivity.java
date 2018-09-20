package ru.supernacho.tkb.tz.moneytransfer.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.databinding.ActivityMainBinding;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.VmAbstractAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.VmReceiverAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.VmSenderAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.alert.Alert;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.CardViewModel;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.factory.ViewModelFactory;

public class MainActivity extends AppCompatActivity/*MvpAppCompatActivity implements MainView*/ {

    private VmSenderAdapter senderRvAdapter;
    private VmReceiverAdapter beneficiaryRvAdapter;

    @BindView(R.id.rv_sender_cards)
    RecyclerView rvSenderList;
    @BindView(R.id.rv_beneficiary_cards)
    RecyclerView rvBeneficiaryList;
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        binding.setMainModel(mainViewModel);
        mainViewModel.senderCards.observe(this, cardViewModels -> senderRvAdapter.notifyDataSetChanged());
        mainViewModel.receiverCards.observe(this, cardViewModels -> beneficiaryRvAdapter.notifyDataSetChanged());
        ButterKnife.bind(this);
        initRecyclerViews();
    }

    private void initRecyclerViews() {
        SnapHelper senderSnapHelper = new PagerSnapHelper();
        SnapHelper beneficiarySnapHelper = new PagerSnapHelper();
        LinearLayoutManager senderLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager beneficiaryLayoutManager = new LinearLayoutManager(this);
        senderLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        beneficiaryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        senderRvAdapter = new VmSenderAdapter(mainViewModel);
        beneficiaryRvAdapter = new VmReceiverAdapter(mainViewModel);
        rvSenderList.setLayoutManager(senderLayoutManager);
        rvBeneficiaryList.setLayoutManager(beneficiaryLayoutManager);
        rvSenderList.setAdapter(senderRvAdapter);
        rvBeneficiaryList.setAdapter(beneficiaryRvAdapter);
        senderSnapHelper.attachToRecyclerView(rvSenderList);
        beneficiarySnapHelper.attachToRecyclerView(rvBeneficiaryList);
    }
}
