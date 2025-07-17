package datastruct;
public class MyHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = (Entry<K, V>[]) new Entry[capacity];
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int idx = getIndex(key);
        Entry<K, V> head = buckets[idx];
        for (Entry<K, V> cur = head; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                cur.value = value;
                return;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value);
        entry.next = head;
        buckets[idx] = entry;
    }
    public V get(K key) {
        int idx = getIndex(key);
        for (Entry<K, V> cur = buckets[idx]; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) return cur.value;
        }
        return null;
    }
    public MyLinkedList<K> keySet() {
        MyLinkedList<K> keys = new MyLinkedList<>();
        for (Entry<K, V> head : buckets) {
            for (Entry<K, V> cur = head; cur != null; cur = cur.next) {
                keys.add(cur.key);
            }
        }
        return keys;
    }
    public MyLinkedList<V> values() {
        MyLinkedList<V> vals = new MyLinkedList<>();
        for (Entry<K, V> head : buckets) {
            for (Entry<K, V> cur = head; cur != null; cur = cur.next) {
                vals.add(cur.value);
            }
        }
        return vals;
    }}