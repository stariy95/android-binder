package com.kendamasoft.binder.handlers;

import com.kendamasoft.binder.BaseBinding;
import com.kendamasoft.binder.ValueBinding;
import com.kendamasoft.binder.annotations.Bind;

import java.lang.reflect.Field;

/**
 * Created by stariy on 17.10.15.
 */
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
