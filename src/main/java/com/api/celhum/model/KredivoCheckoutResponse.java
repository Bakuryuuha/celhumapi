package com.api.celhum.model;

public class KredivoCheckoutResponse {
    String status;
    String message;
    String redirect_url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public static class Error{
        String message;
        String kind;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }
    }

    Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
