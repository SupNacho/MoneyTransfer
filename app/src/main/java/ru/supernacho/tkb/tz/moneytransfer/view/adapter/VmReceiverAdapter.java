package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;

public class VmReceiverAdapter extends VmAbstractAdapter {

    public VmReceiverAdapter(MainViewModel mainViewModel) {
        super(mainViewModel.receiverCards.getValue(), mainViewModel,true);
    }
}
