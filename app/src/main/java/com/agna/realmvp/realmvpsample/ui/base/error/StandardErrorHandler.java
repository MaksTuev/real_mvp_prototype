package com.agna.realmvp.realmvpsample.ui.base.error;


import android.text.TextUtils;

import com.agna.ferro.mvp.component.scope.PerScreen;
import com.agna.realmvp.realmvpsample.interactor.error.ApiServiceException;
import com.agna.realmvp.realmvpsample.interactor.error.ConversionException;
import com.agna.realmvp.realmvpsample.interactor.error.HttpError;
import com.agna.realmvp.realmvpsample.interactor.error.NoInternetException;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Стандартный обработчик ошибок, возникающих при работе с сервером
 */
@PerScreen
public class StandardErrorHandler extends NetworkErrorHandler {

    @Inject
    public StandardErrorHandler() {
    }


    @Override
    protected void handleApiServiceError(ApiServiceException err) {

    }

    @Override
    protected void handleHttpError(HttpError e) {

    }

    @Override
    protected void handleNoInternetError(NoInternetException e) {

    }

    @Override
    protected void handleConversionError(ConversionException e) {

    }

    @Override
    protected void handleOtherError(Throwable e) {

    }

   /* private final MessagePresenter messagePresenter;

    @Inject
    public StandardErrorHandler(MessagePresenter messagePresenter) {
        this.messagePresenter = messagePresenter;
    }


    @Override
    protected void handleApiServiceError(ApiServiceException err) {
        if (!TextUtils.isEmpty(err.getUserMessage())) {
            messagePresenter.show(err.getUserMessage());
        } else {
            messagePresenter.show(R.string.service_error_message);
        }
    }

    @Override
    protected void handleHttpError(HttpError e) {
        if (e.getCode() >= HttpCodes.CODE_500) {
            messagePresenter.show(R.string.server_error_message);
        } else {
            BaseResponse errorResponse = e.getErrorResponse();
            if (errorResponse != null && !TextUtils.isEmpty(errorResponse.getUserMessage())) {
                messagePresenter.show(errorResponse.getUserMessage());
            } else {
                messagePresenter.show(String.format(Locale.getDefault(), "%d %s", e.getCode(), e.getMessage()));
            }
        }
    }

    @Override
    protected void handleNoInternetError(NoInternetException e) {
        messagePresenter.show(R.string.no_internet_connection_error_message);
    }

    @Override
    protected void handleConversionError(ConversionException e) {
        messagePresenter.show(R.string.bad_response_error_message);
    }

    @Override
    protected void handleOtherError(Throwable e) {
        messagePresenter.show(R.string.unexpected_error_error_message);
        Logger.e(e, "Unexpected error");
    }*/
}
