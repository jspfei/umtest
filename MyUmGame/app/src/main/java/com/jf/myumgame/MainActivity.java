package com.jf.myumgame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.OnlineConfigLog;

import org.json.JSONObject;

import java.util.HashMap;


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


        UMGameAgent.setDebugMode(true);//设置输出运行时日志
        UMGameAgent.init( this );
        Button btn_click_num = (Button) findViewById(R.id.button_click_number);
        btn_click_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HACK-TAG","click_num");
                HashMap<String,String> map= new HashMap<String,String>();
                map.put("app_id","com.ss.jj");
                MobclickAgent.onEvent(mContext,"id_click_number",map);
            }
        });

        Button btn_down_num = (Button) findViewById(R.id.button_down_number);
        btn_down_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HACK-TAG","down_num");
                HashMap<String,String> map= new HashMap<String,String>();
                map.put("app_id","com.ss.jj");
                MobclickAgent.onEvent(mContext,"id_down_number",map);
            }
        });

        Button btn_installed_num = (Button) findViewById(R.id.button_installed_number);
        btn_installed_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HACK-TAG","installed_num");
                int duration = 10;
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("type","apk");
                map.put("pn","net.tt.jjss");
                map.put("__ct__", String.valueOf(duration));
                MobclickAgent.onEvent(mContext,"id_installed_number",map);
            }
        });


       // id_installed_number
    }
    public static void onEvent(Context context, String id, HashMap<String,String> m, int value){
        m.put("__ct__", String.valueOf(value));
        MobclickAgent.onEvent(context, id, m);
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
        UMGameAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        UMGameAgent.onPause(this);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        OnlineConfigAgent.getInstance().removeOnlineConfigListener();
    }
}
