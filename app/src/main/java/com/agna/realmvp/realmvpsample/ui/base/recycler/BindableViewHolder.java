package com.agna.realmvp.realmvpsample.ui.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ViewHolder поддерживающий отображение данных
 * @param <T>
 */
public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T data);
}
