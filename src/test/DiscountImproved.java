package test;

public class DiscountImproved {

	public static void main(String[] args) {
		double money = 111.50;
		double charge = 10.60;
		double serviceTax = (charge * 14) / 100;
		double swatchCess = (charge * 0.5) / 100;
		double krishiCess = (charge * 0.5) / 100;
		
		double left = money - (charge + serviceTax + swatchCess + krishiCess);
		System.out.format("money: %f%n", money);
		System.out.format("charge: %f%n", charge);
		System.out.format("service tax: %f%n", serviceTax);
		System.out.format("swatch cess: %f%n", swatchCess);
		System.out.format("krishi cess: %f%n", krishiCess);
		System.out.format("left: %f%n", left);
	}
}
