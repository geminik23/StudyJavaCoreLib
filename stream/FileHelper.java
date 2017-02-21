package stream;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileHelper{
    public static Reader OpenReader(String filename) throws IOException{
        return Files.newBufferedReader(Paths.get(filename));
    }

    public static Writer OpenWriter(String filename) throws IOException{
        return Files.newBufferedWriter(Paths.get(filename));
    }

}