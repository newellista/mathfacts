package org.newell.mathfacts;

public class ProblemConstraints
{
	public static final int ADDITION_OPERATION = 1;
	public static final int SUBTRACTION_OPERATION = 2;
	public static final int MULTIPLICATION_OPERATION = 3;
	public static final int DIVISION_OPERATION = 4;

	public static final int NO_CARRY = 1;
	public static final int MAY_CARRY = 2;
	public static final int MUST_CARRY = 3;

	protected int operation;
	
	protected int firstUpperBound;
	protected int secondUpperBound;

	protected int firstLowerBound;
	protected int secondLowerBound;
	
	protected int carryFlag;
	
	protected int fontSize;

	public ProblemConstraints( int operation, int flags, int firstLowerBound, int firstUpperBound, int secondLowerBound, int secondUpperBound, int fontSize )
	{
		this.operation = operation;
		this.firstLowerBound = firstLowerBound;
		this.firstUpperBound = firstUpperBound;
		this.secondLowerBound = secondLowerBound;
		this.secondUpperBound = secondUpperBound;
		this.carryFlag = flags;
		this.fontSize = fontSize;
	}
	
	public int getOperation()
	{
		return operation;
	}

	public void setOperation( int operation )
	{
		this.operation = operation;
	}

	public int getFirstLowerBound()
	{
		return firstLowerBound;
	}

	public void setFirstLowerBound( int firstLowerBound )
	{
		this.firstLowerBound = firstLowerBound;
	}

	public int getFirstUpperBound()
	{
		return firstUpperBound;
	}

	public void setFirstUpperBound( int firstUpperBound )
	{
		this.firstUpperBound = firstUpperBound;
	}

	public int getSecondLowerBound()
	{
		return secondLowerBound;
	}

	public void setSecondLowerBound( int secondLowerBound )
	{
		this.secondLowerBound = secondLowerBound;
	}

	public int getSecondUpperBound()
	{
		return secondUpperBound;
	}

	public void setSecondUpperBound( int secondUpperBound )
	{
		this.secondUpperBound = secondUpperBound;
	}

	public int getCarryFlag()
	{
		return carryFlag;
	}

	public void setCarryFlag( int carryFlag )
	{
		this.carryFlag = carryFlag;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append( "Operation: " );
		sb.append( getOperation() );
		sb.append( "\n" );
		sb.append( "Carry Flag: " );
		sb.append( getCarryFlag() );
		sb.append( "\n" );
		sb.append( "FontSize: " );
		sb.append( getFontSize() );
		sb.append( "\n" );
		sb.append( "FirstUpperBound: " );
		sb.append( getFirstUpperBound() );
		sb.append( "\n" );
		sb.append( "FirstLowerBound: " );
		sb.append( getFirstLowerBound() );
		sb.append( "\n" );
		sb.append( "SecondUpperBound: " );
		sb.append( getSecondUpperBound() );
		sb.append( "\n" );
		sb.append( "SecondLowerBound: " );
		sb.append( getSecondLowerBound() );
		
		return sb.toString();
	}

	public int getFontSize()
	{
		return fontSize;
	}

	public void setFontSize( int fontSize )
	{
		this.fontSize = fontSize;
	}
}
