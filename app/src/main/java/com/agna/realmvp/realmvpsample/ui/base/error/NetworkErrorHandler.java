package com.agna.realmvp.realmvpsample.ui.base.error;

import com.agna.realmvp.realmvpsample.interactor.error.ApiServiceException;
import com.agna.realmvp.realmvpsample.interactor.error.ConversionException;
import com.agna.realmvp.realmvpsample.interactor.error.HttpError;
import com.agna.realmvp.realmvpsample.interactor.error.NetworkException;
import com.agna.realmvp.realmvpsample.interactor.error.NoInternetException;
import com.agna.realmvp.realmvpsample.ui.common.error.*;

import java.util.List;

import rx.exceptions.CompositeException;

/**
 * Базовый класс для обработки ошибок, возникающий при работе с Observable из слоя Interactor
 */
public abstract class NetworkErrorHandler implements ErrorHandler{

    @Override
    public void handleError(Throwable err) {
        //Logger.i(err, "NetworkErrorHandler handle error");
        if (err instanceof CompositeException) {
            handleCompositeException((CompositeException) err);
        } else if (err instanceof ConversionException) {
            handleConversionError((ConversionException) err);
        } else if (err instanceof HttpError) {
            handleHttpError((HttpError) err);
        } else if (err instanceof NoInternetException) {
            handleNoInternetError((NoInternetException) err);
        } else if (err instanceof ApiServiceException) {
            handleApiServiceError((ApiServiceException) err);
        } else {
            handleOtherError(err);
        }
    }

    /**
     * @param err - CompositeException может возникать при комбинировании Observable
     */
    private void handleCompositeException(CompositeException err) {
        List<Throwable> exceptions = err.getExceptions();
        NetworkException networkException = null;
        Throwable otherException = null;
        for (Throwable e : exceptions) {
            if (e instanceof NetworkException) {
                if (networkException == null) {
                    networkException = (NetworkException) e;
                }
            } else if (otherException == null) {
                otherException = e;
            }
        }
        if (networkException != null) {
            handleError(networkException);
        }
        if (otherException != null) {
            handleOtherError(otherException);
        }
    }

    protected abstract void handleApiServiceError(ApiServiceException err);

    protected abstract void handleHttpError(HttpError e);

    protected abstract void handleNoInternetError(NoInternetException e);

    protected abstract void handleConversionError(ConversionException e);

    protected abstract void handleOtherError(Throwable e);
}
