package org.vinci.collection;

public class HashMap {
    private final int DEFAULT_CAPACITY = 10000;
    Node[] nodes = new Node[DEFAULT_CAPACITY];

    private void put(int key, int value) {
        int index = getIndex(key);
        Node curr = nodes[index];
        Node tmp = curr;
        if (tmp != null) {
            Node prev = null;
            while (tmp != null) {
                // 如果找到了相同的key，则更新value
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
                // 如果没有找到相同的key，则继续遍历下去
                prev = tmp;
                tmp = tmp.next;
            }
            // 找到最后一个节点
            tmp = prev;
        }
        Node node = new Node(key, value);
        if (tmp != null) {
            tmp.next = node;
        } else {
            nodes[index] = node;
        }

    }

    private int get(int key) {
        // 获得链表的第一个节点
        int index = getIndex(key);
        Node curr = nodes[index];

        if (curr != null) {
            // 遍历链表，寻找目标key的节点
            if (curr.key == key) {
                return curr.value;
            } else {
                while (curr != null) {
                    if (curr.key == key) {
                        return curr.value;
                    }
                    curr = curr.next;
                }
            }
        }
        return -1;
    }

    private void remove(int key) {
        int index = getIndex(key);
        Node curr = nodes[index];

        if (curr != null) {
            Node prev = null;
            while (curr != null) {
                if (curr.key == key) {
                    // 如果删除的节点不是头结点，就让前一个指针指向目标节点的下一个
                    if (prev != null) {
                        prev.next = curr.next;
                    }
                    // 如果删除的节点是头结点，就让头指针指向目标节点的下一个
                    else {
                        nodes[index] = curr.next;
                    }
                    // 方便垃圾回收的内容
                    curr.next = null;
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
        }

    }

    private int getIndex(int key) {
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        return hash % DEFAULT_CAPACITY;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        System.out.println(map.get(1)); // 10
        System.out.println(map.get(2)); // 20
        System.out.println(map.get(3)); // 30
        map.remove(2);
        System.out.println(map.get(2)); // -1
    }

}


class Node {
    int key, value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

}

