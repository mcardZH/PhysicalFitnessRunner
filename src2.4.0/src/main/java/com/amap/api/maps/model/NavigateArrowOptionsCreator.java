package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class NavigateArrowOptionsCreator implements Creator<NavigateArrowOptions> {
    public NavigateArrowOptions createFromParcel(Parcel parcel) {
        NavigateArrowOptions navigateArrowOptions = new NavigateArrowOptions();
        ArrayList arrayList = new ArrayList();
        parcel.readTypedList(arrayList, LatLng.CREATOR);
        float readFloat = parcel.readFloat();
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        float readFloat2 = parcel.readFloat();
        boolean z = false;
        boolean z2 = parcel.readByte() == (byte) 1;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        }
        navigateArrowOptions.addAll(arrayList);
        navigateArrowOptions.width(readFloat);
        navigateArrowOptions.topColor(readInt);
        navigateArrowOptions.sideColor(readInt2);
        navigateArrowOptions.zIndex(readFloat2);
        navigateArrowOptions.visible(z2);
        navigateArrowOptions.a = parcel.readString();
        navigateArrowOptions.set3DModel(z);
        return navigateArrowOptions;
    }

    public NavigateArrowOptions[] newArray(int i) {
        return new NavigateArrowOptions[i];
    }
}
