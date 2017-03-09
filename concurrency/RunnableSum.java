package concurrency;


import java.io.IOException;
public class RunnableSum implements Runnable{

    private String inFile, outFile;
    public RunnableSum(String in, String out){ inFile = in; outFile = out;}
    
    public void run()
    {
        try{
            SingleSum.Do(inFile, outFile);

        }catch(IOException e){}
    }
}