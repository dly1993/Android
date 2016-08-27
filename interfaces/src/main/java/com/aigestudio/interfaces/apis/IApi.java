package com.aigestudio.interfaces.apis;

import com.aigestudio.interfaces.models.LoginInfo;
import com.aigestudio.interfaces.models.UpgradeInfo;

/**
 * 网络请求方法接口
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
interface IApi {
    /**
     * 用户登录
     *
     * @param user     用户账户
     * @param password 用户密码
     * @param c        数据请求完成后回调接口
     */
    void login(String user, String password, CallBack<LoginInfo> c);

    /**
     * 应用更新
     *
     * @param code 版本号
     * @param c    数据请求完成后回调接口
     */
    void upgrade(String code, CallBack<UpgradeInfo> c);
}