package com.cache.baechu.datastructure;

import java.util.Objects;

public class Node<K> {

    K key;
    Node<K> next;
    Node<K> prev;

    public Node(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return key.equals(node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
