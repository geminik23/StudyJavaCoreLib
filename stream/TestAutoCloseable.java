package stream;

import java.lang.Exception;
import java.io.IOException;


public class TestAutoCloseable  implements AutoCloseable{

    @Override
    public void close() throws IOException {
        // throw new IOException("close exception");
        System.out.println("TestAutoCloseable : close()");
    }


    public void doSomthing() throws IOException{
        // throw new IOException("dosomething exception");
        System.out.println("TestAutoCloseable : doSomething()");
    }
    

}