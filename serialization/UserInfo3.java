package serialization;

import java.io.Serializable;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserInfo3 implements Serializable{
    private static final long serialVersionUID = -3261485661159361586L;
    
    private String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    private int gameCount;
    
    public UserInfo3(String uid){
        this.userId = uid;
        this.gameCount = 0;
    }
    public UserInfo3(String uid, int max, int current) {
        this.userId = uid;
        this.maxScore = max;
        this.gameCount = 0;
        this.setScore(current);
    }
    public void setScore(int score){
        this.currentScore = score;
        this.maxScore = Math.max(maxScore, score);
        this.gameCount++;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream.GetField fields = in.readFields();
        this.userId = (String) fields.get("userId", null);
        this.maxScore = fields.get("maxScore", 1000);
        this.currentScore = fields.get("currentScore", -1);
        this.gameCount = fields.get("gameCount", -1);
    }


    @Override
    public String toString(){
        return String.format("UserId : %s , Max Score : %d, Current Score : %d, Total Game Count : %d", 
        this.userId, this.maxScore, this.currentScore, this.gameCount);
    }
}