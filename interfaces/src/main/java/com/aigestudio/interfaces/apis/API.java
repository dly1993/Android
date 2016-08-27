package com.aigestudio.interfaces.apis;

import com.aigestudio.interfaces.models.LoginInfo;
import com.aigestudio.interfaces.models.UpgradeInfo;

/**
 * 网络请求方法单例封装类
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public final class Api implements IApi {
    private volatile static Api sInstance;

    private IApi mApi;

    private Api() {
        mApi = new ApiOkHttp();
    }

    public static Api getInstance() {
        if (null == sInstance)
            synchronized (Api.class) {
                sInstance = new Api();
            }
        return sInstance;
    }

    @Override
    public void login(String user, String password, CallBack<LoginInfo> c) {
        mApi.login(user, password, c);
    }

    @Override
    public void upgrade(String code, CallBack<UpgradeInfo> c) {
        mApi.upgrade(code, c);
    }
}