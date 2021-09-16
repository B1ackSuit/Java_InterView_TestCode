package cn.ean.ticket;

/**
 * FileName:SellTicket
 * Author:ean
 * Date:2021/9/2 4:05 下午
 * 使用多线程模拟三个窗口同时售票
 **/
public class SellTicket {
    public static void main(String[] args) {

        SellTicketWay01 sellTicketWay011 = new SellTicketWay01();
        SellTicketWay01 sellTicketWay012 = new SellTicketWay01();
        SellTicketWay01 sellTicketWay013 = new SellTicketWay01();

        SellTicketWay02 sellTicketWay02 = new SellTicketWay02();


        //都会出现负数票，原因是if判断时都在循环里判断
        sellTicketWay011.start();
        sellTicketWay012.start();
        sellTicketWay013.start();

//        new Thread(sellTicketWay02).start();
//        new Thread(sellTicketWay02).start();
//        new Thread(sellTicketWay02).start();
    }
}

class SellTicketWay01 extends Thread{
    //让多个线程共享这个属性
    private static int ticketNum = 100;

    @Override
    public void run(){
        while (true){
            if (ticketNum <= 0){
                System.out.println("售票结束");
                break;
            }

            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口w1：" + Thread.currentThread().getName() + "售出一张票" +
                    "剩余票数：" + --ticketNum);
        }
    }
}

class SellTicketWay02 implements Runnable{
    //让多个线程共享这个属性
    private static int ticketNum = 100;

    @Override
    public void run(){
        while (true){
            if (ticketNum <= 0){
                System.out.println("售票结束");
                break;
            }

            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口w2：" + Thread.currentThread().getName() + "售出一张票" +
                    "剩余票数：" + --ticketNum);
        }
    }
}
