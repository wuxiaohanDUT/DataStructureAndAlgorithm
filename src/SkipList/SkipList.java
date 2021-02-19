package SkipList;

import java.util.Random;

public class SkipList <K extends Comparable<K>,V>{

    private final static int MAX_VALUE = 1<<4;

    /**
     * 头节点
     */
    public Node<K,V> head;

    /**
     * 尾节点
     */
    public Node<K,V> tail;

    /**
     * 最大层数
     */
    private int maxLevel = 1;

    public SkipList(){
        head=new Node<K, V>();
    }

    public SkipList(K key,V val){
        this.head=new Node<K, V>();

    }

    public SkipList(int maxLevel){
        this.maxLevel=maxLevel;
    }

    /**
     * 获取随机层数
     * @return
     */
    private int randLevel(){
        int level=new Random().nextInt(MAX_VALUE)+1;
        return level;
    }

    /**
     * 插入节点
     * @param key
     * @param val
     * @return
     */
    public boolean insert(K key,V val){
        //生成一个新节点层
        int newNodeLevel = randLevel();
        //待插入节点的每一层的前驱节点
        Node<K,V>[] previousNodes=new Node[maxLevel];
        //搜索待插入节点键是否已经存在
        Node<K,V> q=genericSearch(key,previousNodes);
        //如果存在，则覆盖
        if(q!=null&&q.compareKey(key)==0){
            q.val=val;
            return true;
        }
        //如果不存在，生成一个新的节点
        Node<K,V> newNode=new Node<K, V>(key,val,newNodeLevel);
        int l=newNodeLevel-1;
        //如果新节点层数高于已存在节点最大层数，需要更新头节点的指针
        if(newNodeLevel>=maxLevel){
            for(;l>=maxLevel;l--){
                this.head.next[l]=newNode;
            }
            this.maxLevel=newNodeLevel;
        }
        //更新插入节点的后继指针
        for(;l>=0;--l){
            newNode.next[l]=previousNodes[l].next[l];
            previousNodes[l].next[l]=newNode;
        }
        return true;
    }

    /**
     * 删除指定key的节点
     * @param key
     * @return
     */
    public V delete(K key){
        Node<K,V>[] previousNodes=new Node[maxLevel];
        Node<K,V> kvNode=genericSearch(key,previousNodes);
        if(kvNode==null){
            return null;
        }
        for(int i= previousNodes.length-1;i>=0;i--){
            if(kvNode==previousNodes[i].next[i]){
                previousNodes[i].next[i]=kvNode.next[i];
                if(head.next[i]==null){
                    maxLevel--;
                }
            }
        }
        return kvNode.val;
    }
    /**
     * 查找
     * @param key
     * @return
     */
    public V search(K key){
        Node<K,V> node=genericSearch(key,null);
        return node==null?null:node.val;
    }
    /**
     * 通用查询的实现
     * @param key           key
     * @param previousNode  前继节点
     * @return 存在返回Node，不存在返回null
     */
    private Node<K,V> genericSearch(K key,Node<K,V>[] previousNode){
        Node<K,V> q=null,p=this.head;
        if(previousNode==null){
            previousNode=new Node[maxLevel];
        }
        //逐层向下查找，并更新前驱节点，为插入节点做准备
        for(int level=maxLevel-1;level>=0;level--){
            while((q=p.next[level])!=null&&q.compareKey(key)<0){
                p=q;
            }
            //更新前驱节点
            previousNode[level]=p;
        }
        //未找到
        if(q==null||q.compareKey(key)>0){
            return null;
        }
        return q;
    }

    /**
     * 节点
     * @param <K>
     * @param <V>
     */
    static class Node<K extends Comparable<K>,V>{
        K key;
        V val;
        Node<K,V>[] next;
        private Node(){
            this.next=new Node[MAX_VALUE];
        }
        public Node(K key,V val,int level){
            this.key=key;
            this.val=val;
            this.next=new Node[level];
        }
        public int compareKey(K k1){
            return this.key.compareTo(k1);
        }
    }
}
