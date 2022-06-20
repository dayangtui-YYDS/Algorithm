package com.yc; /**
 * @ClassName: main.java.com.yc.Heap
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-05-06 11:45
 */

import org.junit.Test;

import java.util.*;

/**
 * @ClassName: main.java.com.yc.Heap
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-05-06 11:45
 */
public class Heap<T> {

    private class Inner{
        public T value;
        public Inner(T value){
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Inner tmp = (Inner) obj;
            if (tmp == null) return false;

            return tmp == this;
        }
    }

    // 构建堆
    private void HeapInsert(int index, boolean isBigHeap)
    {
        int parent = (index -1) / 2;
        if (isBigHeap) { //构建大根堆
            while (comparator.compare(elements.get(parent).value, elements.get(index).value) < 0) {
                Swap(parent,index);
                index = parent;
                parent = (index -1) / 2;
            }
        }else{ // //构建小根堆
            while (comparator.compare(elements.get(parent).value, elements.get(index).value) > 0) {
                Swap(parent,index);
                index = parent;
                parent = (index -1) / 2;
            }
        }
    }


    private void HeapIfy(int index,boolean isBigHeap)
    {
        int left = (index << 1) + 1;
        int right = left + 1;
        //有子节点 取较大的子节点与该节点比较
        while (left < heapSize)
        {
            int best ;
            if (isBigHeap) { //构建大根堆
                if (right < heapSize){
                    best = comparator.compare(elements.get(left).value,elements.get(right).value)<0?right:left;
                }else {
                    best = left;
                }

                if (comparator.compare(elements.get(index).value,elements.get(best).value)<0) {
                    Swap(index,best);
                    index = best;
                    left = (index << 1) + 1;
                    right = left + 1;
                }else {
                    break;
                }
            }else {
                if (right < heapSize){
                    best = comparator.compare(elements.get(left).value,elements.get(right).value)>0?right:left;
                }else {
                    best = left;
                }

                if (comparator.compare(elements.get(index).value,elements.get(best).value)>0) {
                    Swap(index,best);
                    index = best;
                    left = (index << 1) + 1;
                    right = left + 1;
                }else {
                    break;
                }
            }
        }
    }

    private void Swap(int i,int j){
        Inner obj1 = elements.get(i);
        Inner obj2 = elements.get(j);
        elements.set(i,obj2);
        elements.set(j,obj1);
        indexMap.put(obj1,j);
        indexMap.put(obj2,i);
    }

    public Heap(Comparator<T> comparator) {
        indexMap = new HashMap<>();
        elements = new ArrayList<>();
        this.comparator = comparator;
        heapSize = 0;
    }

    public T Peek(){
        return elements.get(0).value;
    }

    public boolean Push(T obj,boolean isBigHeap)
    {
        Inner inner = new Inner(obj);
        if (indexMap.containsKey(inner)) return false;

        elements.add(inner);
        int index = heapSize++;
        indexMap.put(inner, index);
        HeapInsert(index,isBigHeap);
        return true;
    }

    public T Pop(boolean isBigHeap)
    {
       //先将第一个元素和最后一个元素交换 再移除最后一个元素
       Swap(0,heapSize-1);
       Inner obj = elements.get(--heapSize);
       indexMap.remove(obj);
       elements.remove(obj);

       // 调整第一个元素使其成为最大堆
       HeapIfy(0,isBigHeap);
       return obj.value;
    }

    public boolean Remove(T obj,boolean isBigHeap)
    {
        if (!indexMap.containsKey(obj)) return false;

        int index = indexMap.get(obj);
        Swap(index,--heapSize);
        elements.remove(obj);
        indexMap.remove(obj);

        HeapInsert(index,isBigHeap);
        HeapIfy(index,isBigHeap);
        return true;
    }

    public boolean Contains(T obj){
        return indexMap.containsKey(obj);
    }

    public boolean IsEmpty(){
        return elements.isEmpty();
    }

    public List<T> GetAllElements(){
        ArrayList<T> list = new ArrayList<>();
        for (Inner element : elements) {
            list.add(element.value);
        }
        return list;
    }

    public int Size(){
        return heapSize;
    }

    // 比较器
    private Comparator<T> comparator;
    private ArrayList<Inner> elements;
    private int heapSize;
    private Map<Inner, Integer> indexMap;
}

class Line
{
    public int Start;
    public int End;
    public Line(int start,int end){
        this.Start = start;
        this.End = end;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]",Start,End);
    }
}

class MaxLineCover{
    // 随机生成一条[0，value]之间的线段
    private Line RandomLine(int value){
        int start = (int)(Math.random()*(value));
        int end;
        do {
            end = (int)(Math.random()*(value+1));
        }while (start >= end);

        return new Line(start,end);
    }

    // 随机生成一组线段
    private Line[] RandomLines(int value, int count){
        Line[] lines = new Line[count];
        for (int i = 0; i< count; ++i){
            lines[i] = RandomLine(value);
        }
        return lines;
    }

    //最大线段重合数量
    private int MaxLineCover(Line[] lines){
        //将线段根据起点从小到大依次排序
        Arrays.sort(lines,(a,b)-> a.Start - b.Start );

        int max = 0;
        Heap<Integer> heap = new Heap<Integer>((a,b)->a-b);
        for (Line line : lines) {
            if (!heap.Push(line.End, false)) {
                System.out.println("main.java.com.yc.Heap Push fail");
            }

            assert  heap.Push(line.End,false) :"main.java.com.yc.Heap Push fail";
            while (!heap.IsEmpty() && heap.Peek() <= line.Start) {
                heap.Pop(false);
            }

            if (max < heap.Size())
            {
                max = heap.Size();
            }
        }
        return max;
    }

    private int MaxLineCoverEnmu(Line[] lines)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Line line : lines) {
            if (min > line.Start)
                min = line.Start;

            if (max < line.End )
                max = line.End;
        }

        int maxCount = 0;
        for (int node = min; node < max; ++node){
            int count = 0;
            for (Line line : lines) {
                if (node+0.5 > line.Start && node+0.5 < line.End){
                    count++;
                }
            }
            if (maxCount < count)
                maxCount = count;
        }
        return maxCount;
    }

    public void Test(){
        int total = 10000;
        int lineCount = 10;
        for (int i = 0;i < total; ++i){
            Line[] lines = RandomLines(100,lineCount);

            int actual = MaxLineCover(lines);
            int expect = MaxLineCoverEnmu(lines);
            if (actual != expect) {
                assert actual== expect;
                System.out.println(String.format("main.java.com.yc.MaxLineCover error: actual:%d expect:%d",actual,expect));
                for (Line line : lines) {
                    System.out.println(line.toString()+"\t");
                }
                System.out.println();
            }
        }
    }
    @Test
    public static void main(String[] args) {
        new MaxLineCover().Test();
    }

    private Line[] lines;
}