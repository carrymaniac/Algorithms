package cn.code.sort;

public abstract class Sort<T extends Comparable<T>> {
    public abstract void sort(T[] nums);

    protected boolean less(T v,T w){
        return v.compareTo(w)<0;
    }

    protected void swap(T[] a,int j,int i){
        T tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }
}
