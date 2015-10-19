package com.kendamasoft.binder.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark fields that should be registered in context.
 * It's equivalent of just calling BindingContext.register(field, activity);
 *
 * class MyActivity extends Activity {
 *     &#64;Model
 *     B field;
 *
 *     public void onCreate() {
 *         ...
 *         BindingContext.register(this);
 *         // BindingContext.register(field, this);
 *         ...
 *     }
 * }
 *
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Model {
}
