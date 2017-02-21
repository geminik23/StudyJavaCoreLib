package stream;

import java.io.IOException;

public class Example2{
    public static void main(String[] args){

        try(TestAutoCloseable test = new TestAutoCloseable()){
			test.doSomthing();
		}catch(IOException e){
            
			System.out.println(String.format("%s - %s", e.getClass().getSimpleName(), e.getMessage()));
			
            for(Throwable t : e.getSuppressed()){
				System.out.println("Suppresed " + t.getMessage());
			}
		}

    }


}