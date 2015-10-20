package com.kendamasoft.binder.annotation;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate on focus change event handler.
 *
 * <code>
 * &#64;OnFocusChange(R.id.view)
 * public void onMyViewFocusChange(boolean focus) {
 *     //...
 * }
 * </code>
 *
 * or
 *
 * <code>
 * &#64;OnFocusChange({R.id.view1, R.id.view2, R.id.view3})
 * public void onAnyViewLongClick(View view, boolean focus) {
 *     //...
 * }
 * </code>
 *
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OnFocusChange {
//    @IdRes
    int[] value();
}
