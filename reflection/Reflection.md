Reflection
==========

## Type as a Type
-  Type is the foundation of any app solution
-  Type is used to model
-  Java uses types to model type issues
    * Fundmental type is the Class class
        1. Each type has a Class instance
        2. Describes the type in detail

### Class Declaration
```java

public class GameScore{
    private int score;
    public GameScore(int initScore){
        this.score = initScore;
    }

    public synchronized int getScore(){
        return this.score;
    }

    public synchronized void addScore(int score){
        this.score += score;
    }
}

// Instance of Class class
simpleName : GameScore
fields : int score
constructors : GameScore
methods : getScore, addScore

```

## Accessing a Type's Class Instance
-  Accessing a type's Class instance
    * from a type reference
        - call getClass method
    * from string name
        - call Class.forName static method (fully qualified type name)
    * from type literal
        - Use typename.class

```java

void showName(Class<?> theClass){ // Generic parameter -> use question mark. why? we don't know what parameter will be used. actually.
    System.out.println(theClass.getSimpleName());
}


/* 1. type reference */
void doWork(object obj){
    Class<?> c = obj.getClass();
    showName(c);
}

GameScore score = new GameScore();
doWork(score);

/* 2. string name */

Class<?> c = Class.forName("some.GameScore");
showName(c);


/* 3. type literal */
Class<?> c = GameScore.class;
showName(c);

```

[Example1](./Test.java)


### Type has only one class(Class class) instance
 above example, through different three methods, return same class instance.


## What can we know?
-  Superclass
-  Implemented interfaces
-  Modifiers
-  Members


### Accessing Type Information

```java

public class GameScore{
    protected int score = 0;
}

public final class AdvancedGameScore extends GameScore implements Runnable{
    protected String playerId;

    public AdvancedGameScore(String playerId){ this.playerId = playerId;}
    public AdvancedGameScore(String playerId, int initScore){this.playerId = playerId; this.score = initScore;}

    public void run(){
        //.... do something
    }    
}



void classInfo(Object obj){
    Class<?> theClass = obj.getClass();
    System.out.println(theClass.getSimpleName());
     
    Class<?> superClass = theClass.getSuperclass();
    System.out.println(superClass.getSimpleName());

    Class<?>[] interfaces = theClass.getInterfaces();
    for(Class<?> interface:interfaces){

        //  interface.isInterface(); return true
        System.out.println(String.format("%s is %s", interface.getSimpleName(), (interface.isInterface()?"interface":"not interface")));
    }
}


```
[Example2](./Test.java)





### Type Access Modifiers
-   Retrieving type access modifiers
    * getModifiers method
    * return int value
        - each modifier is a separate bit.
- Modifier class


```java

void typeModifiers(Object obj){
    Class<?> theClass = obj.getClass();
    int modifiers = theClass.getModifiers();

    if((modifiers & Modifier.FINAL)>0)
        System.out.println("bitwise check - final");
    
    if(Modifier.isFinal(modifiers))
        System.out.println("class check - final");

    if(Modifier.isPrivate(modifiers))
        System.out.println("class check - private");

    ...
}


```

[Example3](./Test.java)



## Types to descibe type members
-  Field
    * Name
    * Type
-  Method
    * Name
    * Return type
    * Parameter types
-  Constructor
    * Name
    * Parameter types

### Accessing
-   Type's declared members only // Public, Protected, Private
    * getDeclaredFields
    * getDeclaredMethods
    * getDeclaredConstructors
-   Type's declared & Inherited members / Public
    * getFields
    * getMethods
    * getConstructors



```java
public class GameScore{
    private int score;
    private final double bonusRate = 0.1;
}; 

void printFields(Field[] arr){
    for(Field f:arr){
        System.out.println(f.getName() + " : " + f.getType());
    }
}

void fieldInfo(Object obj){
    Class<?> theClass = obj.getClass();
    Field[] fields = theClass.getFields();
    printFields(fields);

    Field[] declaredFields = theClass.getDeclaredFields();
    printFields(declaredFields);
}

```
[Example4](./Test.java)




```java
public final class AdvancedGameScore extends GameScore implements Runnable{
    public void ResetGameScore(){}
    public void run(){
        //do something
    }
}

void printMethod(Method[] arr){
    for(Method m: arr){
        // print
    }
}

// print method list
void methodInfo(Object obj){
    Class<?> theClass = obj.getClass();
    Method[] methods = theClass.getMethods();
    printMethod(methods);

    Method[] declaredMethods = theClass.getDeclaredMethods();
    printMeethod(declaredMethods);   


    /* only declared method */
    for(Method m: methods){
        if(m.getDeclaringClass()!= Object.class){
            System.out.println(m.getName());
        }
    }

}
```
[Example5](./Test.java)



## Interacting with Object Instances
-  Reflection not limited to describing types
-  Can access and invoke members

### Member Access with Reflection
```java

void classGetScore(Object obj){
    try{
        Class<?> theClass = obj.getClass();

        Method m = theClass.getMethod("getScore");
        Object result = m.invoke(obj);

        System.out.println("Result: "+result);
    }catch(Exception e){ }
}

void classAddScore(Object obj, int score){
    try{
        Class<?> theClass = obj.getClass();
        Method m = theClass.getMethod("addScore", int.class);
        m.invoke(obj, score);
    }catch(Exception e){ }
}
```
[Example6](./Test.java)


## Instance Creation with Reflection
-  Constructor can be executed
    * use Constructor's newInstance method
    * return new instance

```java

public class GameScoreWorker {
    AdvancedGameScore m_score;
    public GameScoreWorker(AdvancedGameScore score){
        this.m_score = score;
    }

    public void dowork(){
        Thread t = new Thread(this.m_score);
        t.start();
    }
}


void working(String workerType, Object target){
    try{
        Class<?> workerT = Class.forName(workerType);
        Class<?> targetT = target.getClass();
        Constructor c = workerT.getConstructor(targetT);

        Object worker = c.newInstance(target);
        Method m = workerT.getMethod("do");
        m.invoke(worker);

    }catch(Excpetion e){

    }

}

```
[Example7](./Test.java)
