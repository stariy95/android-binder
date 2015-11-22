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
 *     &#64;BindAttribute(id = R.id.button, attr = ViewAttribute.ENABLED)
 *     boolean buttonEnabled;
 *
 *     void setIconAlpha(float alpha) {
 *         iconAlpha = alpha;
 *         notifyFieldChange("iconAlpha");
 *     }
 *
 *     void setButtonEnabled(boolean enabled) {
 *         buttonEnabled = enabled;
 *         notifyFieldChange("buttonEnabled");
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
