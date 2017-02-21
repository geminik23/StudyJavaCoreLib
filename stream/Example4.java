package stream;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

import java.net.URI;
import java.net.URISyntaxException;

public class Example4{
    
    public static void main(String[] args){
        Path zipPath = Paths.get("res/data.zip");
        String[] dd = {"1","2","3"};
        try(FileSystem zipFS = OpenZip(zipPath)){
            Files.write(zipFS.getPath("aa.txt"),  Arrays.asList(dd));
        }catch(Exception e){

        }
    }

    public static FileSystem OpenZip(Path zipPath) throws IOException, URISyntaxException{
        Map<String, String> prop = new HashMap<String, String>();
        prop.put("create", "true");
        URI uri = new URI("jar:file", zipPath.toUri().getPath(), null);

        return FileSystems.newFileSystem(uri, prop);
    }
}