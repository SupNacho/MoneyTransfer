package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.R;

public class NewCardView extends RecyclerView.ViewHolder{
    @BindView(R.id.et_card_number)
    TextInputEditText etCardNumber;
    @BindView(R.id.et_exp_date)
    TextInputEditText etExpDate;
    @BindView(R.id.et_cvv)
    TextInputEditText etCVV;

    public NewCardView(View itemView, boolean isBeneficiary) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (isBeneficiary) {
            etCVV.setVisibility(View.GONE);
            etExpDate.setVisibility(View.GONE);
        }
    }
}
