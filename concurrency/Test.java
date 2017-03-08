package concurrency;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;



public class Test{

    static String[] InFiles = {"./concurrency/res/file1.txt", "./concurrency/res/file2.txt", "./concurrency/res/file3.txt", "./concurrency/res/file4.txt"};
    static String[] OutFiles = {"./concurrency/res/out_file1.txt", "./concurrency/res/out_file2.txt", "./concurrency/res/out_file3.txt", "./concurrency/res/out_file4.txt"};
    
    public static void main(String[] agrs)
    {
        CreateExampleFiles();
        Example1();
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









    //======================================================================================================
    static void CreateExampleFiles(){
        for(String f:InFiles){
            GenerateRandomInteger(f);
        }    
    }

    static void GenerateRandomInteger(String fn){
        Random random = new Random();
        int count = (80+random.nextInt(20));
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(fn))){
            for(int i=0;i<count;++i){
                writer.write(""+random.nextInt(3000));
                writer.newLine();
            }
        }catch(IOException e){}    
        
    }

}