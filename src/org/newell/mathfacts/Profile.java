package org.newell.mathfacts;

public class Profile
{
	protected String name;
	protected int numberOfProblems;
	protected ProblemConstraints problemConstraints;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public int getNumberOfProblems()
	{
		return numberOfProblems;
	}

	public void setNumberOfProblems( int numberOfProblems )
	{
		this.numberOfProblems = numberOfProblems;
	}

	public ProblemConstraints getProblemConstraints()
	{
		return problemConstraints;
	}

	public void setProblemConstraints( ProblemConstraints problemConstraints )
	{
		this.problemConstraints = problemConstraints;
	}

	public void setNumberOfProblems( String numberOfProblemsString )
	{
		if( numberOfProblemsString != null )
		{
			setNumberOfProblems( Integer.parseInt( numberOfProblemsString ) );
		}
	}
}
