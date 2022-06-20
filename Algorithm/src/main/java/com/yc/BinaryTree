package com.yc;

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

    public void PreVisit(Node head, StringBuilder sb)
    {
        if (head == null) return;

        sb.append(head + ",");
        PreVisit(head.left, sb);
        PreVisit(head.right, sb);
    }

    public void InVisit(Node head, StringBuilder sb){
        if (head == null) return;

        InVisit(head.left, sb);
        sb.append(head + ",");
        InVisit(head.right, sb);
    }

    public void PostVisit(Node head, StringBuilder sb){
        if (head == null) return;

        PostVisit(head.left, sb);
        PostVisit(head.right, sb);
        sb.append(head + ",");
    }

    // 二叉树的先序遍历 非递归实现
    public void PreVisitUnRecur(StringBuilder sb){
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        do {
            // 先把所有左子树结点压栈
            if (cur != null) {
                stack.push(cur);
                sb.append(cur + ",");
                cur = cur.left;
            }else {
                // 左子树遍历结束 从栈中弹出一个结点 遍历该结点右子树
                cur = stack.pop();
                cur = cur.right;
            }
        }while (!stack.isEmpty() || cur != null);
    }

    // 二叉树的中序遍历 非递归实现
    public void InVisitNoRecur(StringBuilder sb)
    {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        do {
            // 先把所有左子树结点压栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {
                // 左子树遍历结束 从栈中弹出一个结点 遍历该结点右子树
                cur = stack.pop();
                sb.append(cur + ",");
                cur = cur.right;
            }
        }while (!stack.isEmpty() || cur != null);
    }

    // 二叉树的后序遍历 非递归实现
    public void PostVisitNoRecur(StringBuilder sb)
    {
        if (head == null) return;

        Node cur = head;
        Stack<Node> stack = new Stack<>();
        stack.push(cur);
        Stack<Node> outStack = new Stack<>();
        while (!stack.isEmpty()) {
            cur = stack.pop();
            outStack.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        while (!outStack.isEmpty()) {
            sb.append(outStack.pop() + ",");
        }
    }

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
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) continue;

            if (isFirstNode) {
                isFirstNode = false;
                BuildTab(sb,(1<<(TreeLevel- curLevel))-1);
                if (node != null) sb.append(node);
            }else {
                BuildTab(sb,1<<(TreeLevel - curLevel + 1));
                if (node != null) sb.append(node);
            }

            queue.add(node.left);
            queue.add(node.right);

            // 修改下一层的结束结点
            if (node.left != null) nextLevelEnd = node.left;
            if (node.right != null) nextLevelEnd = node.right;

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

    // 按层遍历
    public void LevelVisit(StringBuilder sb)
    {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLevelEnd = head;
        Node nextLevelEnd = null;
        int maxLevel = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 空节点不做处理
            if (cur == null) continue;

            // 打印当前结点
            sb.append(cur + ",");

            if (cur.left != null) {
                queue.add(cur.left);
                // 下一层结束结点
                nextLevelEnd = cur.left;
            }

            if (cur.right != null) {
                queue.add(cur.right);
                // 下一层结束结点
                nextLevelEnd = cur.right;
            }

            // 到本层的最后一个结点
            if (cur == curLevelEnd) {
                curLevelEnd = nextLevelEnd;
            }
        }
    }

    // 随机生成一颗二叉树
    public void RandomCreateBinaryTree(){
        // 树的深度
        int maxLevel = random.nextInt(Bound);
        if (maxLevel == 0) {
            head = null;
            return;
        }

        do {
            // 头结点不能为空
            do {head = RandomCreateNode();}while (head == null);
            RandomCreateBinaryTree(head,1,maxLevel);
        }while (TreeLevel < maxLevel);
    }

    private void RandomCreateBinaryTree(BinaryTree.Node node, int curLevel, int maxLevel){

        // 叶子结点没有子树
        if (node == null) return;
        TreeLevel = curLevel > TreeLevel ? curLevel : TreeLevel;
        if (curLevel >= maxLevel) return;

        // 处理左子树
        node.left = RandomCreateNode();
        RandomCreateBinaryTree(node.left,curLevel+1, maxLevel);

        // 处理右子树
        node.right = RandomCreateNode();
        RandomCreateBinaryTree(node.right, curLevel+1, maxLevel);
    }

    // 随机生成一个结点
    private BinaryTree.Node RandomCreateNode(){
        int number = random.nextInt(Bound);
        return new BinaryTree.Node(number);
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
    public void Test() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.RandomCreateBinaryTree();
        System.out.println(binaryTree);

        StringBuilder sb = new StringBuilder();
        binaryTree.PreVisit(binaryTree.head, sb);
        System.out.println("PreVisit:" + sb);

        sb.setLength(0);
        binaryTree.InVisit(binaryTree.head, sb);
        System.out.println("InVisit:" + sb);

        sb.setLength(0);
        binaryTree.PostVisit(binaryTree.head, sb);
        System.out.println("PostVisit:" + sb);

        sb.setLength(0);
        binaryTree.LevelVisit(sb);
        System.out.println("LevelVisit:" + sb);

        sb.setLength(0);
        binaryTree.PreVisitUnRecur(sb);
        System.out.println("PreVisitUnRecur:"+ sb);

        sb.setLength(0);
        binaryTree.InVisitNoRecur(sb);
        System.out.println("InVisitUnRecur:" + sb);

        sb.setLength(0);
        binaryTree.PostVisitNoRecur(sb);
        System.out.println("PostVisitUnRecur:" + sb);
    }
}