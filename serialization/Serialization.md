
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


//TODO testing Example2.java