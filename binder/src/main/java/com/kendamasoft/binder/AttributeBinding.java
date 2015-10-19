package com.kendamasoft.binder;

import com.kendamasoft.binder.updaters.ViewUpdaterFactory;

import java.lang.reflect.Field;

/**
 * Created by stariy on 19.10.15.
 */
public class AttributeBinding extends BaseBinding {

    ViewAttribute attribute;

    public AttributeBinding(Object object, Field field, ViewAttribute attribute) {
        super(object, field);
        this.attribute = attribute;
        updater = ViewUpdaterFactory.getUpdaterForAttribute(attribute);
    }


}
