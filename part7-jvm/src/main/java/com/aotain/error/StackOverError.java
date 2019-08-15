package com.aotain.error;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2019/07/04
 */
public class StackOverError {

    private long deep(int count){
        if (count<=0){
           return 0;
        } else if (count==1){
            return 1;
        } else if (count==2){
            return 1;
        }else {
            return deep(count-1)+deep(count-2);
        }

    }

    public static void main(String[] args) {
        StackOverError stackOverError = new StackOverError();
//        for (int i=1;i<3000;i++){
//            System.out.println(stackOverError.deep(i));
//        }

        System.out.println(stackOverError.deep(3000));

    }
}
