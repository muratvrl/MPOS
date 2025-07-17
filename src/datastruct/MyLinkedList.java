package datastruct;

import java.util.Iterator;
public class MyLinkedList<T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;
        Node(T data) { this.data = data; }
    }

    private Node head, tail;
    private int size = 0;
    public void add(T data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node curr = head;
            private Node prev = null;
            private Node prevPrev = null;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T d = curr.data;
                prevPrev = prev;
                prev = curr;
                curr = curr.next;
                return d;
            }

            @Override
            public void remove() {
                if (prev == null) return;
                if (prevPrev == null) {
                    head = head.next;
                } else {
                    prevPrev.next = prev.next;
                }
                if (prev == tail) tail = prevPrev;
                size--;
                prev = null;
            }
        };
    }
}