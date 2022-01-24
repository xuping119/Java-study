package cn.migu.thread.demo2.safe;


/*
模拟生产者和消费者模型---信号灯法
包括：产品，生产者，消费者，标志位
 */
public class ProdConsum2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}


//产品
class TV{
    private String voice;//产品ID

    //演员表演节目 ：T
    //观众观看节目：F
    private boolean flag = true;


    //演员表演节目
    public synchronized void play(String voice){
        //如果观看，就等待
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了："+voice);
        this.notifyAll(); //通知观看
        this.voice = voice;
        this.flag = !this.flag;
    }

    //观众观看节目
    public synchronized void watch(){
        //如果在表演，则等待
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("观众观看了:"+voice);
        this.notifyAll();//通知表演
        this.flag = !this.flag;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

}

//生产者
class Player extends Thread{
    private TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    //负责生产，并将产品放入容器（队列，缓存）
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if(i%2 == 0){
                tv.play("郭德纲相声");
            }else {
                tv.play("动物世界");
            }
        }
    }
}

//消费者
class Watcher extends Thread{
    private TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    //负责消费产品
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            tv.watch();
        }
    }
}

