package org.newell.mathfacts;


public class SubtractionProblem extends MathProblem
{
	private static final String OPERATION = "- ";
	protected int answer;
	
	public SubtractionProblem( ProblemConstraints problemConstraints )
	{
		super( problemConstraints );
	}

	protected void generateProblem()
	{
		do
		{
			firstNumber = getRandomInteger( getProblemConstraints().getFirstLowerBound(), getProblemConstraints().getFirstUpperBound() );
			secondNumber = getRandomInteger( getProblemConstraints().getSecondLowerBound(), getProblemConstraints().getSecondUpperBound() );
		} while ( firstNumber == secondNumber );
		
		if( secondNumber > firstNumber )
		{
			int tmp = firstNumber;
			firstNumber = secondNumber;
			secondNumber = tmp;
		}
	}

	@Override
	public int evaluate()
	{
		return firstNumber - secondNumber;
	}

	@Override
	public String getOperation()
	{
		return OPERATION;
	}
}
