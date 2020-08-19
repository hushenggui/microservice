package com.shenzhou.hu.springboot.moudles.algorithm;

/**
 * @description: 双链表
 * @author: 胡胜归
 * @create: 2019-11-25 15:17
 **/
public class DoublePointLinkedList {
    //节点个数
    private int size;
    //头节点
    private Node head;
    //尾节点
    private Node tail;

    public class Node{
        private Object data;
        private Node next;

        public Node(Object obj){
            this.data = obj;
        }
    }

    public DoublePointLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    /** 
    * @Description: 链表头部新增节点
    * @Param: [obj] 
    * @return: void 
    * @Author: 胡胜归
    * @Date: 2019/11/25 
    * @throws  
    */ 
    public void addHead(Object obj){
        Node node = new Node(obj);
        //判断链表是否为空
        if(size == 0){
            head = node;
            tail = node;
            size++;
        }else{
            node.next  = head;
            head = node;
            size++;
        }
    }

    /**
    * @Description: 链表尾增加节点
    * @Param: [obj]
    * @return: void
    * @Author: 胡胜归
    * @Date: 2019/11/25
    * @throws
    */
    public void addTail(Object obj){
        Node node = new Node(obj);
        if(size == 0){
            head = node;
            tail = node;
            size++;
        }else{
            tail.next = node;
            tail = node;
            size++;
        }
    }

    /**
    * @Description: 删除头部节点,成功返回true,失败返回false
    * @Param: []
    * @return: boolean
    * @Author: 胡胜归
    * @Date: 2019/11/25
    * @throws
    */
    public boolean delHead(){
        if(size == 0){
            return false;
        }
        //表明链表中有一个节点
        if(head.next == null){
            head = null;
            tail = null;
        }else{
            head = head.next;
        }
        size--;
        return true;
    }

    /** 
    * @Description: 判断是否为空 
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
    * @Description: 获得链表的节点个数 
    * @Param: [] 
    * @return: int 
    * @Author: 胡胜归
    * @Date: 2019/11/25 
    * @throws  
    */ 
    public int getSize(){
        return size;
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
        if(size > 0){
            Node node = head;
            int template = size;

            if(template > 0){
                if(node.equals(head)){
                    System.out.print("[" + head.data + "->");
                }else if(node.next == null){
                    System.out.print(tail.data + "]");
                }else{
                    System.out.print( node.data + "->");
                }
                node = node.next;
                template--;
            }
            System.out.println();
        }else{
            //如果链表一个节点都没有,直接打印[]
            System.out.println("[]");
        }
    }
}