package test;

public class BitWiser {

	public static void main(String[] args) {
		/**
		 * Bitwise operators & (bitwise AND operator) | (bitwise OR operator) ~
		 * (bitwise COMPLEMENT operator) (1's complement - flips bits) ^
		 * (bitwise EXOR operator) set to true only when both bits are different
		 * << (left shift operator) shift bits to left >> (right shift) shift
		 * bits to right >>> (unsigned right shift) shift right fill 0 <br>
		 * Note:when shift bit is greater than size then modulo shift is done <br>
		 * Note:Java performs shifts in steps one bit at a time <br>
		 * Note: In Java negative no. are represented in 2's compliment form <br>
		 * Note: before operation is performed Java converts the operands to int
		 * and then carries out the operation <br>
		 * Note: shift right 1 bit divides the number by 2 <br>
		 * Note: shift left 1 bit multiplies the number by 2. <br>
		 * Note: average of two rally large numbers where their sum overflows
		 * the range can be computed using >>> operator average = (a + b) >>> 2;
		 */

		int four = 4;
		int five = 5;
		// outputs 4
		System.out.format("%d AND %d = %d%n", five, four, five & four);
		// outputs 5
		System.out.format("%d OR %d = %d%n", five, four, five | four);
		// outputs -6
		/**
		 * Why? in binary 5 is represented as 00000101 ~5 is 11111010 Since
		 * numbers in java are represented in 2's complement form as it happens
		 * that ~5 is 2's complement representation for -6
		 */
		System.out.format("COMPLEMENT %d = %d%n", five, ~five);

		// outputs 1
		System.out.format("%d EXOR %d = %d%n", five, four, five ^ four);

		int two = 2;
		int minusFive = -5;
		// outputs 20
		// shift left fill zero
		System.out.format("%d << %d = %d%n", five, two, five << two);
		// outputs -20
		/**
		 * Why? 2's complement representation for -5 is 11111011 left shift 2
		 * bits 11101100 which is -20 in 2's complement
		 */
		System.out.format("%d << %d = %d%n", minusFive, two, minusFive << two);

		// outputs 1
		System.out.format("%d >> %d = %d%n", five, two, five >> two);
		// outputs -2
		/**
		 * Why? 2's complement representation for -5 is 11111011 shift is
		 * performed in steps so shift 1 bit to right 01111101 set left most to
		 * 1(for negative number) so after 1 bit right shift it's 11111101 shift
		 * again 01111110 set left most to 1 after shifting 2 bits right
		 * 11111110 which happens to be 2's complement representation for -2
		 */
		System.out.format("%d >> %d = %d%n", minusFive, two, minusFive >> two);

		// outputs 1
		System.out.format("%d >> %d = %d%n", five, two, five >>> two);

		// outputs 1073741822
		/**
		 * Why? convert operands to int and then carry out the operation
		 */
		System.out
				.format("%d >>> %d = %d%n", minusFive, two, minusFive >>> two);

	}
}
