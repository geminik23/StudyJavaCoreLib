package serialization;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;


public class UserInfo implements Serializable{
    private final String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    // private int gameCount;
    
    public UserInfo(String uid){
        this.userId = uid;
    }
    public UserInfo(String uid, int max, int current) {
        this.userId = uid;
        this.maxScore = max;
        this.currentScore = current;
    }
    public void setScore(int score){
        this.currentScore = score;
        this.maxScore = Math.max(maxScore, score);
    }

    public int getMaxScore(){return this.maxScore;}
    public int getCurrentScore(){return this.currentScore;}
    

    @Override
    public String toString(){
        return String.format("UserId : %s , Max Score : %d, Current Score : %d", this.userId, this.maxScore, this.currentScore);
    }
}



