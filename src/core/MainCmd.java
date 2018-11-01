package core;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class MainCmd {
    static long DeployInterval = TimeUnit.MINUTES.toMillis(10);

    public static void main(String[] args) {
        long ObserverInterval = TimeUnit.SECONDS.toMillis(5);
        String rootDir = "/Users/ryziii/Hexo/";
        Watch watch = new Watch();
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(watch);
        FileAlterationMonitor monitor = new FileAlterationMonitor(ObserverInterval,observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
