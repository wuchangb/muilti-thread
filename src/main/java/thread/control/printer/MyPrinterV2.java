package thread.control.printer;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class MyPrinterV2 {
	public static void main(String[] args) {
		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			log("프린터할 내용을 입력하세요. 종료 (q)");
			String userInput = scanner.nextLine();
			if (userInput.equals("q")) {
				printer.work = false;
				printerThread.interrupt();
				break;
			}
			printer.addJob(userInput);
		}

	}

	static class Printer implements Runnable {
		volatile boolean work = true;
		Queue<String> jobQueue = new LinkedBlockingDeque<>();
		@Override

		public void run() {
			while (work) {
				if (jobQueue.isEmpty()) {
					continue;
				}

				try {
					String job = jobQueue.poll();
					log("출력시작: " + job + ", 대기문서: " + jobQueue);
					Thread.sleep(3000);
					log("출력완료");
				} catch (InterruptedException e) {
					log("인터럽트!!");
					break;
				}
			}

			log("프린터 종료 ");
		}

		public void addJob(String input) {
			jobQueue.offer(input);
		}
	}
}
