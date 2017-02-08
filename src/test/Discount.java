package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Discount {

	public static class Product {
		private String name;
		private double price;

		public Product(String name, double price) {
			super();
			this.name = name;
			this.price = price;
		}

		public double getDiscountAmount(double discountPercentage) {
			return (price * discountPercentage) / 100;
		}

		public double getPriceAfterDiscount(double discountPercentage) {
			return price - getDiscountAmount(discountPercentage);
		}
	}

	private static List<Product> products = new ArrayList<Product>();

	static {
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			products.add(new Product("Product " + i,
					(random.nextDouble() * 100) + random.nextDouble()));
		}
	}

	public static void main(String[] args) {

		Collections.sort(products, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {

				return (int) (o2.price - o1.price);
			}
		});

		System.out.format("%15s %15s %15s %15s%n", "Product", "Price", "Discount",
				"Discounted Price");
		Random random = new Random();
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			double discountPercentage = 0;
			if (i < 5) {
				 discountPercentage = random.nextDouble() * 10
						+ random.nextDouble();
				
			}
			
			System.out.format("%15s %12.2f %12.2f %12.2f%n", product.name,
					product.price,
					product.getDiscountAmount(discountPercentage),
					product.getPriceAfterDiscount(discountPercentage));
		}
	}
}
