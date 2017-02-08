package test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DiscountBig {

	public static void main(String[] args) {
		MathContext context = new MathContext(2, RoundingMode.HALF_UP);
		BigDecimal money = new BigDecimal(111.50, context);
		BigDecimal charge = new BigDecimal(10.60, context);
		BigDecimal serviceTax = charge.multiply(new BigDecimal(14.0, context))
				.divide(new BigDecimal(100.0, context));
		BigDecimal swatchCess = charge.multiply(new BigDecimal(0.5, context))
				.divide(new BigDecimal(100.0, context));
		BigDecimal krishiCess = charge.multiply(new BigDecimal(0.5, context))
				.divide(new BigDecimal(100.0, context));
		BigDecimal left = money.subtract(charge).subtract(serviceTax)
				.subtract(swatchCess).subtract(krishiCess);
		
		System.out.format("money: %f%n", money.doubleValue());
		System.out.format("charge: %f%n", charge.doubleValue());
		System.out.format("service tax: %f%n", serviceTax.doubleValue());
		System.out.format("swatch cess: %f%n", swatchCess.doubleValue());
		System.out.format("krishi cess: %f%n", krishiCess.doubleValue());
		System.out.format("left: %f%n", left.doubleValue());
	}
}
