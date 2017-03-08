package concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;




public class SingleSum{
    public static void Do(String in, String out) throws IOException{
        int sum = 0;
        String temp = null;
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(in)))
        {
            while((temp = reader.readLine())!=null)
                sum += Integer.parseInt(temp);
        }

        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(out)))
        {
            writer.write(String.format("Result of '%s' is %d", in, sum));
        }
    }
}