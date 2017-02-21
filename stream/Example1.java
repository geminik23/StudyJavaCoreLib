package stream;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedWriter;


public class Example1{

    public static void main(String[] args){
        tryCatchClose();
		tryWithResources();
    }

    public static void tryCatchClose(){
        char[] buff = new char[8];
		int length;
		Reader reader = null;

		try {
			reader = FileHelper.OpenReader("res/file1.txt");
			while((length = reader.read(buff))>=0){
				System.out.println("\nlength: " + length);
				for(int i=0;i<length;++i)
					System.out.println(buff[i]);
			}
		} catch (IOException e1) {
			System.out.println(String.format("%s - %s", e1.getClass().getSimpleName(), e1.getMessage()));
		} finally{
			try{
				if(reader != null)
					reader.close();
			}catch(IOException e2){
				System.out.println(String.format("%s - %s", e2.getClass().getSimpleName(), e2.getMessage()));
			}
		}
    }

	public static void tryWithResources(){
		char[] buff = new char[8];
		int length;

		try (
			Reader reader = FileHelper.OpenReader("res/file1.txt");
			Writer writer = FileHelper.OpenWriter("res/file2.txt")
			){
			while((length = reader.read(buff))>=0){
				writer.write(buff, 0, length);
			}
		} catch (IOException e1) {
			System.out.println(String.format("%s - %s", e1.getClass().getSimpleName(), e1.getMessage()));
		}
	}

    

}