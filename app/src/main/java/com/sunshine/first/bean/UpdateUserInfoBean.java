package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class UpdateUserInfoBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 修改成功
     * data : []
     */

    private boolean success;
    private String error_code;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
