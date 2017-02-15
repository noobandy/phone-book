package test;

import java.security.SecureRandom;
import java.util.Arrays;

public class FisherYates {

	private static int[] random(int n) {

		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = i + 1;
		}
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < n; i++) {
			int j = random.nextInt(i + 1);
			int temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
		}
		return numbers;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(random(52)));
	}
}
