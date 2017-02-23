package serialization;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;



public class Example2{
    public static void main(String[] args){
        if(args.length<=1){
            help();
            return;
        }

        UserInfo2 ui = null;

        switch(args[0]){
            case "--open":
                ui = LoadInfo(Paths.get(args[1]));
                System.out.println(ui);
            break;
            case "--save":
                ui = new UserInfo2("Geminik", 1430, 1100);
                SaveInfo(Paths.get(args[1]), ui);
            break;
            default:
            System.out.println("Wrong arguments");
            return;
        }
    }

    private static UserInfo2 LoadInfo(Path path)
    {
        UserInfo2 ui = null;
        try(ObjectInputStream  ois = new ObjectInputStream(Files.newInputStream(path))){
            ui = (UserInfo2)ois.readObject();
        }catch(InvalidClassException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }catch(ClassNotFoundException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
        return ui;
    }

    private static void SaveInfo(Path path, UserInfo2 info)
    {   
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            if(info !=null)
                oos.writeObject(info);
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }


    private static void help(){
        System.out.println("WARNING!! - need argsments");
        System.out.println("   --save [FILENAME]");
        System.out.println("   --open [FILENAME]");
    }

}