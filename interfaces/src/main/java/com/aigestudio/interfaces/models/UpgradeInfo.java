package com.aigestudio.interfaces.models;

/**
 * 应用更新信息数据实体类
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public class UpgradeInfo {
    public String mVersionName;// 版本名
    public String mDescription;// 版本描述
    public String mDownloadUrl;// 安装包下载地址

    public int mVersionCode;// 版本号

    public boolean isForce;// 是否强制更新
}