package com.cache.baechu.datastructure;

public class DoubleLinkedList<K> {

    private Node<K> head = null;
    private Node<K> tail = null;

    public void add(K key) {
        Node<K> newNode = new Node<>(key);
        if (head == null) { //처음 캐시에 저장
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
        if (head == tail) { //노드가 하나
            clear();
        } else { //노드가 두개 이상
            head.next.prev = null;
            head = head.next;
        }
        return deletedKey;
    }

    public void moveToTail(K key) {
        Node<K> findNode = findByKey(key);
        if (findNode == tail) { //이미 최근에 사용
            return;
        }
        if (findNode == head) { //제일 오래전에 사용
            head.next.prev = null;
            head = head.next;
        } else { //중간 노드
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
        if (findNode == head && findNode == tail) { //캐시에 하나만 저장되있으면
            clear();
            return;
        }
        if (findNode == head) { //머리 노드
            findNode.next.prev = null;
            head = findNode.next;
            return;
        }
        if (findNode == tail) { //꼬리 노드
            findNode.prev.next = null;
            tail = findNode.prev;
            return;
        }
        //중간 노드
        findNode.prev.next = findNode.next;
        findNode.next.prev = findNode.prev;
    }

    public void clear() {
        head = null;
        tail = null;
    }
}
