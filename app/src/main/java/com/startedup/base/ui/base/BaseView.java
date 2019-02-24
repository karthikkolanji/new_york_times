package com.startedup.base.ui.base;

public interface BaseView {
    void showLoading( String loadingMessage);

    void hideLoading();

    void showNetworkError( String errorMessage);

    void showUnknownError( String errorMessage);

    void hideError();

    void onSuccess(Object object);
}
