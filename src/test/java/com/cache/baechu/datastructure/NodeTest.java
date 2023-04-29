package com.cache.baechu.datastructure;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NodeTest {

    @Test
    void equalsAndHashcode() {
        //given
        String key = "key";

        //when
        Node<String> node = new Node<>(key);
        Node<String> node2 = new Node<>(key);

        //then
        assertThat(node).isEqualTo(node2);
    }
}
