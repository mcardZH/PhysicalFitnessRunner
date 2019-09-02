package com.autonavi.ae.gmap.gloverlay;

import android.util.SparseArray;
import com.amap.api.mapcore.util.ca;
import java.util.ArrayList;
import java.util.List;

public class GLOverlayBundle<E extends BaseMapOverlay<?, ?>> {
    private int mEngineID;
    ca mGLMapView = null;
    private long mNativeInstance = 0;
    private final List<E> mOverlayList = new ArrayList();
    private SparseArray<GLOverlayTexture> mTextureCaches = new SparseArray();

    public static class GLAmapFocusHits {
        public long mHitedIndex = 0;
        public long mHitedTimes = 1000;
        public long mOverlayHashCode = 0;
    }

    public GLOverlayBundle(int i, ca caVar) {
        this.mEngineID = i;
        this.mGLMapView = caVar;
        this.mNativeInstance = caVar.O000000o().getGlOverlayMgrPtr(this.mEngineID);
    }

    private int getOverlyExType(E e) {
        return 0;
    }

    public static void intClr2PVRClr(float[] fArr, int i) {
        fArr[2] = ((float) (i & 255)) / 255.0f;
        fArr[1] = ((float) ((i >> 8) & 255)) / 255.0f;
        fArr[0] = ((float) ((i >> 16) & 255)) / 255.0f;
        fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
    }

    private static native void nativeAddGLOverlay(long j, long j2, long j3);

    private static native void nativeAddGLOverlayEx(long j, long j2, long j3, int i);

    private static native void nativeClearAllGLOverlay(long j, boolean z);

    private static native boolean nativeOnSingleTapLineOverlay(long j, int i, int i2, long[] jArr);

    private static native boolean nativeOnSingleTapPointOverlay(long j, int i, int i2, long[] jArr);

    private static native void nativeRemoveGLOverlay(long j, long j2);

    private static native void nativeRemoveGLOverlayEx(long j, long j2, int i);

    private static native void nativeSortAllGLOverlay(long j);

    public void addOverlay(E e) {
        if (e != null) {
            nativeAddGLOverlay(this.mNativeInstance, e.getGLOverlay().getNativeInstatnce(), (long) e.getGLOverlay().getCode());
            e.getGLOverlay().mIsInBundle = true;
            synchronized (this.mOverlayList) {
                this.mOverlayList.add(e);
            }
        }
    }

    public boolean addOverlayTextureItem(int i, int i2, float f, float f2, int i3, int i4) {
        GLOverlayTexture gLOverlayTexture = new GLOverlayTexture(i, i2, f, f2, i3, i4);
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.put(i, gLOverlayTexture);
        }
        return true;
    }

    public boolean addOverlayTextureItem(int i, int i2, int i3, int i4) {
        GLOverlayTexture gLOverlayTexture = new GLOverlayTexture(i, i2, i3, i4);
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.put(i, gLOverlayTexture);
        }
        return true;
    }

    public long checkSingleTapOnLine(int i, int i2) {
        long[] jArr = new long[3];
        return nativeOnSingleTapLineOverlay(this.mNativeInstance, i, i2, jArr) ? jArr[0] : -1;
    }

    public long checkSingleTapOnPoint(int i, int i2) {
        long[] jArr = new long[3];
        return nativeOnSingleTapPointOverlay(this.mNativeInstance, i, i2, jArr) ? jArr[0] : -1;
    }

    public void clearFocus() {
        if (this.mOverlayList != null) {
            synchronized (this.mOverlayList) {
                for (int i = 0; i < this.mOverlayList.size(); i++) {
                    BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                    if (baseMapOverlay != null) {
                        baseMapOverlay.clearFocus();
                    }
                }
            }
        }
    }

    public void clearOverlayTexture() {
        synchronized (this.mTextureCaches) {
            this.mTextureCaches.clear();
        }
    }

    public boolean cotainsOverlay(E e) {
        if (e == null) {
            return false;
        }
        boolean contains;
        synchronized (this.mOverlayList) {
            contains = this.mOverlayList.contains(e);
        }
        return contains;
    }

    public E getOverlay(int i) {
        synchronized (this.mOverlayList) {
            if (i >= 0) {
                try {
                    if (i <= this.mOverlayList.size() - 1) {
                        BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                        return baseMapOverlay;
                    }
                } finally {
                }
            }
            return null;
        }
    }

    public int getOverlayCount() {
        int size;
        synchronized (this.mOverlayList) {
            size = this.mOverlayList.size();
        }
        return size;
    }

    public GLOverlayTexture getOverlayTextureItem(int i) {
        GLOverlayTexture gLOverlayTexture;
        synchronized (this.mTextureCaches) {
            gLOverlayTexture = (GLOverlayTexture) this.mTextureCaches.get(i);
        }
        return gLOverlayTexture;
    }

    public boolean onSingleTap(int i, int i2, int i3, int i4) {
        boolean onSingleTapPoints = (i4 & 1) == 1 ? onSingleTapPoints(i, i2, i3) : false;
        if (onSingleTapPoints) {
            return true;
        }
        if ((i4 & 2) == 2) {
            onSingleTapPoints = onSingleTapLines(i, i2, i3);
        }
        return onSingleTapPoints;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onSingleTapLines(int i, int i2, int i3) {
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onSingleTapPoints(int i, int i2, int i3) {
        return false;
    }

    public void reculateRouteBoard(ca caVar) {
    }

    public void removeAll(boolean z) {
        nativeClearAllGLOverlay(this.mNativeInstance, z);
        synchronized (this.mOverlayList) {
            for (int i = 0; i < this.mOverlayList.size(); i++) {
                BaseMapOverlay baseMapOverlay = (BaseMapOverlay) this.mOverlayList.get(i);
                if (baseMapOverlay != null) {
                    baseMapOverlay.getGLOverlay().mIsInBundle = false;
                    baseMapOverlay.getGLOverlay().releaseInstance();
                }
            }
            this.mOverlayList.clear();
        }
    }

    public void removeOverlay(E e) {
        if (e != null) {
            nativeRemoveGLOverlay(this.mNativeInstance, e.getGLOverlay().getNativeInstatnce());
            e.getGLOverlay().mIsInBundle = false;
            synchronized (this.mOverlayList) {
                this.mOverlayList.remove(e);
            }
        }
    }

    public void sortOverlay() {
        nativeSortAllGLOverlay(this.mNativeInstance);
    }
}
