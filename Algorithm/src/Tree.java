import org.junit.Test;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Tree
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-05-06 11:24
 */

 class Tree {
    class Node<T>
    {
        public T value;
        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public void PreOrder(Node head)
    {
        if (head == null) return;

        System.out.println(head.toString());
        PreOrder(head.left);
        PreOrder(head.right);
    }

    public void InOrder(Node head){
        if (head == null) return;

        InOrder(head.left);
        System.out.println(head.toString());
        InOrder(head.right);
    }

    public void PostOrder(Node head){
        if (head == null) return;

        InOrder(head.left);
        InOrder(head.right);
        System.out.println(head.toString());
    }

    public void PreOrderNoRecursion(Node head){
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        do {
            stack.push(head);
            System.out.println(head.toString());
            if (head.left != null) {
                head = head.left;
                continue;
            }

            if (head.right != null) {
                head = head.right;
                continue;
            }



        }while (!stack.isEmpty());

//        while (!stack.isEmpty()) {
//            Node node = stack.pop();
//            node.right
//        }
    }

    public void InOrderNoRecursion(){}

    public void PostOrderNoRecursion(){}

    @Test
    public static void main(String[] args) {

    }

    // 随机生成一颗二叉树
    private Node RandomCreateBinaryTree(){
        Random random = new Random();
        // 树的深度
        int level = random.nextInt(Bound);
        if (level == 0) {
            return null;
        }

        int curLevel = 1;
        Stack<Node> stack = new Stack<>();
        Node<Integer> head;
        // 头结点不能为空
        do {head = RandomCreateNode(random);}while (head == null);
        stack.push(head);
        while (!stack.empty()) {
            Node curNode = stack.pop();
        }
    }

    // 随机生成一个结点
    private Node<Integer> RandomCreateNode(Random random){
        int number = random.nextInt(Bound);
        return number == 0 ? null : new Node(number);
    }

    private final int Bound = 100;
}
