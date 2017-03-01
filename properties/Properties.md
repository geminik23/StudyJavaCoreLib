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
  prop.setProperty("Name", "Jaemin Kim");
  prop.setProperty("PhoneNumber", "000-0000-0000");

  try(Writer writer = Files.newBufferedWriter(Paths.get("some.properties"))){
      prop.store(writer, "some comments");
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

 use .xml File

 method storeToXML & loadFromXML (OutputStream/InputStream)

 One element in XML <-> One Key/value


 Store Properties
 ```java
  Properties prop = new Propeties();
  prop.setProperty("Name", "Jaemin Kim");
  prop.setProperty("PhoneNumber", "000-0000-0000");

  try(outputStream out = Files.newOutputStream(Paths.get("some.xml"))){
    prop.storeToXML(out, "Some Comment");
  }
  ...
 ```

 result 
 ```xml
 <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
 <properties>
    <comment>Some Comment</comment>
    <entry key="Name">Jaemin Kim</entry>
    <entry key="PhoneNUmber">000-0000-0000</entry>
 </properties>
 ```

Load Properties
```java
Properties prop = new Properties();
try(InputStream in = Files.newInputStream(Paths.get("some.xml"))){
    prop.loadFromXML(in);
}

String value1 = prop.getProperty("Name"); 
String value2 = prop.getProperty("PhoneNumber");
...

```

Using Default Properties
================================
 can create default properties by passing to constructor.

 ```java
 Properties defaults = new Properties();
 defaults.setProperty("Price", "3000");
 
 
 Properties newProp = new Properties(defaults);
 String price = newProp.getProperty("Price");
 
 System.out.println(price); // 3000

 int value = Integer.parseInt(price);
 newProp.setProperty("Price", Integer.toString(value*2));
 price = newProp.getProperty("Price");

 System.out.println(price); // 6000
 ```
 

Load file from package
---------------------------------

```java
 ClassName.class.getResourceAsStream(path)
``` 

[Example2 - Default properties in package](Example2.java)
