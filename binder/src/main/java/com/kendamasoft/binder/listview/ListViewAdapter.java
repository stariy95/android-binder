package com.kendamasoft.binder.listview;

import android.content.Context;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kendamasoft.binder.BindingContext;

import java.util.List;

/**
 * Created by stariy on 13.10.15.
 */
public class ListViewAdapter<T, B extends ModelToViewBinder<T>> extends ArrayAdapter<T> {

    static private final String TAG = ListViewAdapter.class.getSimpleName();

//    @LayoutRes
    private int resource;

    private Class<B> binderClass;

    public ListViewAdapter(Context context, /*@LayoutRes*/ int resource, Class<B> binderClass, /*@NonNull*/ List<T> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.binderClass = binderClass;
    }

    @Override
    @SuppressWarnings("unchecked") // cast Object from getTag() to B type
    public View getView(int position, View convertView, ViewGroup parent) {
        B holder = null;
        View rowView = convertView;
        if(rowView != null) {
            holder = (B)convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(resource, parent, false);
            try {
                holder = binderClass.newInstance();
                holder.setContext(getContext());
                BindingContext.register(holder, rowView);
            } catch (Exception ex) {
                // should not happen, ModelToViewBinder has public default constructor
                Log.e(TAG, "Should not happen", ex);
                return null;
            }
            rowView.setTag(holder);
        }
        holder.applyView(getItem(position), rowView);

        return rowView;
    }
}
