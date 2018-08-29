package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.R;

public class CachedCardView extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_card_number)
    public TextView tvCardNumber;
    @BindView(R.id.tv_bank_label)
    public TextView tvBanklabel;
    @BindView(R.id.et_cached_cvv)
    public TextInputEditText etCacheCVV;

    public CachedCardView(View itemView, boolean isBeneficiary) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (isBeneficiary) etCacheCVV.setVisibility(View.GONE);
    }
}
