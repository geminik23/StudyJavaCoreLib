package concurrency;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;




public class Test{

    static String[] InFiles = {"./concurrency/res/file1.txt", "./concurrency/res/file2.txt", "./concurrency/res/file3.txt", "./concurrency/res/file4.txt"};
    static String[] OutFiles = {"./concurrency/res/out_file1.txt", "./concurrency/res/out_file2.txt", "./concurrency/res/out_file3.txt", "./concurrency/res/out_file4.txt"};
    
    public static void main(String[] agrs)
    {
        CreateExampleFiles();
        // Example1();
        // Example2();
        // Example3();
        Example4();
    }



    /**
     * Single Thread Task
     */
    static void Example1(){
        try{
            for(int i=0;i<InFiles.length;++i)
                SingleSum.Do(InFiles[i], OutFiles[i]);
        }catch(IOException e){}
        

    }

    static void Example2(){
        
        for(int i=0;i<InFiles.length;++i){
            RunnableSum sum = new RunnableSum(InFiles[i], OutFiles[i]);
            Thread thread = new Thread(sum);
            thread.start();
            
        }

    }

    static void Example3(){
        Thread[] threads = new Thread[InFiles.length];
        for(int i=0;i<inFiles.length;++i)
        {
            RunnableSum sum = new RunnableSum(InFiles[i], OutFiles[i]);
            threads[i] = new Thread(sum);
            threads[i].start();
        }

        for(Thread thread:threads){
            thread.join();
        }
    }

    static void Example4(){
        ExecutorService es = Executors.newFixedThreadPool(3);
        
        for(int i=0;i<inFiles.length;++i){
            RunnableSum sum = new RunnableSum(InFiles[i], OutFiles[i]);
            es.submit(sum);

        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        }catch(Exception e){}
    }


    //======================================================================================================
    static void CreateExampleFiles(){
        for(String f:InFiles){
            GenerateRandomInteger(f);
        }    
    }

    static void GenerateRandomInteger(String fn){
        Random random = new Random();
        int count = (1080+random.nextInt(20));
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(fn))){
            for(int i=0;i<count;++i){
                writer.write(""+random.nextInt(3000));
                writer.newLine();
            }
        }catch(IOException e){}    
        
    }

}