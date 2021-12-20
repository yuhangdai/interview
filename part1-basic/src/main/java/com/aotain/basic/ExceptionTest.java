package com.aotain.basic;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/13
 */
public class ExceptionTest {

    private static String exceptionReturn(String str){
        try{
            return str.length()+"";
        } catch (Exception e){
            return "exception result";
        } finally {
            System.out.println("aaaaaaa");
//            return "finally result";
        }

    }


    private static void exceptioninFor(){
        try{
            for (int i=0;i<100;i++){
                if (i %50 == 0){
                    System.out.println(i);
                    throw  new RuntimeException();
                }
            }
        } catch (Exception e){

        }
        System.out.println("======");
    }

    public static void main(String[] args) {
        String result = exceptionReturn("");
        System.out.println(result);
//        exceptioninFor();
    }
}
