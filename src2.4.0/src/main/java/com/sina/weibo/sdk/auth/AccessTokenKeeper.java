package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.tencent.connect.common.Constants;

public class AccessTokenKeeper {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_UID = "uid";
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    public static void clear(Context context) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
            edit.clear();
            edit.commit();
        }
    }

    public static Oauth2AccessToken readAccessToken(Context context) {
        if (context == null) {
            return null;
        }
        Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 32768);
        oauth2AccessToken.setUid(sharedPreferences.getString("uid", ""));
        oauth2AccessToken.setToken(sharedPreferences.getString("access_token", ""));
        oauth2AccessToken.setRefreshToken(sharedPreferences.getString("refresh_token", ""));
        oauth2AccessToken.setExpiresTime(sharedPreferences.getLong("expires_in", 0));
        return oauth2AccessToken;
    }

    public static void refreshToken(String str, final Context context, final RequestListener requestListener) {
        Oauth2AccessToken readAccessToken = readAccessToken(context);
        if (readAccessToken != null) {
            WeiboParameters weiboParameters = new WeiboParameters(str);
            weiboParameters.put("client_id", str);
            weiboParameters.put("grant_type", "refresh_token");
            weiboParameters.put("refresh_token", readAccessToken.getRefreshToken());
            new AsyncWeiboRunner(context).requestAsync("https://api.weibo.com/oauth2/access_token", weiboParameters, Constants.HTTP_POST, new RequestListener() {
                public void onComplete(String str) {
                    AccessTokenKeeper.writeAccessToken(context, Oauth2AccessToken.parseAccessToken(str));
                    if (requestListener != null) {
                        requestListener.onComplete(str);
                    }
                }

                public void onWeiboException(WeiboException weiboException) {
                    if (requestListener != null) {
                        requestListener.onWeiboException(weiboException);
                    }
                }
            });
        }
    }

    public static void writeAccessToken(Context context, Oauth2AccessToken oauth2AccessToken) {
        if (context != null && oauth2AccessToken != null) {
            Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
            edit.putString("uid", oauth2AccessToken.getUid());
            edit.putString("access_token", oauth2AccessToken.getToken());
            edit.putString("refresh_token", oauth2AccessToken.getRefreshToken());
            edit.putLong("expires_in", oauth2AccessToken.getExpiresTime());
            edit.commit();
        }
    }
}
