package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

import reflection.AdvancedGameScore;

public class Test{
    static AdvancedGameScore obj = new AdvancedGameScore("someId");
    public static void main(String[] arg){
        Example1();
        // Example2();
        // Example3();
        // Example4();
        // Example5();
        // Example6();
        // Example7();
    }




    static void showName(Class<?> theClass){
        System.out.println(theClass.getSimpleName());
    }

    static void Example1(){
        GameScore score = new GameScore(100);
        
        //
        Class<?> c = score.getClass();
        showName(c);
        
        //
        try{
            c = Class.forName("reflection.GameScore");
            showName(c);
        }catch(ClassNotFoundException e){}
        
        //
        c = GameScore.class;
        showName(c);
    }

    static void Example2(){

        Class<?> theClass = obj.getClass();
        System.out.println(theClass.getSimpleName());
        
        Class<?> superClass = theClass.getSuperclass();
        System.out.println(superClass.getSimpleName());

        Class<?>[] interfaces = theClass.getInterfaces();
        for(Class<?> i:interfaces){

            //  interface.isInterface(); return true
            System.out.println(String.format("%s is %s", i.getSimpleName(), (i.isInterface()?"interface":"not interface")));
        }
    }

    static void Example3(){

        Class<?> theClass = obj.getClass();
        int modifiers = theClass.getModifiers();
        if((modifiers & Modifier.FINAL)>0)
            System.out.println("bitwise check - final");
        
        if(Modifier.isFinal(modifiers))
            System.out.println("class check - final");

        if(Modifier.isPrivate(modifiers))
            System.out.println("class check - private");

    }


    static void Example4(){
        
        Consumer<Field[]> printField = (Field[] fields)->{    
            for(Field f:fields) System.out.println(f.getName() + " : " + f.getType());
        };

        Class<?> theClass = obj.getClass();

        Field[] fields = theClass.getFields();
        printField.accept(fields);

        System.out.println("--------------------");

        Field[] declaredFields = theClass.getDeclaredFields();
        printField.accept(declaredFields);



    }


    static void Example5(){
        Consumer<Method[]> printMethod = (Method[] methods)->{
            for(Method m: methods){
                System.out.println(m.getName());
            }
        };

        Class<?> theClass = obj.getClass();

        Method[] methods = theClass.getMethods();
        printMethod.accept(methods);

        System.out.println("--------------------");

        Method[] declaredMethods = theClass.getDeclaredMethods();
        printMethod.accept(declaredMethods);   

        System.out.println("--------------------");

        /* only declared method */
        for(Method m: methods){
            if(m.getDeclaringClass()!= Object.class){
                System.out.println(m.getName());
            }
        }

               
    }




    static void classGetScore(Object obj){
        try{
            Class<?> theClass = obj.getClass();

            Method m = theClass.getMethod("getScore");
            Object result = m.invoke(obj);

            System.out.println("Result: "+result);
        }catch(Exception e){}
    }

    static void classAddScore(Object obj, int score){
        try{
            Class<?> theClass = obj.getClass();

            Method m = theClass.getMethod("addScore", int.class);
            m.invoke(obj, score);
        }catch(Exception e){}
    }

    static void Example6(){
        classGetScore(obj);
        classAddScore(obj, 100);
        System.out.println(String.format("Final Score is %d", obj.getScore()));
    }



        
    public static class GameScoreWorker {
        AdvancedGameScore m_score;
        public GameScoreWorker(AdvancedGameScore score){
            this.m_score = score;
        }

        public void dowork(){
            Thread t = new Thread(this.m_score);
            t.start();
        }
    }


    static void Example7(){
        try{
            Class<?> workerT = GameScoreWorker.class;
            Class<?> targetT = obj.getClass();
            Constructor c = workerT.getConstructor(targetT);

            Object worker = c.newInstance(obj);
            Method m = workerT.getMethod("dowork");
            m.invoke(worker);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}