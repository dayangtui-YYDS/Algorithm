import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Tree
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-05-06 11:24
 */

 class Tree {

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

}

class Node
{
    public int value;
    public Node left;
    public Node right;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
