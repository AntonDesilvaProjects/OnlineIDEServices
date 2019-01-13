package com.onlineide.dto.response;

public class BaseServiceResponse<T> {

    private boolean success;
    private Error error;
    private T response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean successful) {
        success = successful;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
