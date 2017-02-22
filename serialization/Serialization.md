
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
public class UserInfo{
    private String userId; // implemented Serializable 
    private int maxScore = 0; // primitives
    private int currentScore = 0;

    public UserInfo(String uid){/* implementation */}
    public UserInfo(String uid, int max, int current) { /* implementation */ }
    public void setScore(int score){/* implentation */}
}

```

 Implement [Example - Persist Object](Example1.java)
 

Class Version
===========================
