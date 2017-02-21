package stream;


import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public class Example3{

    public static void main(String[] args){
        // readTxtFile();
        // writeLines();
        readLines();
    }

    public static void readTxtFile(){
        try(BufferedReader br = Files.newBufferedReader(Paths.get("res/file1.txt"))){ 
            String str;
			while((str = br.readLine())!=null){
				System.out.println(str);
			}
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    public static void writeLines(){
        String[] data = {"line1", "line2", "line3", "line4"};
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("res/result.txt"))){
            for(String line: data){
                writer.write(line);
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    public static void readLines(){
        try{
            List<String> lines =  Files.readAllLines(Paths.get("res/result.txt"));

            for(String line:lines){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));            
        }
    }
}