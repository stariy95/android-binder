package com.kendamasoft.binder.annotations;

//import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate method as callback for view value change.
 * Supported view types (and subtypes):
 *      EditText,
 *      CompoundButton (CheckBox, RadioButton, Switch, ToggleButton, etc..)
 *      SeekBar
 * Usage:
 *
 *      &#64;Callback(R.id.checkbox)
 *      public void onMyCheckBoxChange(boolean newValue) {
 *          // do something
 *      }
 *
 *      &#64;Callback(R.id.editText)
 *      public void onNameChange(View view, String newValue) {
 *          // do something
 *      }
 *
 * For full model to view binding @see com.kendamasoft.binder.annotations.Bind
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Callback {
    //@IdRes
    int[] value();
}
