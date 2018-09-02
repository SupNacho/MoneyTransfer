package ru.supernacho.tkb.tz.moneytransfer.view;

import android.content.res.Resources;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.AppConstants;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class NewCardView extends RecyclerView.ViewHolder{
    private Card card;
    private Resources res;
    private MainActivityPresenter presenter;
    private boolean isBeneficiary;
    @BindView(R.id.et_card_number)
    public TextInputEditText etCardNumber;
    @BindView(R.id.et_exp_date)
    public TextInputEditText etExpDate;
    @BindView(R.id.et_cvv)
    public TextInputEditText etCVV;

    public NewCardView(View itemView, MainActivityPresenter presenter, boolean isBeneficiary) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
        this.isBeneficiary = isBeneficiary;
        this.res = itemView.getResources();
        setViewsVisibility(isBeneficiary);
        initViews();
    }

    private void initViews() {
        Slot[] expireDateSlots = new UnderscoreDigitSlotsParser().parseSlots(AppConstants.EXP_DATE_MASK);
        FormatWatcher expDateFormatWatcher = new MaskFormatWatcher(MaskImpl.createTerminated(expireDateSlots));
        expDateFormatWatcher.installOn(etExpDate);
        etCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (s.length() > 11) {
                        if (InputChecker.checkCard(s.toString())) {
                            card.setNumber(s.toString());
                        } else {
                            etCardNumber.setError(res.getString(R.string.card_number_error));
                        }
                    } else {
                        etCardNumber.setError(res.getString(R.string.card_number_to_short));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCardNumber.clearFocus();
        etExpDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (InputChecker.checkDate(s.toString())) {
                        card.setExpire(s.toString());
                    } else {
                        etExpDate.setError(res.getString(R.string.card_exp_date_error));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etExpDate.clearFocus();
        etCVV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (InputChecker.checkCVC(s.toString())) {
                        card.setCvv(s.toString());
                    } else {
                        etCVV.setError(res.getString(R.string.cvv_to_short));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCVV.clearFocus();
        itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                setPos();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                checkPos();
            }
        });
    }

    private void setViewsVisibility(boolean isBeneficiary) {
        if (isBeneficiary) {
            etCVV.setVisibility(View.GONE);
            etExpDate.setVisibility(View.GONE);
        }
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    private void setPos(){
        if (isBeneficiary) presenter.setBeneficiaryPos(getAdapterPosition());
        else presenter.setSenderPos(getAdapterPosition());
    }
    private void checkPos(){
        if (isBeneficiary) presenter.checkBeneficiaryPos(getAdapterPosition());
        else presenter.checkSenderPos(getAdapterPosition());
    }
}
