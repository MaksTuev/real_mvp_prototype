package com.agna.realmvp.realmvpsample.interactor.error;

/**
 * ошибки сервиса (приходят в теле ответа)
 */
public class ApiServiceException extends NetworkException {
    private final int errorCode;
    private final String userMessage;
    private final String developerMessage;

    public ApiServiceException(int errorCode, String userMessage, String developerMessage) {

        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    @Override
    public String toString() {
        return "ApiServiceException{" +
                "errorCode=" + errorCode +
                ", userMessage='" + userMessage + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                '}';
    }
}
