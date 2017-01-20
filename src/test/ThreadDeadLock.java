package test;

import java.util.Arrays;

public class ThreadDeadLock {

	public static void main(String[] args) throws InterruptedException {
		final Object resourceOne = new Object();
		final Object resourceTwo = new Object();

		Thread threadOne = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					synchronized (resourceOne) {
						System.out.println("T-1: Aquired resource one");
						System.out.println("T-1: Wait....");
						Thread.currentThread().sleep(100);
						System.out.println("T-1: Aquiring resource two");
						synchronized (resourceTwo) {
							System.out.println("T-1: Aquired resource two");
						}
					}

				} catch (InterruptedException e) {
					System.out.println("T-1: Interrupted");
				}

			}
		});

		Thread threadTwo = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (resourceTwo) {
					System.out.println("T-2: Aquired resource two");

					System.out.println("T-2: Aquiring resource one");
					synchronized (resourceOne) {
						System.out.println("T-1: Aquired resource one");
					}
				}

			}
		});
		
		threadOne.start();
		threadTwo.start();
		
		System.out.println("Wating for threds to finish.....");
		
		threadOne.join(1000);

		System.out.println("Interrupting T1");
		
		threadOne.interrupt();
		int arr[] = new int[]{64630,11735,14216,99233,14470,4978,73429,38120,51135,67060};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println((arr[arr.length / 2] + arr[(arr.length + 2) / 2]) / 2);
	}
}
