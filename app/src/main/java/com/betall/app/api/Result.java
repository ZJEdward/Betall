package com.betall.app.api;

/**
 * Created by fly on 2018/1/27.
 */

public final class Result<T> {
    public int code;
    public String msg;
    public T data;
}
