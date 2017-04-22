Annotations
===========
- special types that act as metadata
    * Applied to a specific target
- Have no direct impact on target
    * Do not change target's behavior

Annotations in code
-------------------
-  Name always preceded by @ when used
-  Placed directly before target
-  Allowable targets vary with annotation





```java

public class SomeClass{

    @Override // compiler looks for methods marked with this annotation
    public String toString(){ // Verifies there is a method with matching signature that can be overridden

    }
}

```


Common Java core platform annotations
-------------------------------------
-  Most affect compilation
    * Override
    * Deprecated
    * SuppressWarnings

```java

public class A{
    @Deprecated
    void doThis(){}
    void newDoThis(){}
}


public class Program{

    @SuppressWarnings("deprecation")
    public void work(){
        A a = new A();
        a.doThis();
    }

}


```


[Example - DeprecateTest()](./Test.java)




Declaring Annotations
=====================
- Custom Annotations
- Flexible work dispatch system



Special kind of interface
-------------------------
-  Usage is much more restricted
    * Can't be explicitly implemented
-  Implicitly extend Annotation interface
-  Interface behavior not initially apparent


Similar to interfaces
---------------------
-  Use interface keyword
    * Preceded by an @ symbol
-  Declarations allow same modifies

Can optionally have elements
---------------------
-  Associate values within annotation
-  Declared as methods
-  Setting is similar to fields

```java
public @interface Something{
    boolean use();
}

@Something(use = true)
public class SomeClass{
    ...
}

```


Accessing Annotations
=====================
- Annotations available through reflection
    * Call getAnnotation on type/member -> Class of annotation
- Returns reference to annotation interface
    * Null if does not have annotation of requested type

```java

ExecutorService pool = ...

void work(String typeName, Object target) throws Exception{
    Class<?> workerType = class.forName(typeName);
    Worker worker = (Worker) workerType.newInstance();
    worker.setTarget(target);

    WorkHandler wh = workerType.getAnnotation(WorkHandler.class); // !!NOT WORK -> retention
    if(wh==null){
        // throw exception
    }

    if(wh.use()){
        pool.submit(new Runnable(){
            public void run(){
                worker.doWork();
            }
        });
    }else
        worker.doWork();
}

```


[Example - AccessingAnnotation()](./Test.java)



Annotation Retention
====================
- Annotations can specify availability
    * Part of annotation declaration
    * Indicated with Retention annotation(RetentionPolciy value)

- RetentionPolicy Values
    * SOURCE : Exist only in source file Discared by compiler
    * CLASS : Compiled into class file Discarded by runtime (Default)
    * RUNTIME : Loaded into runtime, Accessible with reflection

```java

@Retention(RetentionPolicy.RUNTIME)
public @interface WorkHandler{
    ...
}
```



Annotation Target
=================
- Annotatations can narrow allowable targets
    * Part of annotation declaration
    * Indicated with Target annotation
        * Accepts ElementType value
    * Can support multiple targets
        * Use array notation

> Target(ElementType.CONSTRUCTOR)
> Target({ElementType.TYPE, ElementType.METHOD})

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkHandler{
    ...
}

```


Simplifying Element Setting
===========================
- Elements can be setup to simplify setting
    * Handle common cases
    * Element default values
    * Element assignment shorthand
- Elements can be declared with a default
    * Use default keyword
    * Can still explicitly set if desired


```java



public @interface Something{
    boolean use() default true;
}

@Something 
@Something(use=true)

```

Element Assignment Shorthand
============================
- Can exclude element name when setting
    * Must be setting only one element
    * Element name must be value

```java

public @interface Something{
    boolean value() default true;
}

@Something(false)

```

Valid Annotation Element Types
-----------------------------
-  Primitive type
-  String
-  Enum
-  Annotation
-  Class<?>
-  Array