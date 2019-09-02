package com.baidu.location.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.location.a.l;
import com.baidu.location.e.e;
import com.baidu.location.f;
import com.baidu.location.g.j;

public class b {
    private static b a;
    private boolean b = false;
    private Handler c = null;
    private AlarmManager d = null;
    private a e = null;
    private PendingIntent f = null;
    private long g = 0;

    private class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(b bVar, c cVar) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            if (b.this.b && intent.getAction().equals("com.baidu.location.autonotifyloc_7.6.2") && b.this.c != null) {
                b.this.f = null;
                b.this.c.sendEmptyMessage(1);
            }
        }
    }

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    private void f() {
        if (System.currentTimeMillis() - this.g >= 1000) {
            if (this.f != null) {
                this.d.cancel(this.f);
                this.f = null;
            }
            if (this.f == null) {
                this.f = PendingIntent.getBroadcast(f.getServiceContext(), 0, new Intent("com.baidu.location.autonotifyloc_7.6.2"), AMapEngineUtils.HALF_MAX_P20_WIDTH);
                this.d.set(0, System.currentTimeMillis() + ((long) j.W), this.f);
            }
            Message message = new Message();
            message.what = 22;
            if (System.currentTimeMillis() - this.g >= ((long) j.X)) {
                this.g = System.currentTimeMillis();
                if (!e.a().j()) {
                    l.c().b(message);
                }
            }
        }
    }

    private void g() {
        if (this.b) {
            try {
                if (this.f != null) {
                    this.d.cancel(this.f);
                    this.f = null;
                }
                f.getServiceContext().unregisterReceiver(this.e);
            } catch (Exception unused) {
            }
            this.d = null;
            this.e = null;
            this.c = null;
            this.b = false;
        }
    }

    public void b() {
        if (!this.b && j.W >= 10000) {
            if (this.c == null) {
                this.c = new c(this);
            }
            this.d = (AlarmManager) f.getServiceContext().getSystemService("alarm");
            this.e = new a(this, null);
            f.getServiceContext().registerReceiver(this.e, new IntentFilter("com.baidu.location.autonotifyloc_7.6.2"), "android.permission.ACCESS_FINE_LOCATION", null);
            this.f = PendingIntent.getBroadcast(f.getServiceContext(), 0, new Intent("com.baidu.location.autonotifyloc_7.6.2"), AMapEngineUtils.HALF_MAX_P20_WIDTH);
            this.d.set(0, System.currentTimeMillis() + ((long) j.W), this.f);
            this.b = true;
            this.g = System.currentTimeMillis();
        }
    }

    public void c() {
        if (this.b && this.c != null) {
            this.c.sendEmptyMessage(2);
        }
    }

    public void d() {
        if (this.b && this.c != null) {
            this.c.sendEmptyMessage(1);
        }
    }

    public void e() {
        if (this.b && this.c != null) {
            this.c.sendEmptyMessage(1);
        }
    }
}
