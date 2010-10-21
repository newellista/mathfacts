package org.newell.mathfacts;


public class AdditionProblem extends MathProblem
{
	private static final String OPERATION = "+ ";

	public AdditionProblem( ProblemConstraints problemConstraints )
	{
		super( problemConstraints );
	}

	protected void generateProblem()
	{
		firstNumber = getRandomInteger( getProblemConstraints().getFirstLowerBound(), getProblemConstraints().getFirstUpperBound() );
		secondNumber = getRandomInteger( getProblemConstraints().getSecondLowerBound(), getProblemConstraints().getSecondUpperBound() );
		
		if( secondNumber > firstNumber )
		{
			int tmp = firstNumber;
			firstNumber = secondNumber;
			secondNumber = tmp;
		}
	}

	public boolean isCorrect()
	{
		return false;
	}

	@Override
	public int evaluate()
	{
		return firstNumber + secondNumber;
	}

	@Override
	public String getOperation()
	{
		return OPERATION;
	}
}
