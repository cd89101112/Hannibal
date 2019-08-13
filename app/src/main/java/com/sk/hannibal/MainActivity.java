package com.sk.hannibal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;


//import com.hannibal.scalpel.Hannibal;
import com.hannibal.scalpel.task.PickOutTask;
import com.sk.hannibal.base.BaseActivity;

import java.io.IOException;
import java.lang.reflect.Proxy;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity {

    @BindView(R.id.sdsdds)
    AppCompatTextView sss;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        try {
            new OkHttpClient.Builder()
                    .build()
                    .newCall(new Request.Builder()
                            .url("http://www.baidu.com")
                            .build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("hi", "code:" + response.code());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.sdsdds)
    public void onClick(View v) {
        Log.e("e", "ad");


    }

}

