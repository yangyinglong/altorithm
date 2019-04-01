package stack;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Item[] items;
    private int maxSize, top;

    public Stack() {
        this(10);
    }

    public Stack(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            top = -1;
            items = (Item[]) new Object[initialSize];
        } else {
            throw new RuntimeException("初始化不能小于0");
        }
    }


    private void resize(int capacity) {
        assert capacity >= maxSize;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < maxSize; i++) {
            temp[i] = items[i];
        }
        items = temp;
        maxSize = maxSize * 2;
    }

    public void push(Item item) {
        if (top == maxSize-1) {
            resize(2*maxSize);
        }
        items[++top] = item;
    }

    public Item pop() {
        if (top > -1) {
            Item item =  items[top];
            items[top--] = null;
            return item;
        }else {
            return null;
        }
    }

    public boolean isEmpty() {
        return top > -1;
    }

    public int size() {
        return top + 1;
    }

    @Override
    public Iterator<Item> iterator() {
        class StackIter implements Iterator<Item> {

            private int cur = 0;

            @Override
            public boolean hasNext() {
                return cur <= top;
            }

            @Override
            public Item next() {
                Item item = items[cur];
                cur++;
                return item;
            }
        }
        return new StackIter();
    }

    public static void calculate(String formula) {
        Stack<String> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        for (int i = 0; i < formula.length(); i++) {
            String s = formula.substring(i,i+1);
            if (s.equals("("));
            else if (s.equals("+")) operators.push(s);
            else if (s.equals("-")) operators.push(s);
            else if (s.equals("*")) operators.push(s);
            else if (s.equals("/")) operators.push(s);
            else if (s.equals(")")) {
                String op = operators.pop();
                double value = values.pop();
                if (op.equals("+")) value = values.pop() + value;
                else if (op.equals("-")) value = values.pop() - value;
                else if (op.equals("*")) value = values.pop() * value;
                else if (op.equals("/")) value = values.pop() / value;
                values.push(value);
            }
            else values.push(Double.parseDouble(s));
        }
        System.out.println(values.pop());
    }

    public static void main(String[] args) {

        calculate("((2*3)-(4*1))");


    }
}
