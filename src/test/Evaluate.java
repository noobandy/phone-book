package test;

import java.util.Stack;

public class Evaluate {

	

	public static class ExpressionTokeniser {  
		public static class ExpressionToken {
			public enum ExpressionTokenType {
				OPERATOR,OPERAND,PARENTHESES;
			}
			
			private ExpressionTokenType type;
			private String value;
			public ExpressionToken(ExpressionTokenType type, String value) {
				super();
				this.type = type;
				this.value = value;
			}
			public ExpressionTokenType getType() {
				return type;
			}
			public String getValue() {
				return value;
			}
			
			
		}
		
		private String expression;
		private int currentIndex;

		public ExpressionTokeniser(String expression) {
			super();
			this.expression = expression;
			currentIndex = 0;
		}
		
		public ExpressionToken nextToken() {
			if(currentIndex < expression.length()) {
				StringBuilder tokenBuilder = new StringBuilder();
				
			} else {
				return null;
			}
			return null;
		}
	}
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("missing expression argument");
			System.exit(1);
		}

		Stack<Character> operators = new Stack<Character>();
		Stack<Double> operands = new Stack<Double>();

		String expression = args[0];

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (ch == ')') {
				// evaluate
			}

			if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%') {
				// push in operator
			}

			if (ch == '.' || ch == '0' || ch == '1' || ch == '2' || ch == '3'
					|| ch == '4' || ch == '5' || ch == '6' || ch == '7'
					|| ch == '8' || ch == '9') {
				StringBuilder numberBuilder = new StringBuilder();
				numberBuilder.append(ch);
				
			}
		}

	}
}
