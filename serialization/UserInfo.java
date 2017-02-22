package serialization;

import java.io.Serializable;


public class UserInfo implements Serializable{
    private final String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    
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

    @Override
    public String toString(){
        return String.format("UserId : %s , Max Score : %d, Current Score : %d", this.userId, this.maxScore, this.currentScore);
    }
}