package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.SenderRvAdapter;

public class MainActivity extends MvpAppCompatActivity implements MainView{

    private SenderRvAdapter senderRvAdapter;
    private SenderRvAdapter beneficiaryRvAdapter;

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
        presenter.getCardsData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        senderRvAdapter = new SenderRvAdapter();
        rvSenderList.setLayoutManager(layoutManager);
        rvBeneficiaryList.setLayoutManager(layoutManager);
    }

    @ProvidePresenter
    MainActivityPresenter providePresenter(){
        MainActivityPresenter presenter = new MainActivityPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @OnClick(R.id.btn_transfer)
    public void onClickTransfer(){
        presenter.startTransfer(0,0);
    }

    @Override
    public void updateAdapters() {

    }


}
