package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.tinkoff.decoro.Mask;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class CachedCardView extends RecyclerView.ViewHolder {
    private static final String CARD_NUMBER_MASK = "____ **** **** ____";
    private Card card;
    @BindView(R.id.tv_card_number)
    public TextView tvCardNumber;
    @BindView(R.id.tv_bank_label)
    public TextView tvBanklabel;
    @BindView(R.id.et_cached_cvv)
    public TextInputEditText etCacheCVV;

    public CachedCardView(View itemView, boolean isBeneficiary) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        initViews(isBeneficiary);
    }

    private void initViews(boolean isBeneficiary) {
        if (isBeneficiary) etCacheCVV.setVisibility(View.GONE);
        FormatWatcher formatWatcher =
                new MaskFormatWatcher(MaskImpl.createTerminated(new UnderscoreDigitSlotsParser()
                        .parseSlots(CARD_NUMBER_MASK)));
        formatWatcher.installOn(tvCardNumber);
        etCacheCVV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 2 && InputChecker.checkCVC(s.toString())) {
                    card.setCvv(s.toString());
                    etCacheCVV.clearFocus();
                } else {
                    etCacheCVV.setError("CVV to short");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
