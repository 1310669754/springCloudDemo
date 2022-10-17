package com.synchronization;


/**
 * 描述: User
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 * 备注：1、如果有几个方法，呈递归调用形式，且都有synchronized修饰，那么锁就是调用的对象实例，例如：A调B，B调C，a.A,则对象都是 a是可重入锁
 * @author sjb
 *
 * @version V1.1.2
 * @date 2022:07:08 14:59:46
 */
public class User implements Runnable{
    static User rich = new User();//只有一把锁

    public synchronized void eat() throws InterruptedException {
        System.out.println("我是："+ Thread.currentThread().getName() +",吃饭了！");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"吃完了");
    }

    @Override
    public void run() {
        try {
            this.eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        User user = new User();//一个实例对象就是一把锁
        Thread a = new Thread(rich);
        Thread b = new Thread(user);
        a.start();
        b.start();
    }
}
