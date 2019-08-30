package com.ww.search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {

    private int n; // 键值对数
    private Node first; // 链表首结点

    /** 节点内部类 */
    private class Node {
        // 链表结点的定义
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {}

    /**
     * 返回节点数
     * @return
     */
    public int size() { return n; }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty() { return 0 == size(); }

    /**
     * 判断是否包含键值key
     * @param key
     * @return
     */
    public boolean contains(Key key) {
        if (null == key) {
            throw new IllegalArgumentException("argument to container() is null");
        }

        return null != get(key);
    }

    /**
     * 根据key从头依次查询
     * @param key
     * @return
     */
    public Value get(Key key) {
        // 查找给定的键，返回相关联的值
        if (null == key) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; null != x; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null; // 未命中
    }

    public void put(Key key, Value val) {
        if (null == key) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (null == val) {
            delete(key);
            return;
        }

        // 查找给定的键，找到则更新其值，否则在表中新建结点
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first); // 未命中，新建结点
        n++;
    }

    /**
     * 根据key删除节点
     * @param key
     */
    public void delete(Key key) {
        if (null == key) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (null == x) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * 键值集合
     * @return
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new ArrayDeque<>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }
}
