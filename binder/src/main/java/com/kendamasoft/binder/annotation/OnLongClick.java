package com.kendamasoft.binder.annotation;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate on long click event handler.
 *
 * <code>
 * &#64;OnLongClick(R.id.view)
 * public boolean onMyViewLongClick() {
 *     //...
 * }
 * </code>
 *
 * or
 *
 * <code>
 * &#64;OnLongClick({R.id.view1, R.id.view2, R.id.view3})
 * public boolean onAnyViewLongClick(View view) {
 *     //...
 * }
 * </code>
 *
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OnLongClick {
//    @IdRes
    int[] value();
}
