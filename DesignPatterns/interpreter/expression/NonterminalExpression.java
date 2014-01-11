package interpreter.expression;

/**
 * One such class is required for every rule in the
 * grammar. Maintains instance variables of type AbstractExpression
 * for each of the symbols.
 */

public class NonterminalExpression implements AbstractExpression
{
	private AbstractExpression successor;

	public void setSuccessor( AbstractExpression successor )
	{
		this.successor = successor;
	}

	public AbstractExpression getSuccessor()
	{
		return successor;
	}

	public void interpret( Context context )
	{
	}
}
