package thread.control.join;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

public class JoinMainV4 {
	public static void main(String[] args) throws InterruptedException {
		log("start");
		SumTask task1 = new SumTask(1, 50);
		Thread thread1 = new Thread(task1, "thread-1");

		thread1.start();

		//thread 종료 대기

		log("join() - main thread가 thread1 종료까지 대기");
		thread1.join(2000);
		log("main thread 대기 완료");

		log("task1.result = " + task1.result);

		int sumAll = task1.result;
		log("task1 = " + sumAll);
		log("end");
	}

	static class SumTask implements Runnable {

		int startValue;
		int endValue;
		int result;

		public SumTask(int startValue, int endValue) {
			this.startValue = startValue;
			this.endValue = endValue;
		}

		@Override
		public void run() {
			log("작업 시작");
			sleep(2000);
			int sum = 0;
			for (int i = startValue; i <= endValue; i++) {
				sum += i;
			}
			result = sum;
			log("작업 완료 result = " + result);
		}
	}

}
