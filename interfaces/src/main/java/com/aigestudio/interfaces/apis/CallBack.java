package com.aigestudio.interfaces.apis;

import com.aigestudio.interfaces.models.Response;

/**
 * 网络请求完成后的回调接口
 *
 * @param <T> 请求完成后返回的数据实体类
 * @author AigeStudio{@link http://aigestudio.com/?p=100}
 * @since 2016-08-15
 */
public interface CallBack<T> {
    /**
     * 请求完成后回调该方法
     *
     * @param isSuccess 请求是否成功
     * @param response  如果请求成功该参数不为空否则为空
     * @param error     如果请求成功该参数为空否则会将失败原因传入
     */
    void onFinish(boolean isSuccess, Response<T> response, String error);
}