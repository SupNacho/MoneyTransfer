package ru.supernacho.tkb.tz.moneytransfer.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
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
import ru.supernacho.tkb.tz.moneytransfer.AppConstants;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.databinding.ActivityMainBinding;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.BeneficiaryRvAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.adapter.SenderRvAdapter;
import ru.supernacho.tkb.tz.moneytransfer.view.alert.Alert;
import ru.supernacho.tkb.tz.moneytransfer.view.filters.DecimalDigitInputFilter;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity/*MvpAppCompatActivity implements MainView*/ {
//
//    private static final String AMOUNT_KEY = "amount";
//    private SenderRvAdapter senderRvAdapter;
//    private BeneficiaryRvAdapter beneficiaryRvAdapter;
//
//    @BindView(R.id.rv_sender_cards)
//    RecyclerView rvSenderList;
//    @BindView(R.id.rv_beneficiary_cards)
//    RecyclerView rvBeneficiaryList;
//    @BindView(R.id.et_amount_input)
//    TextInputEditText etAmount;
//    @BindView(R.id.cl_main_activity)
//    ConstraintLayout clMainActivity;
//
//    @Inject
//    App app;
//
//    @InjectPresenter
//    MainActivityPresenter presenter;
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMainModel(mainViewModel);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        App.getInstance().getAppComponent().inject(this);
//        initRecyclerViews();
//        initViews();
//        initData(savedInstanceState);
    }
//
//    private void initData(Bundle savedInstanceState) {
//        if (savedInstanceState == null) presenter.getCardsData();
//        else etAmount.setText(savedInstanceState.getString(AMOUNT_KEY));
//    }
//
//    private void initViews() {
//        clMainActivity.requestFocus();
//        etAmount.setFilters(new InputFilter[]{new DecimalDigitInputFilter(AppConstants.DIGITS_AFTER_ZERO)});
//        etAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 1 && s.charAt(0) == '0' && s.charAt(1) != '.') {
//                    etAmount.setText("");
//                    etAmount.append(s.subSequence(1, s.length()));
//                }
//                if (s.length() > 0) {
//                    if (!InputChecker.checkAmount(s.toString()))
//                        etAmount.setError(getResources().getString(R.string.amount_error));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    private void initRecyclerViews() {
//        SnapHelper senderSnapHelper = new PagerSnapHelper();
//        SnapHelper beneficiarySnapHelper = new PagerSnapHelper();
//        LinearLayoutManager senderLayoutManager = new LinearLayoutManager(this);
//        LinearLayoutManager beneficiaryLayoutManager = new LinearLayoutManager(this);
//        senderLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        beneficiaryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        senderRvAdapter = new SenderRvAdapter(presenter);
//        beneficiaryRvAdapter = new BeneficiaryRvAdapter(presenter);
//        rvSenderList.setLayoutManager(senderLayoutManager);
//        rvBeneficiaryList.setLayoutManager(beneficiaryLayoutManager);
//        rvSenderList.setAdapter(senderRvAdapter);
//        rvBeneficiaryList.setAdapter(beneficiaryRvAdapter);
//        senderSnapHelper.attachToRecyclerView(rvSenderList);
//        beneficiarySnapHelper.attachToRecyclerView(rvBeneficiaryList);
//    }
//
//    @ProvidePresenter
//    MainActivityPresenter providePresenter() {
//        MainActivityPresenter presenter = new MainActivityPresenter(AndroidSchedulers.mainThread());
//        App.getInstance().getAppComponent().inject(presenter);
//        return presenter;
//    }
//
//    @OnClick(R.id.btn_transfer)
//    public void onClickTransfer() {
//        presenter.startTransfer(etAmount.getText().toString());
//        hideSoftKeyboard();
//    }
//
//    @Override
//    public void requestSignIn() {
//        Alert.showUserChooser(this, presenter);
//    }
//
//    @Override
//    public void updateAdapters() {
//        senderRvAdapter.notifyDataSetChanged();
//        beneficiaryRvAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void viewResult(Card sender, Card beneficiary, String amount) {
//        Alert.showResult(this, sender, beneficiary, amount);
//        updateAdapters();
//        etAmount.setText(null);
//        etAmount.clearFocus();
//    }
//
//    @Override
//    public void viewError(int errorType) {
//        String msg;
//        switch (errorType) {
//            case ErrorTypes.CARD_DATA_ERROR:
//                msg = getResources().getString(R.string.transaction_input_error);
//                break;
//            default:
//                msg = getResources().getString(R.string.error_code_error);
//                break;
//        }
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putString(AMOUNT_KEY, etAmount.getText().toString());
//        super.onSaveInstanceState(outState);
//    }
//
//    private void hideSoftKeyboard() {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        if (inputMethodManager != null && getCurrentFocus() != null)
//            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//    }
}
