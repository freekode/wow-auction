package org.freekode.wowauction.controllers.data;

public class ResponseData<T> {
    private boolean success;
    private T data;
    private String error;


    public ResponseData() {
    }

    public ResponseData(T data) {
        this.data = data;
        this.success = true;
    }

    public ResponseData(Exception e) {
        this.error = e.getMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
