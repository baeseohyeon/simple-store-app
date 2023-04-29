package com.cache.baechu.datastructure;

public class DoubleLinkedList<K> {

    private Node<K> head = null;
    private Node<K> tail = null;

    public void add(K key) {
        Node<K> newNode = new Node<>(key);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public K deleteByLRU() {
        K deletedKey = head.key;
        if (head == tail) {
            clear();
        } else {
            head.next.prev = null;
            head = head.next;
        }
        return deletedKey;
    }

    public void moveToTail(K key) {
        Node<K> findNode = findByKey(key);
        if (findNode == tail) {
            return;
        }
        if (findNode == head) {
            head.next.prev = null;
            head = head.next;
        } else {
            findNode.next.prev = findNode.prev;
            findNode.prev.next = findNode.next;
        }
        findNode.next = null;
        findNode.prev = tail;
        tail.next = findNode;
        tail = findNode;
    }

    public Node<K> findByKey(K key) {
        Node<K> node = tail;
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.prev;
        }
        return null;
    }

    public void deleteByKey(K key) {
        Node<K> findNode = findByKey(key);
        if (findNode == head && findNode == tail) {
            clear();
            return;
        }
        if (findNode == head) {
            findNode.next.prev = null;
            head = findNode.next;
            return;
        }
        if (findNode == tail) {
            findNode.prev.next = null;
            tail = findNode.prev;
            return;
        }
        findNode.prev.next = findNode.next;
        findNode.next.prev = findNode.prev;
    }

    public void clear() {
        head = null;
        tail = null;
    }
}
