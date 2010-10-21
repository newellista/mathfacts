package org.newell.mathfacts;

import java.util.Random;

abstract public class MathProblem
{
	protected int firstNumber;
	protected int secondNumber;
	protected ProblemConstraints problemConstraints;

	public MathProblem( ProblemConstraints problemConstraints )
	{
		setProblemConstraints( problemConstraints );
		generateProblem();
	}

	public String getFirstLine( int minLength )
	{
		String txt = Integer.toString( firstNumber );

		if ( txt.length() < minLength )
		{
			StringBuilder sb = new StringBuilder();

			for ( int i = 0; i < minLength - txt.length(); i++ )
			{
				sb.append( " " );
			}
			sb.append( txt );
			txt = sb.toString();
		}
		return txt;
	}

	public String getSecondNumber()
	{
		return Integer.toString( secondNumber );
	}

	public String getSecondLine()
	{
		StringBuilder sb = new StringBuilder();

		sb.append( getOperation() );
		sb.append( " " );
		sb.append( getSecondNumber() );

		return sb.toString();
	}

	public int pickNumberInRange( int aLowerLimit, int aUpperLimit )
	{
		if ( aLowerLimit >= aUpperLimit )
		{
			StringBuilder message = new StringBuilder();
			message.append( "Lower limit (" );
			message.append( aLowerLimit );
			message.append( ") must be lower than Upper limit (" );
			message.append( aUpperLimit );
			message.append( ")" );
			throw new IllegalArgumentException( message.toString() );
		}

		Random generator = new Random();
		// get the range, casting to long to avoid overflow problems
		long range = ( long ) aUpperLimit - ( long ) aLowerLimit + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = ( long ) ( range * generator.nextDouble() );
		return ( int ) ( fraction + aLowerLimit );
	}

	protected int getRandomInteger( int lowerBound, int upperBound )
	{
		if ( lowerBound > upperBound )
		{
			int tmp = upperBound;
			upperBound = lowerBound;
			lowerBound = tmp;
		}
		
		Random generator = new Random();
		// get the range, casting to long to avoid overflow problems
		long range = ( long ) upperBound - ( long ) lowerBound + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = ( long ) ( range * generator.nextDouble() );
		return ( int ) ( fraction + lowerBound );
	}

	public ProblemConstraints getProblemConstraints()
	{
		return problemConstraints;
	}

	public void setProblemConstraints( ProblemConstraints problemConstraints )
	{
		this.problemConstraints = problemConstraints;
	}

	abstract public String getOperation();

	abstract public int evaluate();

	abstract protected void generateProblem();
}
