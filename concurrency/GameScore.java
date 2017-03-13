package concurrency;

public class GameScore{
    private int score;
    public GameScore(int initScore){
        this.score = initScore;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(int score){
        this.score += score;
    }
}