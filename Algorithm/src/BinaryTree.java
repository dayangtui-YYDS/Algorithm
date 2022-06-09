import com.sun.xml.internal.ws.api.model.CheckedException;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @ClassName: Tree
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-05-06 11:24
 */

 public  class BinaryTree<T> {
   public class Node
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

    public void PreVisit(Node head)
    {
        if (head == null) return;

        System.out.print(head+",");
        PreVisit(head.left);
        PreVisit(head.right);
    }

    public void InVisit(Node head){
        if (head == null) return;

        InVisit(head.left);
        System.out.println(head.toString());
        InVisit(head.right);
    }

    public void PostVisit(Node head){
        if (head == null) return;

        PostVisit(head.left);
        PostVisit(head.right);
        System.out.print(head+",");
    }

    public void PreVisitUnRecursion(Node head){
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

    public void InVisitNoRecursion(){}

    public void PostVisitNoRecursion(){}

    @Override
    public String toString() {
        if (head == null) return null;

        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int curLevel = 1;
        Node curLevelEnd = head;
        Node nextLevelEnd = null;
        // 是否为每层的第一个结点
        boolean isFirstNode = true;
        while (curLevel <= TreeLevel && !queue.isEmpty()) {
            Node node = queue.poll();
            if (isFirstNode) {
                isFirstNode = false;
                BuildTab(sb,(2<<(TreeLevel- curLevel)-1));
                if (node != null) sb.append(node);
            }else {
                BuildTab(sb,2<<(TreeLevel - curLevel + 1));
                if (node != null) sb.append(node);
            }

            if (curLevel < TreeLevel) {
                if (node != null)
                {
                    queue.add(node.left);
                    queue.add(node.right);

                    // 修改下一层的结束结点
                    if (node.left != null)
                        nextLevelEnd = node.left;
                    if (node.right != null) {
                        nextLevelEnd = node.right;
                }
                else
                    queue.add(null);
                }
            }

            //当前层遍历结束
            if (curLevelEnd == node) {
                curLevel ++;
                curLevelEnd = nextLevelEnd;
                isFirstNode = true;
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    private void BuildTab(StringBuilder sb, int num){
        for (int i = 0; i < num; i++) {
            sb.append('\t');
        }
    }

    // 随机生成一颗二叉树
    public void RandomCreateBinaryTree(){
        // 树的深度
        TreeLevel = random.nextInt(Bound);
        if (TreeLevel == 0) {
            head = null;
            return;
        }

        // 头结点不能为空
        do {head = RandomCreateNode(1);}while (head == null);
        RandomCreateBinaryTree(head,1,TreeLevel);
        return;
    }

    private void RandomCreateBinaryTree(BinaryTree.Node node, int curLevel, int maxLevel){
        if (node == null || curLevel >= maxLevel) return;

        // 处理左子树
        node.left = RandomCreateNode(curLevel+1);
        RandomCreateBinaryTree(node.left,curLevel+1, maxLevel);

        // 处理右子树
        node.right = RandomCreateNode(curLevel+1);
        RandomCreateBinaryTree(node.right,curLevel+1, maxLevel);
    }

    // 随机生成一个结点
    private BinaryTree.Node RandomCreateNode(int level){
        int number = random.nextInt(Bound);
        return number == 0 ? null : new BinaryTree.Node(number);
    }

    private final int Bound = 5;
    private final Random random = new Random();

    // 二叉树的深度
    private int TreeLevel;
    // 二叉树的头结点
    public Node head;
}

class Demo
{
    @Test
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.RandomCreateBinaryTree();
        System.out.println(binaryTree);
        binaryTree.PreVisit(binaryTree.head);
    }
}