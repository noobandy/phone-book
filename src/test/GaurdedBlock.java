package test;

public class GaurdedBlock {
	public static void main(String[] args) {

		final  loop = Boolean.TRUE;

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				System.out
						.println("T: wait for at least 100 milisec.. then change loop to false");

				try {
					Thread.currentThread().sleep(100);
					loop.				} catch (InterruptedException e) {
					System.out.println("T: interrupted");
				}

			}
		});

		thread.start();

		while (loop) {
			System.out.println(".");
		}
	}
}
