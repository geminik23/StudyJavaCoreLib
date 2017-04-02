package concurrency2;

import com.sun.media.jfxmedia.events.NewFrameEvent;

public class Test2{
    public static void main(String[] arg){
        new Test2();
    }

    
    public Test2(){
        Producer pro = new Producer();
        Consumer con = new Consumer();

        Thread t1 = new Thread(()->{
            for(int i=0;i<1000;++i){
                pro.produce();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<1000;++i){
                con.consumer();
            }
        });

        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();

        }catch(InterruptedException e){
        }
        System.out.println(String.format("Current Size is %d", offset));
    }



    final static int MAX = 10000;


    Object lock = new Object();
    byte[] buffer = new byte[MAX];
    int offset = 0;

    public boolean isFull(){
        return offset >= MAX;   
    }

    public boolean isEmpty(){
        return offset == 0;
    }

    public class Producer{    
        public void produce(){
            synchronized(lock){
                if(isFull()){
                    try{
                       lock.wait();    
                    }catch(InterruptedException e){}
                }
                buffer[offset++] = 1;
                lock.notify();
            }
        }
    }

    public class Consumer{
        public void consumer(){
            synchronized(lock){
                if(isEmpty()){
                    try{
                        lock.wait();
                    }catch(InterruptedException e){}
                }
                buffer[--offset] = 0;
                lock.notify();
            }
        }
    }

}