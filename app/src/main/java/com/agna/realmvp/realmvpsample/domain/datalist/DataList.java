package com.agna.realmvp.realmvpsample.domain.datalist;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List для работы с пагинацией
 * Имеет лимит и смещение
 * Можно сливать с другим DataList
 *
 * @param <T> Item
 */
public class DataList<T> implements List<T>, Serializable {

    private int limit;
    private int offset;

    private ArrayList<T> data;

    public interface MapFunc<R, T>{
        R call(T item);
    }

    public static <T> DataList<T> empty() {
        return new DataList<>(new ArrayList<>(), 0, 0);
    }

    public DataList(Collection<T> data, int limit, int offset) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * Слияние двух DataList
     *
     * @param data DataList для слияния с текущим
     * @return текущий экземпляр
     */
    public DataListMergeChanges merge(DataList<T> data) {
        int lastSize = this.data.size();
        boolean reverse = data.offset < this.offset;
        ArrayList<T> merged = tryMerge(reverse ? data : this, reverse ? this : data);
        if (merged == null) {
            //Отрезки данных не совпадают, слияние не возможно
            return new DataListMergeChanges(lastSize, 0, 0);
        }
        this.data.clear();
        this.data.addAll(merged);
        if (this.offset < data.offset) {
            this.limit = data.offset + data.limit - this.offset;
        } else if (this.offset == data.offset) {
            this.limit = data.limit;
        } else {
            this.offset = data.offset;
        }
        return new DataListMergeChanges(lastSize, data.size(), data.getOffset());
    }

    public <R> DataList<R> transform(MapFunc<R, T> mapFunc){
        List<R> resultData = new ArrayList<R>();
        for(T item : this){
            resultData.add(mapFunc.call(item));
        }
        return new DataList<>(resultData, limit, offset);
    }

    /**
     * возвращает значение offset c которого нужно начать чтобы подгрузить слкдующий блок данных
     */
    public int getNextOffset() {
        return limit + offset;
    }

    /**
     * Получить смещение данных
     *
     * @return
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Получить лимит данных
     *
     * @return
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Проверка возможности дозагрузки данных
     *
     * @return
     */
    public boolean canGetMore() {
        return data.size() == limit;
    }

    @Nullable
    private ArrayList<T> tryMerge(DataList<T> to, DataList<T> from) {
        if ((to.offset + to.limit) >= from.offset) {
            return merge(to.data, from.data, from.offset - to.offset);
        }
        return null;
    }

    private ArrayList<T> merge(ArrayList<T> to, ArrayList<T> from, int start) {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(start < to.size() ? to.subList(0, start) : to);
        result.addAll(from);
        return result;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return data.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.data.clear();
        limit = 0;
        offset = 0;
    }

    @Override
    public T get(int index) {
        return data.get(index);
    }

    @Override
    public T set(int index, T element) {
        return data.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        limit--;
        return data.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return data.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return data.listIterator(index);
    }

    @NonNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataList)) return false;

        DataList<?> dataList = (DataList<?>) o;

        if (limit != dataList.limit) return false;
        if (offset != dataList.offset) return false;
        return data != null ? data.equals(dataList.data) : dataList.data == null;

    }

    @Override
    public int hashCode() {
        int result = limit;
        result = 31 * result + offset;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataList{");
        sb.append("limit=").append(limit);
        sb.append(", offset=").append(offset);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}