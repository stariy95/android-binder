package com.kendamasoft.binder.annotation;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inject view defined by id as field value.
 * Can inject list of views.
 *
 * Inject single view:
 *     &#64;Inject(R.id.textView)
 *     TextView textView;
 *
 * Inject list of views:
 *      &#64;Inject({R.id.imageView1, R.id.imageView2, R.id.imageView3})
 *      List&lt;ImageView&gt; listOfViews;
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Inject {
    //@IdRes
    int[] value();
}
