package com.kendamasoft.binder.handlers;

import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.List;

/**
 * Created by stariy on 16.10.15.
 */
public interface AnnotationHandler<T extends Annotation> {

    String TAG = AnnotationHandler.class.getSimpleName();

    int[] getViewIds(T annotation);

    void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation);

}
