package com.aigestudio.interfaces.models;

/**
 * 登录信息数据实体类
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public class LoginInfo {
    public String mUser;// 用户名
    public String mPassword;// 用户密码
    public String mName;// 用户昵称
    public String mAvatarUrl;// 用户头像地址
    public String mSex;// 用户性别
    public String mBirthday;// 用户生日

    public long mRegisterTimeStamp;// 用户注册时的时间戳
    public long mLastLoginTimeStamp;// 用户上一次登陆的时间戳

    public int mAge;// 用户年龄
}