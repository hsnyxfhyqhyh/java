package interpreter.expression;

/**
 * Declares an abstract Interpret operation that is common
 * to all nodes in the abstract syntax tree.
 */

public interface AbstractExpression
{
	void interpret( Context context );
}