package com.agna.realmvp.realmvpsample.ui.base.delegate.activity.result;


import android.content.Intent;

import com.agna.realmvp.realmvpsample.ui.base.delegate.ScreenEventDelegate;

public interface ActivityResultDelegate extends ScreenEventDelegate {

    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
