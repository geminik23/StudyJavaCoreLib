package properties;

import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.Writer;
import java.io.IOException;
import java.io.Reader;

public class Example1{
 
    public static void main(String[] args){
        readProperties();
        writeProperties();
       
    }

    private static void readProperties(){
        Properties prop = new Properties();
        try(Reader reader = Files.newBufferedReader(Paths.get("properties/res/sometxt.properties"))){
            prop.load(reader);
        }catch(IOException e){}
        String value1 = prop.getProperty("key1"); // hello
        String value2 = prop.getProperty("key2"); // it's collon
        String value3 = prop.getProperty("key3"); // how about this?
        String value4 = prop.getProperty("key4"); // some value

        System.out.println(String.format("%s\n%s\n%s\n%s", value1, value2, value3, value4));
    }

    private static void writeProperties(){
        Properties prop = new Properties();
        prop.setProperty("name", "Jaemin Kim");
        prop.setProperty("PhoneNumber", "000-0000-00000");

        try(Writer writer = Files.newBufferedWriter(Paths.get("properties/res/result1.properties"))){
            prop.store(writer, "some comment");
        }catch(IOException e){
        }
    }

    

    
}