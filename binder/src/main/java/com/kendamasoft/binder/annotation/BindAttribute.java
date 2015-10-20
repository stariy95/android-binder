package com.kendamasoft.binder.annotation;

//import android.support.annotation.IdRes;

import com.kendamasoft.binder.ViewAttribute;

/**
 *
 * One way binding from field to view attribute.
 *
 * <code>
 * class MyModel extends Observable {
 *     &#64;BindAttribute(id = R.id.icon, attr = ViewAttribute.ALPHA)
 *     float iconAlpha;
 *
 *     void setIconAlpha(float alpha) {
 *         iconAlpha = alpha;
 *         notifyFieldChange("iconAlpha");
 *     }
 * }
 * </code>
 *
 * @see com.kendamasoft.binder.ViewAttribute
 */
public @interface BindAttribute {
    //@IdRes

    /**
     * @return view Id
     */
    int id();

    /**
     * @return view attribute
     */
    ViewAttribute attr();
}
