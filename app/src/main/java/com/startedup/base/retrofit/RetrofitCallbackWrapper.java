package com.startedup.base.retrofit;

import com.startedup.base.R;
import com.startedup.base.ui.base.BaseResponse;
import com.startedup.base.ui.base.BaseView;
import com.startedup.base.utils.ResourceFinder;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

// source : https://medium.com/mindorks/rxjava2-and-retrofit2-error-handling-on-a-single-place-8daf720d42d6

public abstract class RetrofitCallbackWrapper<T extends BaseResponse> extends DisposableObserver<T> {

    //BaseView is just a reference of a View in MVP
    private WeakReference<BaseView> weakReference;

    BaseView view;

    public RetrofitCallbackWrapper(BaseView view) {
        this.weakReference = new WeakReference<>(view);
        view = weakReference.get();
        view.hideError();
        view.showLoading( "Please wait");
    }

    //protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        //You can return StatusCodes of different cases from your API and handle it here. I usually include these cases on BaseResponse and iherit it from every Response
        view = weakReference.get();
        view.hideError();
        view.hideLoading();
        view.onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        view = weakReference.get();
        view.hideLoading();
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            //view.showNetworkError(ResourceFinder.Companion.getString(R.string.network_error));
        } else {
            //view.showUnknownError(ResourceFinder.Companion.getString(R.string.unknown_error));
        }
    }

    @Override
    public void onComplete() {

    }


    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}