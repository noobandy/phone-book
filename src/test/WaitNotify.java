package test;


public class WaitNotify {

	static class Producer {

		public Producer() {
			super();
		}

		synchronized void produce(StringBuilder builder) {
			System.out.println("Producer: Producing data....");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Producer interrupted");
			}
			
			builder.append("data");
			System.out.println("Producer: Data produced. notify all");
			//notify data available
			notifyAll();
		}
	}

	static class Consumer {

		public Consumer() {
			super();
		}

		synchronized void consume(StringBuilder builder) {
			if (builder.length() == 0) {
				System.out.println("Consumer: data not available");
				try {
					//wait until data becomes available
					System.out.println("Consumer: wait untill data becomes available");
					wait();
				} catch (InterruptedException e) {
					System.out.println("Consumer: interrupted");
					if(builder.length() > 0) {
						System.out.println("Consumer: Finally data is available");
						System.out.format("Data %s%n", builder.toString());
					}
					
				}

			}

		}
	}

	public static void main(String[] args) {

		final Producer producer = new Producer();

		final Consumer consumer = new Consumer();

		final StringBuilder builder = new StringBuilder();

		Thread producerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				producer.produce(builder);
			}
		});

		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				consumer.consume(builder);
			}
		});

		producerThread.start();
		consumerThread.start();

	}
}
