package com.kendamasoft.binder.annotation;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate on touch event handler.
 *
 * <code>
 * &#64;OnTouch(R.id.view)
 * public void onMyViewTouch(MotionEvent event) {
 *     //...
 * }
 * </code>
 *
 * or
 *
 * <code>
 * &#64;OnTouch({R.id.view1, R.id.view2, R.id.view3})
 * public void onAnyViewTouch(View view, MotionEvent event) {
 *     //...
 * }
 * </code>
 *
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OnTouch {
//    @IdRes
    int[] value();
}
