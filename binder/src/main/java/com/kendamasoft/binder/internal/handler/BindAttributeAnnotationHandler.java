package com.kendamasoft.binder.internal.handler;

import com.kendamasoft.binder.internal.AttributeBinding;
import com.kendamasoft.binder.internal.BaseBinding;
import com.kendamasoft.binder.annotation.BindAttribute;

import java.lang.reflect.Field;

public class BindAttributeAnnotationHandler extends BaseBindAnnotationHandler<BindAttribute> {

    @Override
    public int[] getViewIds(BindAttribute annotation) {
        return new int[]{annotation.id()};
    }

    @Override
    protected BaseBinding createBinding(Object object, Field field, BindAttribute annotation) {
        return new AttributeBinding(object, field, annotation.attr());
    }
}
