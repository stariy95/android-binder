package com.kendamasoft.binder.internal;

import com.kendamasoft.binder.ViewAttribute;
import com.kendamasoft.binder.internal.updater.ViewUpdaterFactory;

import java.lang.reflect.Field;

public class AttributeBinding extends BaseBinding {

    ViewAttribute attribute;

    public AttributeBinding(Object object, Field field, ViewAttribute attribute) {
        super(object, field);
        this.attribute = attribute;
        updater = ViewUpdaterFactory.getUpdaterForAttribute(attribute);
    }


}
