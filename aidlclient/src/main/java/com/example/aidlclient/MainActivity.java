package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aidlserver.IImoocAidl;
import com.example.aidlserver.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    private EditText et_num1;
    private EditText et_num2;
    private EditText et_res;
    private Button btn_add;
    IImoocAidl iImoocAidl;

    private ServiceConnection conn = new ServiceConnection() {
        //绑定上服务的时候，执行该方法
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 拿到了远程的服务
            iImoocAidl = IImoocAidl.Stub.asInterface(iBinder);
        }

        // 断开服务的时候调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // 回收资源，避免内存泄露
            iImoocAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // 软件已启动就绑定服务
        bindService();
    }

    private void initView() {
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        et_res = (EditText) findViewById(R.id.et_res);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new BtnClickListener());
    }

    private class BtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

//            int num1 = Integer.parseInt(et_num1.getText().toString());
//            int num2 = Integer.parseInt(et_num2.getText().toString());
//            try {
//                // 调用远程的服务
//                int res = iImoocAidl.add(num1, num2);
//                et_res.setText(res+"");
//            } catch (RemoteException e) {
//                e.printStackTrace();
//                et_res.setText("Error");
//            }
            try {
                List<Person> persons = iImoocAidl.add(new Person("zhangsan", 21));
                Log.d(TAG, "onClick: Persons=" + persons.toString());// 此处toString方法已经在Person类中重写了

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**绑定到服务端的操作（因为我们打算计算前就绑定服务）所以需要在onCreate下绑定*/
    private void bindService() {
        // 获取到服务端
        Intent intent = new Intent();
        // Android 5.0以后，不允许隐式方式启动服务
        // 新版本 必须 显示Intent启动绑定服务
        intent.setComponent(new ComponentName("com.example.aidlserver", "com.example.aidlserver.IRemoteService"));
        // 一绑定，就自动启动服务
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
