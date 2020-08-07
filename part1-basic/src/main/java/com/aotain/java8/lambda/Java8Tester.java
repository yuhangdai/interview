package com.aotain.java8.lambda;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/08/06
 */
public class Java8Tester {



    public static void main(String[] args) {

        String param = "Hello!";
        Java8Tester tester = new Java8Tester();

        MathOperation addition = (int a,int b)-> a+b;
        MathOperation subtraction = (a,b)-> a-b;
        MathOperation multiplication = (a,b)-> {return a*b;};
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        GreetingService greetService3 = message ->{
            System.out.println( param + message);
        };
//        param = "2345";

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        greetService3.sayMessage("Google");

        Runnable runnable = () ->{
            while (true){
                System.out.println("aaa");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }



    interface MathOperation {
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
