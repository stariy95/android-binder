package com.kendamasoft.binder.annotations;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * One or two way binding of model and view.
 *
 * Simple one way (from view to model) binding example.
 * Field name will always have actual value entered by user in EditText.
 *
 * class SomeModel {
 *      &#64;Bind(R.id.editText)
 *      private String name;
 * }
 *
 * Two way binding.
 * Field value will be reflected in view and vice versa.
 *
 * class SomeModel extends Observable {
 *     &#64;Bind(R.id.editText)
 *     private String name;
 *
 *     public void setName(String name) {
 *         this.name = name;
 *         notifyFieldChange("name");
 *     }
 * }
 *
 *
 *
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Bind {
    //@IdRes
    int value();
}
