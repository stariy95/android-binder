package com.kendamasoft.binder.annotations;

//import android.support.annotation.IdRes;

import com.kendamasoft.binder.ViewAttribute;

/**
 * Created by stariy on 19.10.15.
 */
public @interface BindAttribute {
    //@IdRes
    int id();
    ViewAttribute attr();
}
