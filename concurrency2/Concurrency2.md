# DeadLocks

```java

public class T{
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public void method1(){
        synchronized(lock1){
            System.out.println(String.format("[thread : %s] method1()"));
            method2();
        }
    }

    public void method2(){
        synchronized(lock2){
            System.out.println(String.format("[thread : %s] method2()"));
            method3();
        }
    }

    public void method3(){
        synchronized(lock1){
            System.out.println(String.format("[thread : %s] method3()"));
        }
    }
}

```

[Example](./Test0.java)


# Wait/Notify pattern
## Producer/Consumer problem

```java



Object lock;
byte[] buffer = new buffer[MAX];
int offset = 0;

public boolean isFull(){
    
}

public boolean isEmpty(){

}

public class Producer{    
    public void produce(){
        synchronized(lock){
            while(isFull()){}
            buffer[offset++] = 1;
        }
    }
}

public class Consumer{
    public void consumer(){
        synchronized(lock){
            while(isEmpty()){}
            buffer[--offset] = 0;
        }
    }
}

```

[Example](./Test1.java)

## wait()/ notify()
-  methods from the Object Class
-  this methods are invoked inside a synchronized block.

### Calling wait() 
-   release the key held by this thread
-   state turns WAIT state.

### Calling notify()
-   release a thread in WAIT state.
-   state turns RUNNABLE state.
-   notifyAll()
-   after change RUNNABLE, choose randomly next thread.


```java

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

```



# Thread State
If thread is in Wait list, then thread Scheduler give it a hand? NO

- New
- Runnable 
- From Runnable 
    * Blocked : Waiting at the entrance of synchronized block.
    * Wait : Parked using a wait() call
    * Timed_Waiting : Parked using a sleep(timeout) or wait(timeout) call
- Terminated

## When back to runnable
-  Blocked : when key is released.
-  Waiting : when the notify() method is called.



```java

Thread t  = new Thread(()->{});
Thread.State state = t.getState();

```