package thread.volatile1;

<<<<<<< HEAD
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");

    }

    static class MyTask implements Runnable {

//        boolean runFlag = true;
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {

            }
            log("task 종료");
        }
    }
=======
import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class VolatileFlagMain {

	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		Thread thread = new Thread(myTask, "myTask");
		log("runFlag = " + myTask.runFlag);
		thread.start();

		sleep(1000);
		log("runFlag를 false로 변경 시도");
		myTask.runFlag = false;
		log("runFlag = " + myTask.runFlag);
		log("main 종료");
	}

	static class MyTask implements Runnable {

		// boolean runFlag = true;
		volatile boolean runFlag = true;

		@Override
		public void run() {
			log("task 시작");
			while (runFlag) {

			}
			log("task 종료");
		}
	}

>>>>>>> 2ff203008edd8822e1100a544446caae570af818
}
