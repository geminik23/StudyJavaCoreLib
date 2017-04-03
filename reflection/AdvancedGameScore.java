package reflection;


public final class AdvancedGameScore extends GameScore implements Runnable{
    protected String playerId;

    public AdvancedGameScore(String playerId){ this.playerId = playerId;}
    public AdvancedGameScore(String playerId, int initScore){this.playerId = playerId; this.score = initScore;}

    public void run(){
        //.... do something
        int count = 10;
        System.out.println(String.format("Player : %s", this.playerId));
        do{
            this.addScore(100);
            System.out.println(String.format("Score is %d", this.score)); 
        }while(count-->0);
    }
}