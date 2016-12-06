package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 完成服务端对客户信息的共享
 * 注意事项：
 * 1.客户端（别的应用程序）想调用服务端方法接口的时候，aidl包名和文件名必须一致（直接从服务端把aidl文件复制到服务端即可）
 * 2.AndroidManifest.xml注册时需要注意Service里面这两个方法一定要添加上
 * android:exported="true"
 * android:process=":remote"
 */

public class IRemoteService extends Service {
    String TAG = getClass().getSimpleName();
    // 服务端对于必须用ArrayList接收Person实体类
    private ArrayList<Person> persons;

    /**
     * 当客户端绑定到该服务的时候 会执行
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return iBinder;
    }

    private IBinder iBinder = new IImoocAidl.Stub() {

        @Override
        public List<Person> add(Person peron) throws RemoteException {
            persons.add(peron);
            return persons;
        }
    };
}
