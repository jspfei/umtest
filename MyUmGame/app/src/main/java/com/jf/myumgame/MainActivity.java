package com.jf.myumgame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.OnlineConfigLog;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        OnlineConfigAgent.getInstance().setDebugMode(true);
        OnlineConfigAgent.getInstance().updateOnlineConfig(mContext);
        getUmengOnlineKey();
    }
    private void getUmengOnlineKey(){
        String value = OnlineConfigAgent.getInstance().getConfigParams(mContext,"apk_version");
        Log.i("HACK-TAG"," value   =         " +value);


        OnlineConfigAgent.getInstance().setOnlineConfigListener(new com.umeng.onlineconfig.UmengOnlineConfigureListener() {
            @Override
            public void onDataReceived(JSONObject jsonObject) {
                OnlineConfigLog.d("HACK-TAG", "json="+jsonObject);
            }
        }) ;

    }


    public void onResume() {
        super.onResume();

    }
    public void onPause() {
        super.onPause();

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        OnlineConfigAgent.getInstance().removeOnlineConfigListener();
    }
}
