package com;

/**
 * 描述: Test
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:09:30 14:05:14
 */
public class Test {
     volatile int sum = 0;
     volatile int sum1 = 0;

    public static void main(String[] args) {
        Test test = new Test();
        test.t();
    }

    public void t(){
        for (int i = 0;i<100;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(sum);
                    sum = sum + 1;
                }
            });
            thread.start();
        }
        System.out.println(sum);
    }
}
