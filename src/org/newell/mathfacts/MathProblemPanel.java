package org.newell.mathfacts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MathProblemPanel extends JPanel
{
	protected ProblemConstraints problemConstraints;
	protected JLabel equalLineLabel;
	protected JTextField answerEditBox;
	protected MathProblem mathProblem;
	protected JLabel firstLine;
	protected JLabel secondLine;
	
	protected Font myFont;
	private static final Color ERROR_COLOR = Color.red;
	private static final int ERROR_VALUE = -1;
	private static final Color CORRECT_COLOR = Color.getColor( "background" );
	
	public MathProblemPanel( MathProblem mathProblem )
	{
		setMathProblem( mathProblem );
		setProblemConstraints( mathProblem.getProblemConstraints() );
		initialize();
	}
	
	protected void initialize()
	{
		myFont = new Font( "Courier", Font.PLAIN, problemConstraints.getFontSize() );
		setLayout( new GridLayout( 4, 1 ) );
		String secondLine = mathProblem.getSecondLine();
		
		add( getFirstLine( secondLine.length() ) );
		add( getSecondLine() );
		add( getEqualLine() );
		add( getAnswerEditBox() );
	}

	private JTextField getAnswerEditBox()
	{
		if( answerEditBox == null )
		{
			answerEditBox = new JTextField();
			Dimension dim = answerEditBox.getPreferredSize();
			
			answerEditBox.setFont( myFont );
			
			dim.width = 10;
			
			answerEditBox.setPreferredSize( dim );
			answerEditBox.setMaximumSize( dim );
			answerEditBox.setMinimumSize( dim );
		}
		return answerEditBox;
	}

	private JLabel getEqualLine()
	{
		if( equalLineLabel == null )
		{
			equalLineLabel = new JLabel( "----" );
			equalLineLabel.setFont( myFont );
		}
		
		return equalLineLabel;
	}

	public boolean isCorrect()
	{
		int enteredValue = ERROR_VALUE;
		int correctValue = getMathProblem().evaluate();
		String enteredText = getAnswerEditBox().getText();
		if( enteredText != null && enteredText.length() != 0 )
		{
			enteredValue = Integer.parseInt( getAnswerEditBox().getText() );
		}
		
		return ( correctValue == enteredValue );
	}
	
	public boolean checkAnswer()
	{
		if( isCorrect() == false )
		{
			setBackground( ERROR_COLOR );
		}
		else
		{
			setBackground( CORRECT_COLOR );
		}
		
		return isCorrect();
	}
	
	protected JLabel getFirstLine(int minLength )
	{
		if( firstLine == null )
		{
			firstLine = new JLabel( mathProblem.getFirstLine( minLength ) );
			firstLine.setFont( myFont );
		}
		
		return firstLine;
	}
	
	protected Component getSecondLine()
	{
		if( secondLine == null )
		{
			secondLine = new JLabel( mathProblem.getSecondLine() );
			secondLine.setFont( myFont );
		}
		return secondLine;
	}
	
	public static void main( String[] args )
	{
		JFrame frame = new JFrame();
		
		ProblemConstraints constraint = new ProblemConstraints( ProblemConstraints.ADDITION_OPERATION, ProblemConstraints.NO_CARRY, 0, 20, 0, 20, 12 );
		
		MathProblem problem = MathProblemFactory.generateMathProblem( constraint );
		
		MathProblemPanel panel = new MathProblemPanel( problem );
		
		frame.getContentPane().add( panel );
		
		frame.pack();
		
		frame.setVisible( true );
		
	}

	public MathProblem getMathProblem()
	{
		return mathProblem;
	}

	public void setMathProblem( MathProblem mathProblem )
	{
		this.mathProblem = mathProblem;
	}

	public ProblemConstraints getProblemConstraints()
	{
		return problemConstraints;
	}

	public void setProblemConstraints( ProblemConstraints problemConstraints )
	{
		this.problemConstraints = problemConstraints;
	}
}
