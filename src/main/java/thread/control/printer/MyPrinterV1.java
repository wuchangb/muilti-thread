package thread.control.printer;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class MyPrinterV1 {
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

				String job = jobQueue.poll();
				log("출력시작: " + job + ", 대기문서: " + jobQueue);
				sleep(3000);
			}

			log("프린터 종료 ");
		}

		public void addJob(String input) {
			jobQueue.offer(input);
		}
	}
}
