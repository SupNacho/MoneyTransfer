package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;

public class VmSenderAdapter extends VmAbstractAdapter {

    public VmSenderAdapter(MainViewModel mainViewModel) {
        super(mainViewModel.senderCards.getValue(), false);
    }
}
