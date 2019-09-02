package com.amap.api.maps.utils.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.IPoint;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MovingPointOverlay {
    private a STATUS = a.ACTION_UNKNOWN;
    private BasePointOverlay baseOverlay = null;
    private long duration = 10000;
    private LinkedList<Double> eachDistance = new LinkedList();
    AtomicBoolean exitFlag = new AtomicBoolean(false);
    private int index = 0;
    private AMap mAMap;
    private long mAnimationBeginTime = System.currentTimeMillis();
    private Object mLock = new Object();
    private long mStepDuration = 20;
    private ExecutorService mThreadPools;
    private MoveListener moveListener;
    private long pauseMillis;
    private LinkedList<LatLng> points = new LinkedList();
    private double remainDistance = 0.0d;
    private double totalDistance = 0.0d;
    private boolean useDefaultDescriptor = false;

    public interface MoveListener {
        void move(double d);
    }

    private enum a {
        ACTION_UNKNOWN,
        ACTION_START,
        ACTION_RUNNING,
        ACTION_PAUSE,
        ACTION_STOP
    }

    private class b implements ThreadFactory {
        private b() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "MoveSmoothThread");
        }
    }

    private class c implements Runnable {
        private c() {
        }

        /* JADX WARNING: Missing block: B:18:?, code skipped:
            java.lang.Thread.sleep(com.amap.api.maps.utils.overlay.MovingPointOverlay.access$900(r7.a));
     */
        public void run() {
            /*
            r7 = this;
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r1 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x008b }
            r0.mAnimationBeginTime = r1;	 Catch:{ Throwable -> 0x008b }
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_START;	 Catch:{ Throwable -> 0x008b }
            r0.STATUS = r1;	 Catch:{ Throwable -> 0x008b }
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.exitFlag;	 Catch:{ Throwable -> 0x008b }
            r1 = 0;
            r0.set(r1);	 Catch:{ Throwable -> 0x008b }
        L_0x0018:
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.exitFlag;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.get();	 Catch:{ Throwable -> 0x008b }
            if (r0 != 0) goto L_0x0083;
        L_0x0022:
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.index;	 Catch:{ Throwable -> 0x008b }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r1 = r1.points;	 Catch:{ Throwable -> 0x008b }
            r1 = r1.size();	 Catch:{ Throwable -> 0x008b }
            r1 = r1 + -1;
            if (r0 > r1) goto L_0x0083;
        L_0x0036:
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.mLock;	 Catch:{ Throwable -> 0x008b }
            monitor-enter(r0);	 Catch:{ Throwable -> 0x008b }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r1 = r1.exitFlag;	 Catch:{ all -> 0x0080 }
            r1 = r1.get();	 Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0049;
        L_0x0047:
            monitor-exit(r0);	 Catch:{ all -> 0x0080 }
            return;
        L_0x0049:
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r1 = r1.STATUS;	 Catch:{ all -> 0x0080 }
            r2 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_PAUSE;	 Catch:{ all -> 0x0080 }
            if (r1 == r2) goto L_0x0075;
        L_0x0053:
            r1 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0080 }
            r3 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r3 = r3.mAnimationBeginTime;	 Catch:{ all -> 0x0080 }
            r5 = r1 - r3;
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r1 = r1.getCurPosition(r5);	 Catch:{ all -> 0x0080 }
            r2 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r2 = r2.baseOverlay;	 Catch:{ all -> 0x0080 }
            r2.setGeoPoint(r1);	 Catch:{ all -> 0x0080 }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ all -> 0x0080 }
            r2 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_RUNNING;	 Catch:{ all -> 0x0080 }
            r1.STATUS = r2;	 Catch:{ all -> 0x0080 }
        L_0x0075:
            monitor-exit(r0);	 Catch:{ all -> 0x0080 }
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r0 = r0.mStepDuration;	 Catch:{ Throwable -> 0x008b }
            java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x008b }
            goto L_0x0018;
        L_0x0080:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0080 }
            throw r1;	 Catch:{ Throwable -> 0x008b }
        L_0x0083:
            r0 = com.amap.api.maps.utils.overlay.MovingPointOverlay.this;	 Catch:{ Throwable -> 0x008b }
            r1 = com.amap.api.maps.utils.overlay.MovingPointOverlay.a.ACTION_STOP;	 Catch:{ Throwable -> 0x008b }
            r0.STATUS = r1;	 Catch:{ Throwable -> 0x008b }
            goto L_0x008f;
        L_0x008b:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x008f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.utils.overlay.MovingPointOverlay$c.run():void");
        }
    }

    public MovingPointOverlay(AMap aMap, BasePointOverlay basePointOverlay) {
        if (aMap != null && basePointOverlay != null) {
            this.mAMap = aMap;
            this.mThreadPools = new ThreadPoolExecutor(1, 2, 5, TimeUnit.SECONDS, new SynchronousQueue(), new b());
            this.baseOverlay = basePointOverlay;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00cc  */
    private com.autonavi.amap.mapcore.IPoint getCurPosition(long r12) {
        /*
        r11 = this;
        r0 = r11.duration;
        r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1));
        r0 = 0;
        r3 = 0;
        r4 = 1;
        if (r2 <= 0) goto L_0x0049;
    L_0x000a:
        r12 = r11.exitFlag;
        r12.set(r4);
        r12 = new com.autonavi.amap.mapcore.IPoint;
        r12.<init>();
        r13 = r11.points;
        r13 = r13.size();
        r13 = r13 - r4;
        r11.index = r13;
        r13 = r11.points;
        r2 = r11.index;
        r13 = r13.get(r2);
        r13 = (com.amap.api.maps.model.LatLng) r13;
        r2 = r11.index;
        r2 = r2 - r4;
        r11.index = r2;
        r2 = r11.index;
        r2 = java.lang.Math.max(r2, r3);
        r11.index = r2;
        r11.remainDistance = r0;
        r0 = r13.longitude;
        r2 = r13.latitude;
        com.autonavi.amap.mapcore.MapProjection.lonlat2Geo(r0, r2, r12);
        r13 = r11.moveListener;
        if (r13 == 0) goto L_0x0048;
    L_0x0041:
        r13 = r11.moveListener;
        r0 = r11.remainDistance;
        r13.move(r0);
    L_0x0048:
        return r12;
    L_0x0049:
        r12 = (double) r12;
        r5 = r11.totalDistance;
        r12 = r12 * r5;
        r5 = r11.duration;
        r5 = (double) r5;
        r12 = r12 / r5;
        r5 = r11.totalDistance;
        r5 = r5 - r12;
        r11.remainDistance = r5;
        r5 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r7 = r12;
        r12 = r3;
    L_0x005a:
        r13 = r11.eachDistance;
        r13 = r13.size();
        if (r12 >= r13) goto L_0x007d;
    L_0x0062:
        r13 = r11.eachDistance;
        r13 = r13.get(r12);
        r13 = (java.lang.Double) r13;
        r9 = r13.doubleValue();
        r13 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r13 <= 0) goto L_0x0076;
    L_0x0072:
        r7 = r7 - r9;
        r12 = r12 + 1;
        goto L_0x005a;
    L_0x0076:
        r13 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r13 <= 0) goto L_0x007e;
    L_0x007a:
        r7 = r7 / r9;
        r5 = r7;
        goto L_0x007e;
    L_0x007d:
        r12 = r3;
    L_0x007e:
        r13 = r11.index;
        if (r12 == r13) goto L_0x008d;
    L_0x0082:
        r13 = r11.moveListener;
        if (r13 == 0) goto L_0x008d;
    L_0x0086:
        r13 = r11.moveListener;
        r0 = r11.remainDistance;
        r13.move(r0);
    L_0x008d:
        r11.index = r12;
        r13 = r11.points;
        r13 = r13.get(r12);
        r13 = (com.amap.api.maps.model.LatLng) r13;
        r0 = r11.points;
        r12 = r12 + r4;
        r12 = r0.get(r12);
        r12 = (com.amap.api.maps.model.LatLng) r12;
        r0 = new com.autonavi.amap.mapcore.IPoint;
        r0.<init>();
        r1 = r13.longitude;
        r3 = r13.latitude;
        com.autonavi.amap.mapcore.MapProjection.lonlat2Geo(r1, r3, r0);
        r1 = new com.autonavi.amap.mapcore.IPoint;
        r1.<init>();
        r2 = r12.longitude;
        r7 = r12.latitude;
        com.autonavi.amap.mapcore.MapProjection.lonlat2Geo(r2, r7, r1);
        r2 = r1.x;
        r3 = r0.x;
        r2 = r2 - r3;
        r3 = r1.y;
        r4 = r0.y;
        r3 = r3 - r4;
        r12 = com.amap.api.maps.AMapUtils.calculateLineDistance(r13, r12);
        r13 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1));
        if (r12 <= 0) goto L_0x00e7;
    L_0x00cc:
        r12 = r11.getRotate(r0, r1);
        r13 = r11.mAMap;
        if (r13 == 0) goto L_0x00e7;
    L_0x00d4:
        r13 = r11.mAMap;
        r13 = r13.getCameraPosition();
        if (r13 == 0) goto L_0x00e7;
    L_0x00dc:
        r1 = r11.baseOverlay;
        r4 = 1135869952; // 0x43b40000 float:360.0 double:5.611943214E-315;
        r4 = r4 - r12;
        r12 = r13.bearing;
        r4 = r4 + r12;
        r1.setRotateAngle(r4);
    L_0x00e7:
        r12 = new com.autonavi.amap.mapcore.IPoint;
        r13 = r0.x;
        r7 = (double) r13;
        r1 = (double) r2;
        r1 = r1 * r5;
        r7 = r7 + r1;
        r13 = (int) r7;
        r0 = r0.y;
        r0 = (double) r0;
        r2 = (double) r3;
        r2 = r2 * r5;
        r0 = r0 + r2;
        r0 = (int) r0;
        r12.<init>(r13, r0);
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.utils.overlay.MovingPointOverlay.getCurPosition(long):com.autonavi.amap.mapcore.IPoint");
    }

    private float getRotate(IPoint iPoint, IPoint iPoint2) {
        if (iPoint == null || iPoint2 == null) {
            return 0.0f;
        }
        return (float) ((Math.atan2(((double) iPoint2.x) - ((double) iPoint.x), ((double) iPoint.y) - ((double) iPoint2.y)) / 3.141592653589793d) * 180.0d);
    }

    private void reset() {
        try {
            if (this.STATUS == a.ACTION_RUNNING || this.STATUS == a.ACTION_PAUSE) {
                this.exitFlag.set(true);
                this.mThreadPools.awaitTermination(this.mStepDuration + 20, TimeUnit.MILLISECONDS);
                this.baseOverlay.setAnimation(null);
                this.STATUS = a.ACTION_UNKNOWN;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            reset();
            this.mThreadPools.shutdownNow();
            synchronized (this.mLock) {
                this.points.clear();
                this.eachDistance.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getIndex() {
        return this.index;
    }

    public BasePointOverlay getObject() {
        return this.baseOverlay;
    }

    public LatLng getPosition() {
        return this.baseOverlay != null ? this.baseOverlay.getPosition() : null;
    }

    public void removeMarker() {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.remove();
                this.baseOverlay = null;
            }
            this.points.clear();
            this.eachDistance.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetIndex() {
        this.index = 0;
    }

    public void setMoveListener(MoveListener moveListener) {
        this.moveListener = moveListener;
    }

    /* JADX WARNING: Missing block: B:25:0x0087, code skipped:
            return;
     */
    public void setPoints(java.util.List<com.amap.api.maps.model.LatLng> r7) {
        /*
        r6 = this;
        r0 = r6.mLock;
        monitor-enter(r0);
        if (r7 == 0) goto L_0x0086;
    L_0x0005:
        r1 = r7.size();	 Catch:{ Throwable -> 0x0080 }
        r2 = 2;
        if (r1 >= r2) goto L_0x000e;
    L_0x000c:
        goto L_0x0086;
    L_0x000e:
        r6.stopMove();	 Catch:{ Throwable -> 0x0080 }
        r1 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r1.clear();	 Catch:{ Throwable -> 0x0080 }
        r7 = r7.iterator();	 Catch:{ Throwable -> 0x0080 }
    L_0x001a:
        r1 = r7.hasNext();	 Catch:{ Throwable -> 0x0080 }
        if (r1 == 0) goto L_0x002e;
    L_0x0020:
        r1 = r7.next();	 Catch:{ Throwable -> 0x0080 }
        r1 = (com.amap.api.maps.model.LatLng) r1;	 Catch:{ Throwable -> 0x0080 }
        if (r1 == 0) goto L_0x001a;
    L_0x0028:
        r2 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r2.add(r1);	 Catch:{ Throwable -> 0x0080 }
        goto L_0x001a;
    L_0x002e:
        r7 = r6.eachDistance;	 Catch:{ Throwable -> 0x0080 }
        r7.clear();	 Catch:{ Throwable -> 0x0080 }
        r1 = 0;
        r6.totalDistance = r1;	 Catch:{ Throwable -> 0x0080 }
        r7 = 0;
        r1 = r7;
    L_0x0039:
        r2 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r2 = r2.size();	 Catch:{ Throwable -> 0x0080 }
        r2 = r2 + -1;
        if (r1 >= r2) goto L_0x0069;
    L_0x0043:
        r2 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r2 = r2.get(r1);	 Catch:{ Throwable -> 0x0080 }
        r2 = (com.amap.api.maps.model.LatLng) r2;	 Catch:{ Throwable -> 0x0080 }
        r3 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r1 = r1 + 1;
        r3 = r3.get(r1);	 Catch:{ Throwable -> 0x0080 }
        r3 = (com.amap.api.maps.model.LatLng) r3;	 Catch:{ Throwable -> 0x0080 }
        r2 = com.amap.api.maps.AMapUtils.calculateLineDistance(r2, r3);	 Catch:{ Throwable -> 0x0080 }
        r2 = (double) r2;	 Catch:{ Throwable -> 0x0080 }
        r4 = r6.eachDistance;	 Catch:{ Throwable -> 0x0080 }
        r5 = java.lang.Double.valueOf(r2);	 Catch:{ Throwable -> 0x0080 }
        r4.add(r5);	 Catch:{ Throwable -> 0x0080 }
        r4 = r6.totalDistance;	 Catch:{ Throwable -> 0x0080 }
        r4 = r4 + r2;
        r6.totalDistance = r4;	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0039;
    L_0x0069:
        r1 = r6.totalDistance;	 Catch:{ Throwable -> 0x0080 }
        r6.remainDistance = r1;	 Catch:{ Throwable -> 0x0080 }
        r1 = r6.points;	 Catch:{ Throwable -> 0x0080 }
        r7 = r1.get(r7);	 Catch:{ Throwable -> 0x0080 }
        r7 = (com.amap.api.maps.model.LatLng) r7;	 Catch:{ Throwable -> 0x0080 }
        r1 = r6.baseOverlay;	 Catch:{ Throwable -> 0x0080 }
        r1.setPosition(r7);	 Catch:{ Throwable -> 0x0080 }
        r6.reset();	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0084;
    L_0x007e:
        r7 = move-exception;
        goto L_0x0088;
    L_0x0080:
        r7 = move-exception;
        r7.printStackTrace();	 Catch:{ all -> 0x007e }
    L_0x0084:
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        return;
    L_0x0086:
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        return;
    L_0x0088:
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.utils.overlay.MovingPointOverlay.setPoints(java.util.List):void");
    }

    public void setPosition(LatLng latLng) {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.setPosition(latLng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRotate(float f) {
        try {
            if (this.baseOverlay != null && this.mAMap != null) {
                CameraPosition cameraPosition = this.mAMap.getCameraPosition();
                if (cameraPosition != null) {
                    this.baseOverlay.setRotateAngle((360.0f - f) + cameraPosition.bearing);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTotalDuration(int i) {
        this.duration = (long) (i * 1000);
    }

    public void setVisible(boolean z) {
        try {
            if (this.baseOverlay != null) {
                this.baseOverlay.setVisible(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startSmoothMove() {
        if (this.STATUS == a.ACTION_PAUSE) {
            this.STATUS = a.ACTION_RUNNING;
            this.mAnimationBeginTime += System.currentTimeMillis() - this.pauseMillis;
            return;
        }
        if ((this.STATUS == a.ACTION_UNKNOWN || this.STATUS == a.ACTION_STOP) && this.points.size() >= 1) {
            this.index = 0;
            try {
                this.mThreadPools.execute(new c());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void stopMove() {
        if (this.STATUS == a.ACTION_RUNNING) {
            this.STATUS = a.ACTION_PAUSE;
            this.pauseMillis = System.currentTimeMillis();
        }
    }
}
