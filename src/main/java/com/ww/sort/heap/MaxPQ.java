package com.ww.sort.heap;

/**
 * 基于堆的优先队列
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq; // 基于堆的完全二叉树
    private int N = 0; // 存储于pq[1..N]中，pq[0]没有使用

    @SuppressWarnings("unchecked")
    public MaxPQ(int maxN) {
        pq = (Key[])new Comparable[maxN+1];
    }

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N-1);
        pq[N+1] = null;
        sink(1);

        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 上浮
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j+1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
