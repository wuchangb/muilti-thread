package thread.control.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 flag=false");
        myTask.flag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                log("작업 중");
                sleep(3000);
            }
        }
    }
}
