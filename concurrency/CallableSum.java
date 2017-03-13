package concurrency;

import java.util.concurrent.Callable;
import java.io.IOException;

public class CallableSum implements Callable<Integer> {
    private String inFile, outFile;
    public CallableSum(String in, String out){ inFile = in; outFile = out;}
    
    public Integer call() throws IOException
    {
        
        return SingleSum.DoNReturn(inFile, outFile);
    }
}