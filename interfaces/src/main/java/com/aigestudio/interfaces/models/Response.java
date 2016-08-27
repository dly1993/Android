package com.aigestudio.interfaces.models;

/**
 * 网络请求数据响应包装类
 *
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public final class Response<T> {
    public T mData;// 数据实体

    public String mResponseMsg;// 响应消息文本 在mResponseCode不等于200的情况下该字段才会有数据

    public long mResponseTimeStamp;// 响应请求的时间戳

    public int mResponseCode;// 具体的相应码 200表示请求并响应成功 该值由服务器规定
}