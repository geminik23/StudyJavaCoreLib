Concurrency Basics
===============================

Process
-------------------------------
 - Instance of Program.
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


```java
ExecutorService es = Executors.newFixedThreadPool(3); // max 3 threads

for(int i=0;i<inFiles.length;++i){
    RunnableSum sum = new RunnableSum(InFiles[i], OutFiles[i]);
    es.submit(sum);
}

try{
    es.shutdown();
    es.awaitTermination(60, TimeUnit.SECONDS);
}catch(Exception e){}
```

[Example4 - FixedThreadPool] (Test.java)



Thread Result Handling
=========================================
 -  Manual handling ( reulst & exception ) using memory
  > Conception Diagram
 -  Using Threading Relation
  > Conception Diagram

Thread Relationship Types
=========================================
 - Callable interface 
    * can return results
    * can throw exceptions
    * call() method

 - Future interface
    * it represents results of thread task
    * returned by ExecutorService.submit
    * get() method (acting like join)
    * return Callable return type
    * throw Callable exception type

```java
...

public class CallableSum implements Callable<Integer> {
    private String inFile, outFile;
    public CallableSum(String in, String out){ inFile = in; outFile = out;}
    
    public Integer call() throws IOException
    {
        return SingleSum.DoNReturn(inFile, outFile);
    }
}

```
[Example5 - Callable & Future] (Test.java)


Concurrency Problem - Sharing Resources
===========================================

```java

public class GameScore{
    private int score;
    public GameScore(int initScore){
        this.score = initScore;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(int score){
        this.score += score;
    }
}


public class GameClient implements Runnable{
    private GameScore score;
    public GameClient(GameScore score){
        this.score = score;
    }

    public void run(){
        int prev = score.getScore();
        score.addScore(100);
        int after = score.getScore();
        System.out.println(String.format("%d after %d", prev, after));
    }

}

```

[Example6 - Sharing Resources Issues ](Test.java)

> Concept Diagram

[Example6 - remove comments](Test.java)


Lock The Thread Access To Method
=====================================
 - Synchronized methods
 - Synchronize per one instence
    * one thread can access to method at a time.

 Why use synchronized methods
 ---------------------------
 - protect modification by multiple threads
 - read value that might be modified by another thread
 - cons 
    * overhead
    * only for multithreading
 - all methods but not constructor


[Example6 - adding synchronized](Test.java)


Manual Synchronization
=====================================
 - manually lock the object with synchronized statement block.



[Example7 - GameClient2, remove synchronized](Test.java)


Why use synchronized statment blocks
-----------------------------
 - provide Flexibility
    * non-thread safe classes
    * protect complex blocks of code
    * synchronized methods just are not enough


[Example8 - AdvancedGameClient -> Synchronized Block](Test.java)