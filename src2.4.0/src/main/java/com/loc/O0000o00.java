package com.loc;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log */
public final class O0000o00 {
    public static final String O000000o = "/a/";
    static final String O00000Oo = "b";
    static final String O00000o = "d";
    static final String O00000o0 = "c";
    static final String O00000oO = "i";
    public static final String O00000oo = "g";
    public static final String O0000O0o = "h";
    public static final String O0000OOo = "e";
    public static final String O0000Oo = "j";
    public static final String O0000Oo0 = "f";

    public static String O000000o(Context context) {
        return O00000o0(context, O00000oO);
    }

    public static String O000000o(Context context, String str) {
        return context.getSharedPreferences("AMSKLG_CFG", 0).getString(str, "");
    }

    @TargetApi(9)
    public static void O000000o(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    static boolean O000000o(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String trim : str.split("\n")) {
                if (O00000Oo(strArr, trim.trim())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public static void O00000Oo(final Context context) {
        try {
            ExecutorService O00000o = O0000o.O00000o();
            if (O00000o != null && !O00000o.isShutdown()) {
                O00000o.submit(new Runnable() {
                    public final void run() {
                        try {
                            O00Oo0OO.O000000o(context);
                            O00oOooO.O00000Oo(context);
                            O00oOooO.O00000o(context);
                            O00oOooO.O00000o0(context);
                            O00OoOO0.O000000o(context);
                            O00Oo.O000000o(context);
                        } catch (RejectedExecutionException unused) {
                        } catch (Throwable th) {
                            O0000o.O00000Oo(th, "Lg", "proL");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            O0000o.O00000Oo(th, "Lg", "proL");
        }
    }

    public static void O00000Oo(Context context, String str) {
        Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.remove(str);
        edit.apply();
    }

    static boolean O00000Oo(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            String str2 = str;
            for (String str3 : strArr) {
                str2 = str2.trim();
                if (str2.startsWith("at ")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str3);
                    stringBuilder.append(".");
                    if (str2.contains(stringBuilder.toString()) && str2.endsWith(")") && !str2.contains("uncaughtException")) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public static String O00000o0(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(O000000o);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    static List<OO0Oo0> O00000o0(Context context) {
        Throwable th;
        List<OO0Oo0> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    List<OO0Oo0> O000000o = new O000OO(context, false).O000000o();
                    try {
                        return O000000o;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        list = O000000o;
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    throw th;
                }
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
            return list;
        }
    }
}
