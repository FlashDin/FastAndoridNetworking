package com.flashdin.fastandroidnetworking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flashdin.testnetworkinglibs.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnNoLibs,mBtnRetrofit,mBtnVolley,mBtnFast,mBtnION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnNoLibs = (Button) findViewById(R.id.btnNoLibs);
        mBtnRetrofit = (Button) findViewById(R.id.btnRetrofit);
        mBtnVolley = (Button) findViewById(R.id.btnVolley);
        mBtnFast = (Button) findViewById(R.id.btnFast);
        mBtnION = (Button) findViewById(R.id.btnION);
        mBtnNoLibs.setOnClickListener(this);
        mBtnRetrofit.setOnClickListener(this);
        mBtnVolley.setOnClickListener(this);
        mBtnFast.setOnClickListener(this);
        mBtnION.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id) {
            case R.id.btnFast:
                intent = new Intent(this.getApplicationContext(), FormTest.class);
                intent.putExtra("act", "fast");
                startActivity(intent);
                break;
        }
    }
}
