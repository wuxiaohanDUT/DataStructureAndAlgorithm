package BlockQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue<T> {

    private int capacity;
    private Object[] queue;

    private ReentrantLock lock;
    private Condition empty;
    private Condition full;

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

    public void push(T element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while(size==capacity){
                full.wait();
            }
            ++size;
            queue[index++]=element;
            if(index==capacity){
                index=0;
            }
        }catch (InterruptedException e){
            full.signal();
            throw e;
        }finally {
            lock.unlock();
        }
    }

    public T pop() throws InterruptedException{
        lock.lockInterruptibly();
        Object ans=null;
        try {
            while(size==0){
                empty.wait();
            }
            --size;
            ans=queue[removeIndex++];
            if(removeIndex==capacity){
                removeIndex=0;
            }
        }catch (InterruptedException e){
            empty.signal();
            throw e;
        }finally {
            lock.unlock();
        }
        return (T)ans;
    }
}
