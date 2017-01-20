package test;

public class WaitNotify {

	static class Producer {

		public Producer() {
			super();
		}

		void produce(StringBuilder builder) {

			synchronized (builder) {
				while (builder.length() != 0) {
					try {
						builder.wait();
					} catch (InterruptedException e) {
					}
				}
				builder.append("data");
				// notify data available
				builder.notifyAll();

			}

		}
	}

	static class Consumer {

		public Consumer() {
			super();
		}

		void consume(StringBuilder builder) {

			synchronized (builder) {
				while (builder.length() == 0) {
					try {
						builder.wait();
					} catch (InterruptedException e) {

					}
				}
				System.out.format("%s%n", builder.toString());
				builder.delete(0, builder.length());
				builder.notifyAll();
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {

		final Producer producer = new Producer();

		final Consumer consumer = new Consumer();

		final StringBuilder builder = new StringBuilder();

		Thread producerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					producer.produce(builder);
				}

			}
		});

		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					consumer.consume(builder);
				}

			}
		});

		consumerThread.start();
		producerThread.start();

	}
}
