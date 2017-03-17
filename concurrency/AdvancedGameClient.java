package concurrency;

public class AdvancedGameClient implements Runnable{
    GameScore score;
    int amount;
    
    public AdvancedGameClient(GameScore score, int amount){
        this.score = score;
        this.amount = amount;
    }

    public void run(){
        int before = score.getScore();
        score.addScore(this.amount);
        if(amount>500){
            int current = score.getScore();
            score.addScore((int)Math.round(current * 0.01));
        }
    }

}