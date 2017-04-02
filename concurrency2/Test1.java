package concurrency2;

import com.sun.media.jfxmedia.events.NewFrameEvent;

public class Test1{
    public static void main(String[] arg){
        new Test1();
    }

    
    public Test1(){
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
            // synchronized(lock){
                while(isFull()){}
                buffer[offset++] = 1;
            // }
        }
    }

    public class Consumer{
        public void consumer(){
            // synchronized(lock){
                while(isEmpty()){}
                buffer[--offset] = 0;
            // }
        }
    }

}