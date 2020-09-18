package com.softterm.tools.deviceinfotest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkState {
    public final boolean hasNetworkConnection(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        return (networkInfo != null && networkInfo.isAvailable());
    }

    public final boolean hasWifiConnection(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    public final boolean hasGPRSConnection(Context context) {
        //获取活动连接管理器
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfo != null && networkInfo.isAvailable());
    }


    public static int getNetWorkConnectionType(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ethernetNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        final NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiNetworkInfo != null && wifiNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_WIFI;
        } else if (mobileNetworkInfo != null && mobileNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_MOBILE;
        } else if (ethernetNetworkInfo != null && ethernetNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_ETHERNET;
        } else {
            return -1;
        }
    }
}
