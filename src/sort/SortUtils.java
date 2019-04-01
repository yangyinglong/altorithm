package sort;


import org.omg.CORBA.COMM_FAILURE;

import java.util.Scanner;

public class SortUtils {


    /**
     * 选择排序
     * @param a
     */
    public static void selectSort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    /**
     * 插入排序
     * @param a
     */
    public static void inserSort(Comparable[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     * @param a
     */
    public static void shellSort(Comparable[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - 1);
                }
            }
            h = h / 3;
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);
        // 归并
        Comparable[] aux = new Comparable[a.length];
        int leftIndex = lo;
        int rightIndex = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (leftIndex>mid) {
                a[k] = aux[rightIndex++];
            } else if(rightIndex > hi) {
                a[k] = aux[leftIndex++];
            } else if(less(aux[rightIndex], aux[leftIndex])) {
                a[k] = aux[rightIndex++];
            } else {
                a[k] = aux[leftIndex++];
            }
        }
    }

    /**
     * a 是否小于 b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换i 和 j的位置
     *
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 数组a是否有序
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i] + " ");
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (1 + Math.random() * 300);
        }
        show(a);
        System.out.println("a[] 是否有序：" + isSorted(a));
//        selectSort(a);
//        inserSort(a);
//        shellSort(a);
        mergeSort(a, 0, 99);
        show(a);
        System.out.println("a[] 是否有序：" + isSorted(a));
//        Scanner in = new Scanner(System.in);
//        String strings = in.next();


    }
}
