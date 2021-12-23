package com.zhuonan.utils;

/**
 * @Author zhuonan
 * @Date 2021/9/15
 * @Description
 */
public class ResponseResult {
    /**
     * 调用是否成功
     */
    private Boolean success;

    /**
     * 返回的提示信息
     */
    private String errmsg;

    /**
     * 内容
     */
    private Object content;

    public static ResponseResult buildSuccess(Object content) {
        ResponseResult response = new ResponseResult();
        response.setContent(content);
        response.setSuccess(true);
        return response;
    }

    public static ResponseResult buildFailure(String errmsg) {
        ResponseResult response = new ResponseResult();
        response.setSuccess(false);
        response.setErrmsg(errmsg);
        return response;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
