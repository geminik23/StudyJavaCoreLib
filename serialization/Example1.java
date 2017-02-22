package serialization;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Example1{
    public static void main(String[] args){
        if(args.length<=1){
            help();
            return;
        }

        UserInfo ui = null;

        switch(args[0]){
            case "--open":
                ui = LoadInfo(Paths.get(args[1]));
                System.out.println(ui.toString());
            break;
            case "--save":
                ui = new UserInfo("Geminik", 1430, 1100);
                SaveInfo(Paths.get(args[1]), ui);
            break;
            default:
            System.out.println("Wrong arguments");
            return;
        }
    }

    private static UserInfo LoadInfo(Path path)
    {
        UserInfo ui = null;
        try(ObjectInputStream  ois = new ObjectInputStream(Files.newInputStream(path))){
            ui = (UserInfo)ois.readObject();
        }catch(IOException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }catch(ClassNotFoundException e){
            System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
        }
        return ui;
    }

    private static void SaveInfo(Path path, UserInfo info)
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