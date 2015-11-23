package com.kendamasoft.binder.internal.handler;

import android.view.View;

import com.kendamasoft.binder.Observable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.List;

public abstract class AnnotationHandler<T extends Annotation> {

    String TAG = AnnotationHandler.class.getSimpleName();

    public abstract int[] getViewIds(T annotation);

    public abstract void handle(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable);

    public void cleanUp(Object object, AccessibleObject member, View topView, List<View> viewList, T annotation, Observable observable) {
    }
}
