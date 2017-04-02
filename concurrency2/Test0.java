package concurrency2;


public class Test0{
    public static class T{
        private Object lock1 = new Object();
        private Object lock2 = new Object();
        
        public void method1(){
            synchronized(lock1){
                System.out.println(String.format("[thread : %s] method1()", Thread.currentThread().getName()));
                method2();
            }
        }

        public void method2(){
            synchronized(lock2){
                System.out.println(String.format("[thread : %s] method2()", Thread.currentThread().getName()));
                method3();
            }
        }
        
        public void method3(){
            synchronized(lock1){
                System.out.println(String.format("[thread : %s] method3()", Thread.currentThread().getName()));
            }
        }
    }


    public static void main(String[] arg){
        T t = new T();

        Thread t1 = new Thread(()->{t.method1();});
        Thread t2 = new Thread(()->{t.method2();});

        t1.start(); t2.start();
        try{
            t1.join(); t2.join();
        }catch(InterruptedException e){}
    }
}