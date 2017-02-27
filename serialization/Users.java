package serialization;


import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.Serializable;
import java.io.ObjectInputStream;

import java.util.HashSet;
import java.util.Set;


public class Users implements Serializable{

    private Set<UserInfo> users = new HashSet<>();
    private transient int maxScore = 0;
    
    
    public int getMaxScore(){return maxScore;}
    public void addUser(UserInfo info){
        this.users.add(info);
        this.maxScore = Math.max(this.maxScore, info.getMaxScore());
    }

    // void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
    //     in.defaultReadObject();
    //     for(UserInfo ui: users){
    //         this.maxScore = Math.max(this.maxScore, ui.getMaxScore());
    //     }
    // }

    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        for(UserInfo ui: users){
            sb.append(ui);
            sb.append("\n");
        }

        return String.format(
            "Total Users : %d\nMaxScore : %d\n%s",
            users.size(), this.maxScore, sb.toString() 
        );
    }

}