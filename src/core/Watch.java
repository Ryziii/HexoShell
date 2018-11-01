package core;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;
import java.util.Date;

public class Watch extends FileAlterationListenerAdaptor {

    private String hexodg = "cd ~/hexo\nhexo d -g";
    private String hexos = "cd ~/hexo\nhexo s";
    private String ki = "lsof -i :4000|grep -v \"PID\"|grep \"node\"|awk '{print \"kill -9\",$2}'|sh";
    private static boolean flag = false;
    private long baseTime;
    private long curTime;

    public Watch() {
        this.baseTime = new Date().getTime();
        this.curTime = new Date().getTime();
    }

    @Override
    public void onFileChange(File file) {
        curTime = new Date().getTime();
        Double dif = (curTime - baseTime)*1.0/1000;
        System.out.printf("%.0fs\n",dif);

        if (dif >= MainCmd.DeployInterval) {
            flag = false;
            String str = new Exec().start(ki).toString();
            System.out.println(str);
            System.out.println("##################################################################");
            System.out.println("#        File Change more than 10min....Starting Hexo work       #");
            System.out.println("##################################################################");
            baseTime = curTime;
            String str1 = new Exec().start(hexodg).toString();
            System.out.println(str1);
        }else{
            if(flag == false) {
                System.out.println("##################################################################");
                System.out.println("#          未达10min，开启预览，请进入http://localhost:4000      #");
                System.out.println("##################################################################");
                flag = true;
                new Exec().start(hexos);
            }
        }

    }
}