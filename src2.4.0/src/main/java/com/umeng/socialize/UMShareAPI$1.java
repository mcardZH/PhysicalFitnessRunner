package com.umeng.socialize;

import android.app.Activity;
import android.content.Context;
import defpackage.bp;
import defpackage.by;
import defpackage.cc.O000000o;

class UMShareAPI$1 extends O000000o<Void> {
    final /* synthetic */ Activity O000000o;
    final /* synthetic */ by O00000Oo;
    final /* synthetic */ UMShareAPI O00000o;
    final /* synthetic */ UMAuthListener O00000o0;

    UMShareAPI$1(UMShareAPI uMShareAPI, Context context, Activity activity, by byVar, UMAuthListener uMAuthListener) {
        this.O00000o = uMShareAPI;
        this.O000000o = activity;
        this.O00000Oo = byVar;
        this.O00000o0 = uMAuthListener;
        super(context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: O000000o */
    public Void O00000Oo() {
        if (UMShareAPI.a(this.O00000o) == null) {
            UMShareAPI.a(this.O00000o, new bp(this.O000000o));
        }
        UMShareAPI.a(this.O00000o).O00000o0(this.O000000o, this.O00000Oo, this.O00000o0);
        return null;
    }
}
