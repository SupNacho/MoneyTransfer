package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.presenter.IMainPresenter;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView{

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

    }

    @ProvidePresenter
    IMainPresenter providePresenter(){
        IMainPresenter presenter = new MainActivityPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }
}
