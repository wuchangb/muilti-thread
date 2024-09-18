package thread.volatile1;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class VolatileCountMain {
	public static void main(String[] args) {
		MyTask myTask = new MyTask();
		Thread thread = new Thread(myTask, "myTask");
		thread.start();

		sleep(1000);

		myTask.flag = false;
		log("flag = " + myTask.flag + ", count = " + myTask.count + " in while()");
	}

	static class MyTask implements Runnable {
		// boolean flag = true;
		// long count;
		volatile boolean flag = true;
		volatile long count;

		@Override
		public void run() {
			while (flag) {
				count++;
				if (count % 100_000_000 == 0) {
					log("flag = " + flag + ", count = " + count + " in while()");
				}
			}
			log("flag = " + flag + ", count = " + count + " while 종료");
		}
	}

}
