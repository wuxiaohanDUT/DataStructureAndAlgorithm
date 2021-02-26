package BlockQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue<T> {

    //最大容量和队列数组
    private int capacity;
    private Object[] queue;

    //锁和等待条件
    private ReentrantLock lock;
    private Condition empty;
    private Condition full;

    //添加、删除元素的下标 当前队列的大小
    private int removeIndex;
    private int index;
    private volatile int size;

    public BlockQueue(){
        this(10);
    }

    public BlockQueue(int capacity){
        this.capacity=capacity;
        this.size=0;
        this.removeIndex=0;
        this.index=0;
        queue=new Object[capacity];
        lock=new ReentrantLock();
        empty=lock.newCondition();
        full=lock.newCondition();
    }

    /**
     * 插入元素,保证线程安全
     * @param element
     * @throws InterruptedException
     */
    public void push(T element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            try{
                while (size==capacity){
                    full.await();
                }
            }catch (InterruptedException e){
                full.signal();//线程中段，节点放弃等待，唤醒之后的线程
                throw e;
            }
            insert(element);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 插入元素，并且唤醒empty条件队列中的等待线程
     * @param element
     */
    private void insert(T element){
        ++size;
        queue[index++]=element;
        if(index==capacity){
            index=0;
        }
        empty.signal();
    }

    /**
     * 移除元素，保证线程安全
     * @return
     * @throws InterruptedException
     */
    public T pop() throws InterruptedException{
        lock.lockInterruptibly();
        T ans;
        try {
            try{
                while (size==0){
                    empty.await();
                }
            }catch (InterruptedException e){
                empty.signal();
                throw e;
            }
            ans=remove();
        }finally {
            lock.unlock();
        }
        return ans;
    }

    /**
     * 移除元素
     * @return
     */
    private T remove(){
        size--;
        T ans=(T)queue[removeIndex];
        ++removeIndex;
        if(removeIndex==capacity){
            removeIndex=0;
        }
        full.signal();
        return ans;
    }
}
