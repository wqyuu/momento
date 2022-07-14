package com.wqy.momento.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class MomentResponse<T> implements Serializable {

    private static final long serialVersionUID = -1220656299702215742L;
    private String code;
    private String message;
    private T data;

    public MomentResponse() {

    }

    public static <T> MomentResponse<T> ok(T data) {
        return new MomentResponse<T>("200", "success", data);
    }

    public static <T> MomentResponse<T> ok(String code, String message, T data) {
        return new MomentResponse<T>(code, message, data);
    }

    public static <T> MomentResponse<T> fail(T data) {
        return new MomentResponse<T>("500", "fail request", data);
    }

    public static <T> MomentResponse<T> fail(String code, String message, T data) {
        return new MomentResponse<T>(code, message, data);
    }

    public void setResultCode(ResultCode code) {
        this.code = String.valueOf(code.code());
        this.message = code.message();
    }

    private MomentResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //成功 自定义成功返回状态 加上数据
    public static MomentResponse<Object> success(ResultCode ResultCode, Object data) {
        MomentResponse<Object> MomentoResponse = new MomentResponse<Object>();
        MomentoResponse.setResultCode(ResultCode);
        MomentoResponse.setData(data);
        return MomentoResponse;
    }

    // 单返回失败的状态码
    public static MomentResponse<ResultCode> failure(ResultCode ResultCode) {
        MomentResponse<ResultCode> MomentoResponse = new MomentResponse<>();
        MomentoResponse.setResultCode(ResultCode);
        return MomentoResponse;
    }

    // 返回失败的状态码 及 数据
    public static MomentResponse<Object> failure(ResultCode ResultCode, Object data) {
        MomentResponse<Object> MomentoResponse = new MomentResponse<>();
        MomentoResponse.setResultCode(ResultCode);
        MomentoResponse.setData(data);
        return MomentoResponse;
    }
}