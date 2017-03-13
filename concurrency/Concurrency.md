Concurrency Basics
===============================

Process
-------------------------------
 - Instance of Program or Application.
 - Has Resources memory.
 - Has at least one thread!

Thread
-------------------------------
 - Sequences of program instructions
 - Thread can create another threads (multithread)

>Concept Diagram (Process with Memory and Main Thread)

Multithreading
===============================
 - Enable to use complete CPU
    - for non CPU tasks
    - Multiple CPU Cores ( allows to run parallel)
 - Why use?
    - reduce excution times

```java

static void Example1(){
    try{
        for(int i=0;i<InFiles.length;++i)
            SingleSum.Do(InFiles[i], OutFiles[i]);
    }catch(IOException e){}
}

```

[Example1 - Single Thread file process](Test.java)

>Concept Diagram

Move to Multithreading
-------------------------------
 - Break the problem into indepedent parts



Java Threading Foundation 
===============================
 - Each thread handle own exceptions

Threading Foundation Types
-------------------------------

 - Runnable interface
    - only run() method (to be run on a thread)
 - Thread class
    - interact with thread states
    - begin with start() method


```java

public class RunnableSum implements Runnable{

    private String inFile, outFile;
    public RunnableSum(String in, String out){ inFile = in; outFile = out;}
    
    public void run()
    {
        try{
            SingleSum.Do(inFile, outFile);
        }catch(IOException e){

        }
        
    }
}
```
[Example2 - Running separate Threads](Test.java)

> Concept Diagram (Main Thread problem)

 join() method // waiting for thread completion.

[Example3 - use join()](Test.java)



Thread Management with Thread Pools
=======================================
 - Problems
    * responsible directly startup, shutdown.
    * Overhead
    * OutOfMemory 
 - ThreadPools
    * queue for tasks
    * handle threads 
 
 Thread Pool Types
 --------------------------------------
 - ExecutorService Interface
    * Models thread pool behavior
    * submit tasks
    * Request and wait 

 - Executors Class
    * Methods for Creating thread pools
        - Dynamically sized pools
        - Size limited pools
        - Schedule tasks for later

[Example4 - FixedThreadPool] (Test.java)


Thread Result Handling
=========================================
 -  manual handling

 -  exception handling

