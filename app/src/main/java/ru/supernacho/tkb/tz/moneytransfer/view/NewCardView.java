package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.databinding.NewCardBinding;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.CardViewModel;

public class NewCardView extends RecyclerView.ViewHolder implements IviewHolder{
    private boolean isBeneficiary;
    private NewCardBinding binding;
    @BindView(R.id.et_card_number)
    public TextInputEditText etCardNumber;
    @BindView(R.id.et_exp_date)
    public TextInputEditText etExpDate;
    @BindView(R.id.et_cvv)
    public TextInputEditText etCVV;

    public NewCardView(NewCardBinding binding, boolean isBeneficiary) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;
        this.isBeneficiary = isBeneficiary;
        setViewsVisibility(isBeneficiary);
    }

    private void setViewsVisibility(boolean isBeneficiary) {
        if (isBeneficiary) {
            etCVV.setVisibility(View.GONE);
            etExpDate.setVisibility(View.GONE);
        }
    }

    public void bind(CardViewModel cardViewModel){
        binding.setCardModel(cardViewModel);
        binding.executePendingBindings();
    }
}
