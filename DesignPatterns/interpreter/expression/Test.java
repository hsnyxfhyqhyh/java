package interpreter.expression;


public class Test
{
	public static void main( String arg[] )
	{
		NonterminalExpression rule1 = new NonterminalExpression();
		NonterminalExpression rule2 = new NonterminalExpression();
		NonterminalExpression rule3 = new NonterminalExpression();
		
		TerminalExpression rule4 = new TerminalExpression();

		rule1.setSuccessor( rule2 );
		rule2.setSuccessor( rule3 );
		rule3.setSuccessor( rule4 );

		rule1.interpret( new Context() {} );
	}
}
