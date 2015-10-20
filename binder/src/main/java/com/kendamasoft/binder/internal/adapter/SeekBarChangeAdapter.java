package com.kendamasoft.binder.internal.adapter;

import android.widget.SeekBar;

class SeekBarChangeAdapter extends ViewChangeAdapter<Integer, SeekBar> {

    public SeekBarChangeAdapter(final SeekBar view, final ViewChangeListener changeListener) {
        super(view, changeListener);
        view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeListener.onViewChange(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


}
