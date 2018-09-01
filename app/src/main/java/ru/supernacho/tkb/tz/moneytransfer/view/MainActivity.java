package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.BeneficiaryRvAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.SenderRvAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.alert.Alert;
import ru.supernacho.tkb.tz.moneytransfer.view.filters.DecimalDigitInputFilter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private SenderRvAdapter senderRvAdapter;
    private BeneficiaryRvAdapter beneficiaryRvAdapter;

    @BindView(R.id.rv_sender_cards)
    RecyclerView rvSenderList;
    @BindView(R.id.rv_beneficiary_cards)
    RecyclerView rvBeneficiaryList;
    @BindView(R.id.et_amount_input)
    TextInputEditText etAmount;

    @Inject
    App app;

    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);
        initRecyclerViews();
        initViews();
        presenter.getCardsData();
    }

    private void initViews() {
        etAmount.setFilters(new InputFilter[]{new DecimalDigitInputFilter(2)});
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("++", "sequence" + s.toString() + " start" + start + " count: " + count + " before " + before);
                if (s.length() > 1 && s.charAt(0) == '0' && s.charAt(1) != '.') {
                    etAmount.setText("");
                    etAmount.append(s.subSequence(1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initRecyclerViews() {
        SnapHelper senderSnapHelper = new PagerSnapHelper();
        SnapHelper beneSnapHelper = new PagerSnapHelper();
        LinearLayoutManager senderLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager beneficiaryLayoutManager = new LinearLayoutManager(this);
        senderLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        beneficiaryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        senderRvAdapter = new SenderRvAdapter(presenter);
        beneficiaryRvAdapter = new BeneficiaryRvAdapter(presenter);
        rvSenderList.setLayoutManager(senderLayoutManager);
        rvBeneficiaryList.setLayoutManager(beneficiaryLayoutManager);
        rvSenderList.setAdapter(senderRvAdapter);
        rvBeneficiaryList.setAdapter(beneficiaryRvAdapter);
        senderSnapHelper.attachToRecyclerView(rvSenderList);
        beneSnapHelper.attachToRecyclerView(rvBeneficiaryList);
    }

    @ProvidePresenter
    MainActivityPresenter providePresenter() {
        MainActivityPresenter presenter = new MainActivityPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @OnClick(R.id.btn_transfer)
    public void onClickTransfer() {
        presenter.startTransfer(etAmount.getText().toString());
        etAmount.setText(null);
    }

    @Override
    public void requestSignIn() {
        Alert.showUserChooser(this, presenter);
    }

    @Override
    public void updateAdapters() {
        senderRvAdapter.notifyDataSetChanged();
        beneficiaryRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewResult(Card sender, Card beneficiary, String amount) {
        Alert.showResult(this, sender, beneficiary, amount);
        updateAdapters();
    }

    @Override
    public void viewError(int errorType) {
        String msg;
        switch (errorType) {
            case ErrorTypes.CARD_DATA_ERROR:
                msg = "Wrong data in cards field";
                break;
            default:
                msg = "No such error code";
                break;
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
