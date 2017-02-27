package serialization;

import serialization.UserInfo;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;




public class Example3{


    public static void main(String[] args){
        if(args.length == 0) return; 
        
        String file = "res/users.dat";
        Users users = null;
        switch(args[0]){
            case "--save":
            users = new Users();
            users.addUser(new UserInfo("John"));
            users.addUser(new UserInfo("Max", 1900, 300));
            users.addUser(new UserInfo("Jane", 2180, 1930));
            saveUsers(users, file);   
            System.out.println("user datas saved"); 
            break;
            case "--open":
            users = loadUsers(file);
            System.out.println(users);
            break;
            default:
            System.out.println("typed wrong options");
            break;
        }
    }   


    public static Users loadUsers(String path){
        Users users = null;

        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(path)))){
            users = (Users)ois.readObject();
        }catch(IOException e){
        }catch(ClassNotFoundException e){
        }

        return users;
    }

    public static void saveUsers(Users users, String path){
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(path)))){
            oos.writeObject(users);
        }catch(IOException e){
        }
    }

}