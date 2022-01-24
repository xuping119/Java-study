package cn.migu.thread.demo1;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class DownloadTest extends  Thread{
    private String url;
    private String name;

    public DownloadTest(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        DownLoaderFile dt = new DownLoaderFile();
        dt.download(url,name);
        System.out.println("下载了文件 ："+ name);
    }

    public static void main(String[] args) {
        DownloadTest dt1 = new DownloadTest("https://img-blog.csdnimg.cn/20201103175307532.png","1.png");
        DownloadTest dt2 = new DownloadTest("https://img-blog.csdnimg.cn/20201103175307532.png","2.png");
        DownloadTest dt3 = new DownloadTest("https://img-blog.csdnimg.cn/20201103175307532.png","3.png");

        dt1.start();
        dt2.start();
        dt3.start();

    }
}


class DownLoaderFile {
    public void download(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
