package interpreter.example2;

import java.util.*;

public class InterpreterExample {
	   public static void main(String[] args) {
		   
		   //'42 2 1 - +' equals 43

		   String input = "42 2 1 - +";
		   
	       System.out.println( "'" + input + "' equals " + new Parser(input).evaluate());
	   }
}

class Parser {
	   private ArrayList parseTree = new ArrayList(); // only one NonTerminal Expression here

	   public Parser(String s) {
		   String tokens [] = s.split(" ");
		   for (int i=0; i< tokens.length; i++ ){
			   String token = tokens[i];
			   if (token.equals("+")) {
				   System.out.println("TOKEN for PLUS: " + token);
				   parseTree.add( new TerminalExpression_Plus() );
			   } else if (token.equals("-")) {
				   System.out.println("TOKEN for MINUS: " + token);
				   parseTree.add( new TerminalExpression_Minus() );
			   } else {
				   System.out.println("TOKEN for NUMBER: " + token);
				   Integer itoken = new Integer(token);
				   parseTree.add( new TerminalExpression_Number(itoken.intValue()));
			   }
	       }
	   }

	   public int evaluate() {
		   System.out.println("ENTER EVALUATE");
		   
	       Stack  context = new Stack(); 
	       for (int i=0; i< parseTree.size(); i++) {
	    	   Expression e = (Expression)parseTree.get(i); 
	    	   
	    	   if (e instanceof TerminalExpression_Number) {
	    		   System.out.println("TerminalExpression_Number Expression is being used");
	    	   } else if (e instanceof TerminalExpression_Minus) {
	    		   System.out.println("TerminalExpression_Minus Expression is being used");
	    	   } else if (e instanceof TerminalExpression_Plus) {
	    		   System.out.println("TerminalExpression_Plus Expression is being used");
	    	   }  
	    	   
	    	   e.interpret(context);
	       }
	       return ((Integer)context.pop()).intValue();
	   }
	  
}
//#######################
	interface Expression {
	   public void interpret(Stack  s);
	}

//	#######################
	class TerminalExpression_Number implements Expression {
	   private int number;
	   
	   public TerminalExpression_Number(int number){ 
		   this.number = number; 
	   }
	   
	   public void interpret(Stack s)  { 
		   s.push(new Integer(number)); 
	   }
	}

//	#######################
	class TerminalExpression_Plus implements Expression {
	   public void interpret(Stack s)  { 
		   //s.push( Integer.valueof (s.pop()) + s.pop() ); 
		   Integer i1 = (Integer)(s.pop());
		   Integer i2 = (Integer)(s.pop());
		   
		   System.out.println("i1 = " + i1 + " i2 = " + i2);
		   int i = i1.intValue() + i2.intValue();
		   
		   s.push( new Integer(i));
	   }
	}


//	#######################
	class TerminalExpression_Minus implements Expression {
	   public void interpret(Stack s)  { 
		   int tmp = ((Integer)s.pop()).intValue(); 
		   s.push( new Integer(((Integer)s.pop()).intValue() - tmp )); 
		}
	}




