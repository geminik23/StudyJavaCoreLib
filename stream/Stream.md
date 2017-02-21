# Stream


 - Byte Streams (Binary)
    * InputStream 
    * OuputStream

 - Char Streams (Text)
    * Reader
    * Writer

 - package java.io


1. InputStream & Reader
 
 ``` java
 /* InputStream*/
 int read(); // return -1 if no data
 int read(byte[] buff); // return length
 
 /* Reader */
 int read(); // return -1 if no data
 int read(char[] buff); // return length
 ``` 


 ### Example
 ``` java

public static void example(InputStream input, Reader reader){
    /* read() */
    int retval;
    while((retval = input.read()) != -1){
        byte b = (byte)retval;
        // do something
    }

    while((retval = reader.read())!=-1){

        char c = (char)retval;
        // do something
    }

    /* read([]) */
    int length;
    char[] charBuff = new char[10];
    byte[] byteBuff = new byte[10];

    while((length = input.read(charBuff))>=0){
        //do something
    }
    

    while((length = input.read(charBuff))>=0){
        //do something
    }   
}
```


## 2. OutputStream & Writer
 
 ``` java
 /* OutputStream */
 void write(int v);
 void write(byte[] buff);
 
 /* Writer */
 void write(int v);
 void write(char[] buff);
 void write(String str);
 ``` 

 ### Example
 ``` java
public static void example(OutputStream output){
    byte byteVal = 100;
    output.write(btyeVal);

    byte[] byteBuff = {0, 12, 101};
    output.write(byteBuff);
}

 ```


## 3. Classes
 * InputStream 
    - ByteArrayInputStream
    - PipedInputStream
    - FileInputStream

 * Reader
    - CharArrayReader
    - StringReader
    - PipedReader
    - InputStreamReader
        - FileReader


## 4. Error and Clean
 * IOException
 
 * Closeable interface
 
 ``` java
 void close() throws IOException
 ```
 

1. Error Handling
 ```java

 public static void example(){
     Reader reader;
     try{
        reader = // open
     }catch(IOException e){
        // handle exception
     }finally{
         try{
             if(reader!=null) 
                reader.close();
         }catch(IOException e){
             // handle exception
         }
     }
 }

 ```

2. Try-with-resource
 * AutoCloseable interface

``` java
 public static void example(){
     try(Reader reader = /*open*/){
         // do something with reader
     }catch(IOException e){
        // handle exception
     }
 }
```

:::[EXAMPLE1 - Try with Resource](Example1.java):::

:::[EXAMPLE2 - AutoCloseable](Example2.java):::


## 5. Chaining Stream
 with construtors


```java
public static void chainExample(InputStream in){
    int length;
    char[] charBuff = new char[100];

    try(InputStreamReader isr = new InputStreamReader(in)){
        while((length = isr.read(charBuff))>=0){
            //do something
        }
    }catch(IOException e){}
}
```


## 6. Handling File

normally use stream for file data i/o.

FileXXXXXX Classes !!!DEPRECATED!!!



### BufferedStream : for Efficiency!
 - BufferedReader
 - BufferedWriter
 - BufferedInputStream
 - BufferedOutputStream

 ```java
try(BufferedReader br = new BufferedReader(new FileReader("res/file1.txt"))){ // !!! Deprecated
    int intVal;
    while((intVal = br.read() >=0)){
        char charVal = (char) intVal;
        System.out.println(charVal);              
    }
}
 ```

 ```java
String[] data = {"line 1", "line 2", "line 3", "line4"};
try(BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))){  // !!! Deprecated
    for(String d:data){
        bw.write(d);
        bw.newline();
    }
}
 ```


 ```java
try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
    String inValue;
    while((inValue = br.readLine())!=null){
        System.out.println(inValue);
    }
}
 ```


## 7. java.nio.file
 alternative of FileXXXX

* Path

* Paths

```java
Path p1 = paths.get("res/file1.txt");
Path p2 = paths.get("res","files.txt");

```

* Files
 - Create, Copy, Delete ...
 - open Streams
    * newBufferedReader
    * newBufferedWriter
    * newInputStream
    * newOuputStream 

 - Read/Write file content
    * readAllLines
    * write


:::[EXAMPLE3 - BuffredStream from Files](Example3.java)





## FileSystem
 
  * FileSystem 
    - default file system (Paths)

  * FileSystems
    - newFileSystems(URI, Path)
    - Zip File System 
        * scheme "jar:file" + data.txt
        * ex) jar:file:/data.txt



```java
/* DefaultFileSystem */
Path p = Paths.get("res/file1.txt");
Path p2 = FileSystems.getDefault().getPath("file1".txt);
```


:::[EXAMPLE4 - ZIP FileSystems from Files](Example4.java)
  