package com.kendamasoft.binder.annotations;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark method as onClick handler for view (or array of views) defined by id.
 * Handler method must be public and can optionally accept View parameter.
 *
 * Usage for one view:
 *      &#64;OnClick(R.id.button)
 *      public void onMyButtonClick() {
 *          // do something
 *      }
 *
 * For several views:
 *      &#64;OnClick({R.id.button1, R.id.button2, R.id.button3})
 *      public void onButtonClick(View button) {
 *          // do something
 *      }
 *
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OnClick {
//    @IdRes
    int[] value();
}
