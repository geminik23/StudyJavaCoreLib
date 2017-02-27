Properties 
================================
 use to persist the properties. and read&write from streams (support OutputStream / InputStream and Writer / Reader)

 Properties has two format
    - Plain Text
    - XML

 Plain Text
 -------------------------------

  - key and value pair per line. using separator (=, :, whitespace) => can be used with escape in text

  - start with comments (# or !) 

  ```
  somekey = hello words
  somekey : hello words
  somekey hello words
  ```

  Store Properties to Text
  ```java
  Properties prop = new Propeties();
  prop.setProperty("name", "Jaemin Kim");
  prop.setProperty("PhoneNumber", "000-0000-0000");

  try(Writer writer = Files.newBufferedWriter(Paths.get("some.properties"))){
      prop.stor(writer, "some comments");
  }
  ```
  above code results

  ```txt
  #some comments
  #Tue Feb ......(Date Info)
  name=Jaemin Kim
  PhoneNumber=000-0000-00000
  ...


  Load Properties from Text
  ```txt
  key1 = hello
  key2 : it's collon
  key3 how about this?
  key4 = some values
  ```

  ```java
Properties prop = new Properties();
try(Reader reader = Files.newBufferedReader(Paths.get("my Properties")){
    prop.load(reader);
}
String value1 = prop.getProperty("key1"); // hello
String value2 = prop.getProperty("key2"); // it's collon
String value3 = prop.getProperty("key3"); // how about this?
String value4 = prop.getProperty("key4"); // some value
  ```

[Example1 - plain text Properties](Example1.java)


 XML
 -------------------------------
