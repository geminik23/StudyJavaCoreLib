package serialization;

import java.io.Serializable;




public class UserInfo2 implements Serializable{
    private static final long serialVersionUID = -3261485661159361586L;
    
    private final String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    
    public UserInfo2(String uid){
        this.userId = uid;
    }
    public UserInfo2(String uid, int max, int current) {
        this.userId = uid;
        this.maxScore = max;
        this.setScore(current);
    }
    public void setScore(int score){
        this.currentScore = score;
        this.maxScore = Math.max(maxScore, score);
    }

    @Override
    public String toString(){
        return String.format("UserId : %s , Max Score : %d, Current Score : %d", 
        this.userId, this.maxScore, this.currentScore);
    }
}


/** Updated */
/*
public class UserInfo2 implements Serializable{
    private static final long serialVersionUID = -3261485661159361586L;
    
    private final String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    private int gameCount;
    
    public UserInfo2(String uid){
        this.userId = uid;
        this.gameCount = 0;
    }
    public UserInfo2(String uid, int max, int current) {
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

    @Override
    public String toString(){
        return String.format("UserId : %s , Max Score : %d, Current Score : %d, Total Game Count : %d", 
        this.userId, this.maxScore, this.currentScore, this.gameCount);
    }
}
*/