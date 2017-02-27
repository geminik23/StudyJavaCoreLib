
Persisting Java Objects
==========================
 Store Java runtime Object into a byte stream and vice versa.

 - Serializing : objects to byte stream
 - Deserializing : byte stream to objects
 
 Use Serializable interface(no METHOD!!) for marking

 * ObjectOutputStream
 * ObjectInputStream


Being Serializable
==========================
 1. Primitive types
 2. Object implemented interface Serializable

```java
public class UserInfo  {
    private String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;

    public UserInfo(String uid){/* implementation */}
    public UserInfo(String uid, int max, int current) { /* implementation */ }
    public void setScore(int score){/* implentation */}
}

```

 [Example - Persist Object](Example1.java)
 > javac serialization/Example1.java
 
 > java serialization.Example1 --save "res/object_data.dat"
 
 > java serialization.Example1 --open "res/object_data.dat"
 

Class Version
===========================

 If we update the class and restore the object then it occurs **InvalidClassException**.

```java
public class UserInfo implements Serializable{
    private String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;
    private int gameCount; /* game count property added */

    public UserInfo(String uid){/* implementation */}
    public UserInfo(String uid, int max, int current) { /* implementation */ }
    public void setScore(int score){/* implentation */}
}
```


 When Storing a object, stored also serialVersionUID(Serial Version Unique Identifier); This ID is 64bit long integer type. 

 This serialVersionUID indicates compatibility. But this UID affected by several factors(type name, interfaces, members, etc... ).For compatibility, class must have the same value.
 
 
How to set serialVersionUID in class?
---------------------------
 -  Adding **serialVersionUID** field
    * long type
    * static
    * final
    * private 

> private final static long serialVersionUID = xxx;


How to get **Serial Version Unique Identifier**?
---------------------------

 1. Use IDE 
 2. Use serialVer (from rootDir and need class file)
    - open GUI '-show' option
    - console [classpath]

> serivalVer -show
    
> serivalVer 


 [Example1 - Restore updated class version](Example1.java)
 
 [Example2 - Save and Restore using serialVersionUID](Example2.java)


Customizing Serialization
===========================

 Methods called through reflection
---------------------------
 - writeObject
    * return void
    * throws IOException
    * ObjectOutputStream parameter
        * private
        * write values
        * use writeFields
        * can call defaultWriteObject
 - readObject  
    * return void
    * throws IOException, ClassNotFoundException
    * ObjectInputStream parameter
        * private 
        * read values
        * use readFields
        * can call defaultReadObject

```java

/* this acts like default serialization */
public class UserInfo{
    ...

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
    }

    ...
}

```



Using Fields interface
---------------------------
 Can access values by name and set default value.

```java
public class UserInfo{
    ...

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream.GetField fields = in.readFields();
        this.userId = (String) fields.get("userId", null);
        this.maxScore = fields.get("maxScore", 1000);
        this.currentScore = fields.get("currentScore", -1);
        this.gameCount = fields.get("gameCount", -1);
    }

    ...
}
```

 [Example3 - Write through defaultWriteObject() and read using Fields](Example2.java)   comparison with (UserInfo2 and UserInfo3)

