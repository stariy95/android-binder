package com.kendamasoft.binder.internal.updater;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.kendamasoft.binder.ViewAttribute;

/**
 *
 * Created by stariy on 03.10.2015.
 */
public class ViewUpdaterFactory {

    static private final class Updaters {
        // Attributes
        static private final ViewUpdater ATTR_VISIBLE = new VisibilityUpdater();
        static private final ViewUpdater ATTR_ENABLED = new EnabledUpdater();
        static private final ViewUpdater ATTR_ALPHA   = new AlphaUpdater();
        // View values
        static private final ViewUpdater VIEW_COMPOUND_BUTTON = new CompoundButtonUpdater();
        static private final ViewUpdater VIEW_TEXT_VIEW       = new TextViewUpdater();
        static private final ViewUpdater VIEW_PROGRESS_BAR    = new ProgressBarUpdater();
        static private final ViewUpdater VIEW_SPINNER         = new SpinnerUpdater();
    }

    static public ViewUpdater getUpdaterForAttribute(ViewAttribute attribute) {
        switch (attribute) {
            case VISIBLE:
                return Updaters.ATTR_VISIBLE;
            case ENABLED:
                return Updaters.ATTR_ENABLED;
            case ALPHA:
                return Updaters.ATTR_ALPHA;
        }
        return null;
    }

    static public ViewUpdater getUpdaterForView(View view) {
        if(view instanceof CompoundButton) {
            return Updaters.VIEW_COMPOUND_BUTTON;
        }
        if(view instanceof TextView) {
            return Updaters.VIEW_TEXT_VIEW;
        }
        if(view instanceof ProgressBar) {
            return Updaters.VIEW_PROGRESS_BAR;
        }
        if(view instanceof Spinner) {
            return Updaters.VIEW_SPINNER;
        }
        return null;
    }

}
