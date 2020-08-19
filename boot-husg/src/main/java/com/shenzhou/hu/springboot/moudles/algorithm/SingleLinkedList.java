package com.shenzhou.hu.springboot.moudles.algorithm;

/**
 * @description: 单链表
 * @author: 胡胜归
 * @create: 2019-11-25 10:37
 **/
public class SingleLinkedList {
    /**
     * 链表节点的个数
     */
    private int size;
    /**
     * 头节点
     */
    private Node head;

    /**
     * @Description: 构造函数
     * @Param: []
     * @return:
     * @Author: 胡胜归
     * @Date:
     * @throws
     */
    public SingleLinkedList(){
        size = 0;
        head = null;
    }

    /**
     * @Description: 链表的每个节点类
     * @Param:
     * @return:
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    private class Node{
        //每个节点的数据
        private Object data;
        //每个节点指向下一个节点的连接
        private Node next;

        public Node(Object data){
            this.data = data;
        }
    }

    /**
     * @Description: 在链表头添加元素
     * @Param: [obj]
     * @return: java.lang.Object
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public Object addHead(Object obj){
        Node newHead = new Node(obj);
        if(size == 0){
            head = newHead;
        }else{
            newHead.next = head;
            head = newHead;
        }
        size++;
        return obj;
    }

    /** 
    * @Description: 在指定位置插入元素 
    * @Param: [index, obj] 
    * @return: yichang.SingleLinkedList.Node 
    * @Author: 胡胜归
    * @Date: 2019/11/25 
    * @throws  
    */ 
    public Node insert(int index,Object obj){
        if(index >= size){
            return null;
        }
        Node newNode = new Node(obj);
        if(index == 0){
            newNode.next = head;
            head = newNode;
        }else{
            Node target = head;
            Node previous = head;
            int pos = 0;
            while(pos != index){
                previous = target;
                target = target.next;
                pos++;
            }
            previous.next = newNode;
            newNode.next = target;
        }
        size++;
        return newNode;
    }

    /** 
    * @Description: 删除指定位置元素 
    * @Param: [index] 
    * @return: yichang.SingleLinkedList.Node 
    * @Author: 胡胜归
    * @Date: 2019/11/25 
    * @throws  
    */ 
    public Node remove(int index){
        if(index >= size){
            return null;
        }
        Node result = head;
        if(index == 0){
            head = head.next;
        }else{
            Node target = head;
            Node previous = head;
            int pos = 0;
            while(pos != index){
                previous = target;
                target = target.next;
                pos++;
            }
            previous.next = target.next;
            result = target;
        }
        size--;
        return result;
    }
    
   /** 
   * @Description: 删除指定元素
   * @Param: [obj] 
   * @return: yichang.SingleLinkedList.Node 
   * @Author: 胡胜归
   * @Date: 2019/11/25 
   * @throws  
   */ 
    public Node removeNode(Object obj){
        Node target = head;
        Node previous = head;
        if(obj.equals(target.data)){
            head = head.next;
            size--;
        }else{
            while(target.next!=null){
                if(obj.equals(target.next.data)){
                    previous = target;
                    target = target.next;
                    size--;
                    break;
                }else{
                    target = target.next;
                    previous = previous.next;
                }
            }
            previous.next = target.next;
        }
        return target;
    }


    /**
     * @Description: 在链表头删除元素
     * @Param: []
     * @return: java.lang.Object
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public Object deleteHead(){
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    /**
     * @Description: 查找指定元素，找到了返回节点Node，找不到返回null
     * @Param: [obj]
     * @return: yichang.SingleLinkedList.Node
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public Node find(Object obj){
        Node current = head;
        int tempSize = size;
        while(tempSize > 0){
            if(obj.equals(current.data)){
                return current;
            }else{
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }

    /**
     * @Description: 删除指定的元素，删除成功返回true
     * @Param: [value]
     * @return: boolean
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public boolean delete(Object value){
        if(size == 0){
            return false;
        }
        Node current = head;
        Node previous = head;
        while(current.data != value){
            if(current.next == null){
                return false;
            }else{
                previous = current;
                current = current.next;
            }
        }
        //如果删除的节点是第一个节点
        if(current == head){
            head = current.next;
            size--;
        }else{//删除的节点不是第一个节点
            previous.next = current.next;
            size--;
        }
        return true;
    }

    /**
     * @Description: 判断链表是否为空
     * @Param: []
     * @return: boolean
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * @Description: 显示节点信息
     * @Param: []
     * @return: void
     * @Author: 胡胜归
     * @Date: 2019/11/25
     * @throws
     */
    public void display(){
        if(size >0){
            Node node = head;
            int tempSize = size;
            //当前链表只有一个节点
            if(tempSize == 1){
                System.out.println("["+node.data+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.data+"->");
                }else if(node.next == null){
                    System.out.print(node.data+"]");
                }else{
                    System.out.print(node.data+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{
            //如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }

    }
    public Object displayByNum(int k){
        if(size > k){


        }


        return null;
    }


    public static void main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //打印当前链表信息
        singleList.display();
        //删除C
        singleList.delete("C");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
    }

}

