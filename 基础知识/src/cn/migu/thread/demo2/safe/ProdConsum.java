package cn.migu.thread.demo2.safe;


/*
模拟生产者和消费者模型---管程法
包括：产品，生产者，消费者，缓存容器
 */
public class ProdConsum {

    public static void main(String[] args) {
        SyncContainer syncContainer = new SyncContainer();
        Producor p1 = new Producor(syncContainer,"苏果超市");
        Consumer c1 = new Consumer(syncContainer,"老乡鸡");

        p1.start();
        c1.start();
    }




}


//产品
class Chicken{
    private int id;//产品ID

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

//生产者
class Producor extends Thread{
    private SyncContainer syncContainer;

    public Producor(SyncContainer syncContainer,String name) {
        super(name);
        this.syncContainer = syncContainer;
    }

    //负责生产，并将产品放入容器（队列，缓存）
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            syncContainer.push(new Chicken(i));
            System.out.println(this.getName()+"生产了第"+i+"只鸡。");
        }
    }
}

//消费者
class Consumer extends Thread{
    private SyncContainer syncContainer;

    public Consumer(SyncContainer syncContainer,String name) {
        super(name);
        this.syncContainer = syncContainer;
    }

    //负责消费产品
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken chicken= syncContainer.pop();
            System.out.println(this.getName()+"消费了第"+chicken.getId()+"只鸡。");
        }
    }
}

//缓存容器
class SyncContainer{
    //产品容器
    private Chicken[] chickens;
    //容器计数
    private int count;

    public SyncContainer() {
        this.chickens = new Chicken[10];
        this.count = 0;
    }

    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，就暂停生产，等待消费
        if(count == chickens.length){
            //生产暂停
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //继续生产
        chickens[count] = chicken;
        count++;

        //通知消费者消费
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized Chicken pop(){
        //如果容器空了
        if(count == 0){
            //暂停消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //继续消费
        count--;
        Chicken chicken = chickens[count];

        //通知继续生产
        this.notifyAll();

        return chicken;
    }

}