package test;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class MeanMedianMode {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int sampleSize = scanner.nextInt();

		int[] samples = new int[sampleSize];
		Map<Integer, Integer> sampleFrequencies = new TreeMap<Integer, Integer>();
		int sampleSum = 0;

		for (int i = 0; i < sampleSize; i++) {
			int sample = scanner.nextInt();

			samples[i] = sample;

			sampleSum += sample;

			Integer sampleFrequency = sampleFrequencies.get(sample);

			if (sampleFrequency == null) {
				sampleFrequency = 0;
			}

			sampleFrequency++;

			sampleFrequencies.put(sample, sampleFrequency);
		}

		double mean = (double) sampleSum / sampleSize;

		double median = 0;

		Arrays.sort(samples);

		if (sampleSize % 2 == 0) {
			median = (double) (samples[(sampleSize / 2) - 1] + samples[((sampleSize + 2) / 2) - 1]) / 2;
		} else {
			median = (double) samples[((sampleSize + 1) / 2) - 1];
		}

		Integer mode = null;
		Integer modeFrequency = null;

		for (Entry<Integer, Integer> entry : sampleFrequencies.entrySet()) {
			if (mode == null) {
				mode = entry.getKey();
				modeFrequency = entry.getValue();
			} else {
				if (entry.getValue() > modeFrequency) {
					mode = entry.getKey();
					modeFrequency = entry.getValue();
				}
			}

		}

		System.out.format("%.1f%n", mean);
		System.out.format("%.1f%n", median);
		System.out.format("%d%n", mode);
	}
}