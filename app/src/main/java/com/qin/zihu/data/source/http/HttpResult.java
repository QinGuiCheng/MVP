package com.qin.zihu.data.source.http;

/**
 * Created by Qin on 2017/2/24.
 * 定义服务器返回字段
 */

public class HttpResult<T> {

    public int status;
    public String msg;
    public String code;

    public T data;
}
