package sort;

public class SortUtils {


    /**
     * 选择排序
     * @param a
     */
    public static void selectSort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i+1; j < len; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void inserSort(Comparable[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }


    /**
     * a 是否小于 b
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换i 和 j的位置
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
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i]+ " ");
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i<a.length; i++) {
            a[i] = (int)(1+Math.random()*300);
        }
        show(a);
        System.out.println("a[] 是否有序："+isSorted(a));
//        selectSort(a);
        inserSort(a);
        show(a);
        System.out.println("a[] 是否有序："+isSorted(a));

    }
}
