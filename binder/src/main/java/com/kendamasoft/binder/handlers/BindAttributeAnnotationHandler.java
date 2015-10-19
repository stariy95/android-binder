package com.kendamasoft.binder.handlers;

import com.kendamasoft.binder.AttributeBinding;
import com.kendamasoft.binder.BaseBinding;
import com.kendamasoft.binder.annotations.BindAttribute;

import java.lang.reflect.Field;

/**
 * Created by stariy on 19.10.15.
 */
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
