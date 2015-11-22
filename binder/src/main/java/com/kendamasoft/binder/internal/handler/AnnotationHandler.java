package com.kendamasoft.binder.internal.handler;

import android.view.View;

import com.kendamasoft.binder.Observable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.List;

public interface AnnotationHandler<T extends Annotation> {

    String TAG = AnnotationHandler.class.getSimpleName();

    int[] getViewIds(T annotation);

    void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable);

}
