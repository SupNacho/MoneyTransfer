package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.databinding.CachedCardBinding;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.CardViewModel;

public class CachedCardView extends RecyclerView.ViewHolder implements IviewHolder{
    private boolean isBeneficiary;
    private CachedCardBinding binding;
    @BindView(R.id.et_cached_cvv)
    public TextInputEditText etCVV;

    public CachedCardView(CachedCardBinding binding, boolean isBeneficiary) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;
        this.isBeneficiary = isBeneficiary;
        setViewsVisibility(isBeneficiary);
    }

    private void setViewsVisibility(boolean isBeneficiary) {
        if (isBeneficiary) {
            etCVV.setVisibility(View.GONE);
        }
    }

    public void bind(CardViewModel cardViewModel){
        binding.setCardModel(cardViewModel);
        binding.executePendingBindings();
    }

}
