package com.ximalaya.ting.android.opensdk.datatrasfer;

import com.google.gson.reflect.TypeToken;
import com.ximalaya.ting.android.opensdk.httputil.BaseResponse;
import com.ximalaya.ting.android.opensdk.model.album.AlbumList;

class CommonRequest$74 implements CommonRequest$IRequestCallBack<AlbumList> {
    CommonRequest$74() {
    }

    public AlbumList success(String str) throws Exception {
        return (AlbumList) BaseResponse.getResponseBodyStringToObject(new TypeToken<AlbumList>() {
        }.getType(), str);
    }
}
