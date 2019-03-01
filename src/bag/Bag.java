package bag;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Item[] items;
    private int len;

    public Bag(){
        items = (Item[]) new Object[2];
        len = 0;
    }

    private void resize(int capacity) {
        assert capacity >= len;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < len; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public void add(Item item) {
        if (len == items.length) {
            resize(2*items.length);
        }
        items[len++] = item;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public int size() {
        return len;
    }

    @Override
    public Iterator<Item> iterator() {
        class BagIter implements Iterator<Item> {
            private int cur = 0;

            @Override
            public boolean hasNext() {
                return cur < size();
            }

            @Override
            public Item next() {
                Item item = items[cur];
                cur ++;
                return item;
            }
        }
        return new BagIter();
    }

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hdu";
        String s3 = "cn";
        Bag<String> bag = new Bag<String>();
        bag.add(s1);
        bag.add(s2);
        bag.add(s3);
        System.out.println(bag.size());
        Iterator iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println((String)iterator.next());
        }
    }


}
