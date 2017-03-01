package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;





public class Example2{

    public static void main(String[] args){
        try{
            Properties defaultProp = new Properties();
            try(InputStream is = Example2.class.getResourceAsStream("myDefault.properties")){
                defaultProp.load(is);
            } 
            
            Properties prop = new Properties(defaultProp);
            String name = prop.getProperty("Name");
            String number = prop.getProperty("PhoneNumber");

            System.out.println(String.format("%s, %s", name, number));

        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}