package com.yc;

import org.junit.Test;

public class BinaryTreeTestCase {
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
