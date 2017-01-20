package test;

public class ThreadInteruption {

	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean loop = true;
				while (loop) {
					System.out.println("T-1");
					if (Thread.interrupted()) {
						System.out.println("Interrupt received");
						loop = false;
					}
				}

			}
		});

		threadOne.start();

		Thread.currentThread().sleep(1000);

		threadOne.interrupt();
	}
}
