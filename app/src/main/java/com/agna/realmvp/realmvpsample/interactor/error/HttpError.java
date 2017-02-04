package com.agna.realmvp.realmvpsample.interactor.error;


/**
 * получен ответ не 2xx
 */
public class HttpError extends NetworkException {
    private int code;
    //private BaseResponse errorResponse;

    public HttpError(Throwable cause, String message, int code/*, BaseResponse errorResponse*/) {
        super(message, cause);
        this.code = code;
        //this.errorResponse = errorResponse;
    }

    public int getCode() {
        return code;
    }

   /* public BaseResponse getErrorResponse() {
        return errorResponse;
    }*/
}
