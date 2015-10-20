package com.kendamasoft.binder.internal.handler;

import com.kendamasoft.binder.internal.BaseBinding;
import com.kendamasoft.binder.internal.ValueBinding;
import com.kendamasoft.binder.annotation.Bind;

import java.lang.reflect.Field;

public class BindAnnotationHandler extends BaseBindAnnotationHandler<Bind> {

    @Override
    public int[] getViewIds(Bind annotation) {
        return new int[]{annotation.value()};
    }

    @Override
    protected BaseBinding createBinding(Object object, Field field, Bind annotation) {
        return new ValueBinding(object, field);
    }
}
