package com.quaindinteractive.androidpractice.presenter;

import android.content.Context;
import android.widget.EditText;

public interface ViewContract {
    void showProgress();
    void hideProgress();
    void finishView();
    EditText getUsername();
    EditText getPassword();
    Context getContext();
}
