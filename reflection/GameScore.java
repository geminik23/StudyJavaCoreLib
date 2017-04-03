package reflection;

public class GameScore{
    public int score;

    public GameScore(){ this.score = 0;}
    public GameScore(int initScore){
        this.score = initScore;
    }

    public synchronized int getScore(){
        return this.score;
    }

    public synchronized void addScore(int score){
        this.score += score;
    }
}