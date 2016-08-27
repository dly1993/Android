package com.aigestudio.interfaces.apis;

import com.aigestudio.interfaces.models.LoginInfo;
import com.aigestudio.interfaces.models.UpgradeInfo;

/**
 * 网络请求方法常规实现
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
class ApiNor implements IApi {
    @Override
    public void login(String user, String password, CallBack<LoginInfo> c) {

    }

    @Override
    public void upgrade(String code, CallBack<UpgradeInfo> c) {

    }
}