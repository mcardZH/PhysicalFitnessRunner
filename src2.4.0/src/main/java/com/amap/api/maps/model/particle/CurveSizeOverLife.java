package com.amap.api.maps.model.particle;

import com.autonavi.amap.mapcore.AMapNativeParticleSystem;

public class CurveSizeOverLife extends SizeOverLife {
    private float x;
    private float y;
    private float z;

    public CurveSizeOverLife(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.type = 0;
        createNativeInstace();
    }

    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateCurveSizeOverLife(this.x, this.y, this.z);
        } catch (Throwable unused) {
        }
    }

    public float getSizeX(float f) {
        return 0.0f;
    }

    public float getSizeY(float f) {
        return 0.0f;
    }

    public float getSizeZ(float f) {
        return 0.0f;
    }
}
