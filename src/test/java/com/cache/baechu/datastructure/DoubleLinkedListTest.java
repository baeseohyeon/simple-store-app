package com.cache.baechu.datastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {

    String key = "key1";
    String key2 = "key2";
    String key3 = "key3";
    DoubleLinkedList<String> list = new DoubleLinkedList<>();

    @BeforeEach
    private void init() {
        list.add(key);
        list.add(key2);
        list.add(key3);
    }

    @AfterEach
    private void destroy() {
        list.clear();
    }

    @Test
    void getLeastRecentlyUsed() {
        //when
        Node<String> leastRecentlyUsed = list.getLeastRecentlyUsed();

        //then
        assertThat(leastRecentlyUsed.key).isEqualTo(key);
    }

    @Test
    void getMostRecentlyUsed() {
        //when
        Node<String> mostRecentlyUsed = list.getMostRecentlyUsed();

        //then
        assertThat(mostRecentlyUsed.key).isEqualTo(key3);
    }

    @Test
    void findByKey() {
        //when
        Node<String> findNode = list.findByKey(key);

        //then
        assertThat(findNode.key).isEqualTo(key);
    }

    @Test
    void deleteByLRU() {
        //when
        list.deleteByLRU();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.getLeastRecentlyUsed()).isEqualTo(new Node<>(key2));
        assertThat(list.getMostRecentlyUsed()).isEqualTo(new Node<>(key3));
    }

    @Test
    void moveToTail() {
        //when
        list.moveToTail(key);

        //then
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.getLeastRecentlyUsed()).isEqualTo(new Node<>(key2));
        assertThat(list.getMostRecentlyUsed()).isEqualTo(new Node<>(key));
    }
}
