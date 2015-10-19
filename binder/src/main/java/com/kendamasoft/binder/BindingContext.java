package com.kendamasoft.binder;

import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.View;

import com.kendamasoft.binder.adapters.ViewChangeListener;
import com.kendamasoft.binder.adapters.ViewChangeListenerFactory;
import com.kendamasoft.binder.annotations.Bind;
import com.kendamasoft.binder.annotations.BindAttribute;
import com.kendamasoft.binder.annotations.Callback;
import com.kendamasoft.binder.annotations.Inject;
import com.kendamasoft.binder.annotations.Model;
import com.kendamasoft.binder.annotations.OnClick;
import com.kendamasoft.binder.annotations.OnFocusChange;
import com.kendamasoft.binder.annotations.OnLongClick;
import com.kendamasoft.binder.annotations.OnTouch;
import com.kendamasoft.binder.handlers.AnnotationHandler;
import com.kendamasoft.binder.handlers.BindAnnotationHandler;
import com.kendamasoft.binder.handlers.BindAttributeAnnotationHandler;
import com.kendamasoft.binder.handlers.CallbackAnnotationHandler;
import com.kendamasoft.binder.handlers.InjectAnnotationHandler;
import com.kendamasoft.binder.handlers.ModelAnnotationHandler;
import com.kendamasoft.binder.handlers.OnClickAnnotationHandler;
import com.kendamasoft.binder.handlers.OnFocusChangeAnnotationHandler;
import com.kendamasoft.binder.handlers.OnLongClickAnnotationHandler;
import com.kendamasoft.binder.handlers.OnTouchAnnotationHandler;

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
 * Created by stariy on 03.10.2015.
 */
public class BindingContext {

    static private final String TAG = BindingContext.class.getSimpleName();

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

    public static void register(Activity activity) {
        register(activity, activity);
    }

    public static void register(Object object, Activity topView) {
        register(object, topView.getWindow().getDecorView());
    }

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
