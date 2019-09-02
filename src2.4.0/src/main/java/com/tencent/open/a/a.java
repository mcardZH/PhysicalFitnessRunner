package com.tencent.open.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* compiled from: ProGuard */
public class a extends i implements Callback {
    private b a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile g e;
    private volatile g f;
    private volatile g g;
    private volatile g h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    public a(int i, boolean z, h hVar, b bVar) {
        super(i, z, hVar);
        this.i = false;
        a(bVar);
        this.e = new g();
        this.f = new g();
        this.g = this.e;
        this.h = this.f;
        this.d = new char[bVar.d()];
        g();
        this.j = new HandlerThread(bVar.c(), bVar.f());
        if (this.j != null) {
            this.j.start();
        }
        if (this.j.isAlive() && this.j.getLooper() != null) {
            this.k = new Handler(this.j.getLooper(), this);
        }
    }

    public a(b bVar) {
        this(c.b, true, h.a, bVar);
    }

    private void f() {
        if (Thread.currentThread() == this.j && !this.i) {
            this.i = true;
            i();
            try {
                this.h.a(g(), this.d);
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.h.b();
            }
            this.h.b();
            this.i = false;
        }
    }

    private Writer g() {
        File a = c().a();
        if (!(a == null || a.equals(this.c)) || (this.b == null && a != null)) {
            this.c = a;
            h();
            try {
                this.b = new FileWriter(this.c, true);
            } catch (IOException unused) {
                return null;
            }
        }
        return this.b;
    }

    private void h() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void i() {
        synchronized (this) {
            if (this.g == this.e) {
                this.g = this.f;
                this.h = this.e;
            } else {
                this.g = this.e;
                this.h = this.f;
            }
        }
    }

    public void a() {
        if (this.k.hasMessages(1024)) {
            this.k.removeMessages(1024);
        }
        this.k.sendEmptyMessage(1024);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        a(e().a(i, thread, j, str, str2, th));
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        this.g.a(str);
        if (this.g.a() >= c().d()) {
            a();
        }
    }

    public void b() {
        h();
        this.j.quit();
    }

    public b c() {
        return this.a;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1024) {
            f();
        }
        return true;
    }
}
