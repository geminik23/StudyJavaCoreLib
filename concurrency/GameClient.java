package concurrency;


public class GameClient implements Runnable{
    private GameScore score;
    public GameClient(GameScore score){
        this.score = score;
    }

    public void run(){
        for(int i=0;i<10;++i){
            int prev = score.getScore();
            score.addScore(100);
            System.out.println(String.format("%d after %d", prev, score.getScore()));
        }
    }

}