package com.vendaodonto.vendasodontoprev.sistemas;

import android.app.Application;

import utils.CustomWebView;

/**
 * Created by sleke on 25/02/2018.
 */

public class OdontoPrevApp  extends Application {

    private static CustomWebView customWebView;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static CustomWebView getCustomWebView() {
        return customWebView;
    }

    public static void setCustomWebView(CustomWebView customWebView) {
        OdontoPrevApp.customWebView = customWebView;
    }

}
