package concurrency;


public class GameClient2 implements Runnable{
    private GameScore score;
    public GameClient2(GameScore score){
        this.score = score;
    }

    public void run(){
        for(int i=0;i<10;++i){
            synchronized(score){
                int prev = score.getScore();
                score.addScore(100);
                System.out.println(String.format("%d after %d", prev, score.getScore()));   
            }
        }
    }

}