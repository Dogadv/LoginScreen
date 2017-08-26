package com.quaindinteractive.androidpractice.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quaindinteractive.androidpractice.R;
import com.quaindinteractive.androidpractice.model.PreferencesHelper;
import com.quaindinteractive.androidpractice.presenter.MainContract;
import com.quaindinteractive.androidpractice.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract{

    @BindView(R.id.logout)
    Button logout;

    @BindView(R.id.helloText)
    TextView helloText;

    private MainPresenter presenter;
    private PreferencesHelper preferencesHelper;

    public static void createMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogoutPressed();
            }
        });

        preferencesHelper = new PreferencesHelper(this);
        presenter = new MainPresenter(preferencesHelper);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
        preferencesHelper = null;
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void setHelloText(String message) {
        this.helloText.setText(message);
    }

    @Override
    public Context getContext() {
        return  this;
    }
}
