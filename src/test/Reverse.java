package test;

import java.util.Scanner;
import java.util.Stack;

public class Reverse {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Stack<Integer> stack = new Stack<Integer>();
		
		while(scanner.hasNextInt()) {
			stack.push(scanner.nextInt());
		}
		
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
