package com.kendamasoft.binder;

import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.View;

import com.kendamasoft.binder.internal.adapter.ViewChangeListener;
import com.kendamasoft.binder.internal.adapter.ViewChangeListenerFactory;
import com.kendamasoft.binder.annotation.Bind;
import com.kendamasoft.binder.annotation.BindAttribute;
import com.kendamasoft.binder.annotation.Callback;
import com.kendamasoft.binder.annotation.Inject;
import com.kendamasoft.binder.annotation.Model;
import com.kendamasoft.binder.annotation.OnClick;
import com.kendamasoft.binder.annotation.OnFocusChange;
import com.kendamasoft.binder.annotation.OnLongClick;
import com.kendamasoft.binder.annotation.OnTouch;
import com.kendamasoft.binder.internal.handler.AnnotationHandler;
import com.kendamasoft.binder.internal.handler.BindAnnotationHandler;
import com.kendamasoft.binder.internal.handler.BindAttributeAnnotationHandler;
import com.kendamasoft.binder.internal.handler.CallbackAnnotationHandler;
import com.kendamasoft.binder.internal.handler.InjectAnnotationHandler;
import com.kendamasoft.binder.internal.handler.ModelAnnotationHandler;
import com.kendamasoft.binder.internal.handler.OnClickAnnotationHandler;
import com.kendamasoft.binder.internal.handler.OnFocusChangeAnnotationHandler;
import com.kendamasoft.binder.internal.handler.OnLongClickAnnotationHandler;
import com.kendamasoft.binder.internal.handler.OnTouchAnnotationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * Main entry for processing annotated members.
 * Simply call one of Binder.register(...) methods.
 *
 */
public class Binder {

    static private final String TAG = Binder.class.getSimpleName();

    static private final Map<Class<? extends Annotation>, AnnotationHandler<? extends Annotation>> annotationHandlerMap = new HashMap<>();

    static {
        annotationHandlerMap.put( Bind.class,          new BindAnnotationHandler()         );
        annotationHandlerMap.put( BindAttribute.class, new BindAttributeAnnotationHandler());
        annotationHandlerMap.put( Inject.class,        new InjectAnnotationHandler()       );
        annotationHandlerMap.put( Model.class,         new ModelAnnotationHandler()        );

        annotationHandlerMap.put( Callback.class,      new CallbackAnnotationHandler()     );

        annotationHandlerMap.put( OnClick.class,       new OnClickAnnotationHandler()      );
        annotationHandlerMap.put( OnLongClick.class,   new OnLongClickAnnotationHandler()  );
        annotationHandlerMap.put( OnTouch.class,       new OnTouchAnnotationHandler()      );
        annotationHandlerMap.put( OnFocusChange.class, new OnFocusChangeAnnotationHandler());
    }

    static private final Map<Integer, ViewChangeListener> registeredListeners = new WeakHashMap<>();

    /**
     * Process all annotated members of activity
     *
     * <code>
     * class MyActivity extends Activity {
     *
     *     &#64;Inject(R.id.textView)
     *     TextView text;
     *
     *     &#64;Bind(R.id.editText)
     *     String editTextContent;
     *
     *     &#64;OnClick(R.id.button)
     *     public void onMyButtonClick() {
     *          // do something
     *     }
     *
     *     &#64;Override
     *     protected void onCreate(Bundle savedInstanceState) {
     *         // ...
     *         Binder.register(this);
     *         editTextContent = "Hello World";
     *         // ...
     *     }
     * }
     * </code>
     *
     * @param activity to process
     */
    public static void register(Activity activity) {
        register(activity, activity);
    }

    /**
     * Usage example:
     * <code>
     * &#64;Override
     * protected void onCreate(Bundle savedInstanceState) {
     *      ...
     *      Binder.register(modelView, this);
     * }
     * </code>
     * @param object to process
     * @param topView top level activity we bind at
     */
    public static void register(Object object, Activity topView) {
        register(object, topView.getWindow().getDecorView());
    }

    /**
     * Usage example:
     * <code>
     * View view = layoutInflater.inflate(...);
     * Binder.register(modelView, view);
     * </code>
     * @param object to process
     * @param topView top level view we bind at
     */
    public static void register(Object object, View topView) {
        Field[] fields = object.getClass().getDeclaredFields();
        Method[] methods = object.getClass().getDeclaredMethods();
        AccessibleObject[] members = new AccessibleObject[fields.length + methods.length];
        System.arraycopy(fields, 0, members, 0, fields.length);
        System.arraycopy(methods, 0, members, fields.length, methods.length);

        for(AccessibleObject field : members) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                AnnotationHandler<Annotation> handler = getHandler(annotation.annotationType());
                if(handler == null) {
                    continue;
                }
                List<View> views = getViews(topView, handler.getViewIds(annotation), true);
                handler.handle(object, field, topView, views, annotation);
            }
        }
    }

    //@NonNull
    public static ViewChangeListener getListenerForView(View view) {
        ViewChangeListener listener = registeredListeners.get(view.getId());
        if(listener == null) {
            listener = createListener(view);
        }
        return listener;
    }

    //@NonNull
    private static ViewChangeListener createListener(View view) {
        ViewChangeListener listener = new ViewChangeListener();
        registeredListeners.put(view.getId(), listener);
        ViewChangeListenerFactory.getAdapterForView(view, listener);
        return listener;
    }

    /**
     * Keep as clean as possible the mess with generics.
     *
     * @param annotationType class of the annotation we are looking handler for
     * @param <T> type of annotation
     * @return handler
     */
    @SuppressWarnings("unchecked")
    //@Nullable
    private static <T extends Annotation> AnnotationHandler<Annotation> getHandler(Class<T> annotationType) {
        return (AnnotationHandler<Annotation>)annotationHandlerMap.get(annotationType);
    }

    //@NonNull
    private static List<View> getViews(View topView, int[] ids, boolean abortOnError) {
        List<View> result = new ArrayList<>();
        for(int id : ids) {
            View next = topView.findViewById(id);
            if(next == null && abortOnError) {
                throw new RuntimeException("Can't find view with id " + id + " in view " + topView);
            }
            result.add(next);
        }
        return result;
    }
}
