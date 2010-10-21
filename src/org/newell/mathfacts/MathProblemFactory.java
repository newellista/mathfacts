package org.newell.mathfacts;

import java.util.ArrayList;

public class MathProblemFactory
{
	static MathProblem generateMathProblem( ProblemConstraints problemConstraints )
	{
		MathProblem mathProblem = null;
		
		switch( problemConstraints.getOperation() )
		{
			case ProblemConstraints.ADDITION_OPERATION:
				mathProblem = new AdditionProblem( problemConstraints );
				break;
			case ProblemConstraints.SUBTRACTION_OPERATION:
				mathProblem = new SubtractionProblem( problemConstraints );
				break;
		}
		
		return mathProblem;
	}
	
	static ArrayList< MathProblem > generateMathProblemList( int numberOfProblems, ProblemConstraints problemConstraints )
	{
		ArrayList< MathProblem > listOfProblems = new ArrayList< MathProblem >();
		
		for( int i = 0; i < numberOfProblems; i++ )
		{
			listOfProblems.add( generateMathProblem( problemConstraints ) );
		}
		return listOfProblems;
	}
}
