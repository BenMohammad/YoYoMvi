package com.benmohammad.yoyo.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fm,
                                             @NonNull Fragment fragment,
                                             int frameId){
        checkNotNull(fm);
        checkNotNull(fragment);
        FragmentTransaction trans = fm.beginTransaction();
        trans.add(frameId, fragment);
        trans.commit();
    }
}
