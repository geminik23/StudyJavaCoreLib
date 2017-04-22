package annotation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activity.InvalidActivityException;

public class Test{


    // @SuppressWarnings("deprecation")  
    public static void DeprecateTest(){
        new DeprecatedClass().DoThis();
    }


    public static void AccessingAnnotation(){
        ExecutorService es = Executors.newFixedThreadPool(3);
        Class<?> workerType = Worker.class;

        try{
            Worker worker = (Worker) workerType.newInstance();
            WorkHandler wh = (WorkHandler)workerType.getAnnotation(WorkHandler.class);
            
            if(wh==null) throw new InvalidActivityException("Not Implemented Annotation");

            if(wh.use()){
                es.submit(new Runnable(){
                    public void run(){
                        System.out.println("In thread : " + Thread.currentThread().getId());
                        worker.doWork();
                    }
                });
            }else{
                worker.doWork();
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        es.shutdown();
    }

    // @SuppressWarnings("deprecation")
    public static void main(String[] args){
        // DeprecateTest();
        AccessingAnnotation();
    }
}