package com.aigestudio.interfaces;

import com.aigestudio.interfaces.apis.Api;
import com.aigestudio.interfaces.apis.CallBack;
import com.aigestudio.interfaces.models.LoginInfo;
import com.aigestudio.interfaces.models.Response;

/**
 * 客户类
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public class Main {
    public static void main(String[] args) {
        Api.getInstance().login("AigeStudio", "123456789", new CallBack<LoginInfo>() {
            @Override
            public void onFinish(boolean isSuccess, Response<LoginInfo> response, String error) {
                if (isSuccess) {
                    int code = response.mResponseCode;
                    if (code == 200) {
                        String user = response.mData.mUser;
                        String password = response.mData.mPassword;
                        String name = response.mData.mName;
                        String avatarUrl = response.mData.mAvatarUrl;
                        String sex = response.mData.mSex;
                        String birthday = response.mData.mBirthday;

                        long registerTimeStamp = response.mData.mRegisterTimeStamp;
                        long lastLoginTimeStamp = response.mData.mLastLoginTimeStamp;

                        int age = response.mData.mAge;
                    } else {
                        System.out.print(response.mResponseMsg);
                    }
                } else {
                    System.out.print(error);
                }
            }
        });
    }
}