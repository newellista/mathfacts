package org.newell.mathfacts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MathFactsFrame extends JFrame implements ActionListener
{
	protected JPanel mainPanel;
	protected JPanel buttonPanel;
	protected JButton checkButton;
	protected JButton exitButton;
	private JScrollPane scrollPanel;
	private JPanel problemsPanel;
	protected ProblemConstraints constraint;
	protected ArrayList< MathProblemPanel > problemList;
	protected int numberOfProblems;
	private long startTime;
	private long stopTime;
	
	public MathFactsFrame( ProblemConstraints constraint )
	{
		setConstraint( constraint );
		initialize();
	}
	
	public MathFactsFrame( Profile profile )
	{
		setConstraint( profile.getProblemConstraints() );
		setNumberOfProblems( profile.getNumberOfProblems() );
		
		initialize();
		setTitle( profile.getName() );
	}

	public void setNumberOfProblems( int numberOfProblems )
	{
		this.numberOfProblems = numberOfProblems;
	}
	
	protected void initialize()
	{
		getContentPane().setLayout( new BorderLayout() );
		getContentPane().add( getMainPanel(), BorderLayout.CENTER );
		
		getContentPane().add( Box.createRigidArea( new Dimension( 5, 0 ) ), BorderLayout.EAST );
		getContentPane().add( Box.createRigidArea( new Dimension( 5, 0 ) ), BorderLayout.WEST );
		getContentPane().add( Box.createRigidArea( new Dimension( 0, 5 ) ), BorderLayout.NORTH );
		
		getContentPane().add( getButtonPanel(), BorderLayout.SOUTH );
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		pack();
		startTimer();
	}

	private void startTimer()
	{
		startTime = System.currentTimeMillis();
	}
	
	private void stopTimer()
	{
		stopTime = System.currentTimeMillis();
	}

	public String getElapsedTime()
	{
		int seconds;
		int minutes;
		
		long elapsedMSecs = stopTime - startTime;
		
		seconds = ( int ) ( elapsedMSecs / 1000 );
		
		minutes = seconds / 60;
		
		seconds %= 60;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append( minutes );
		sb.append( ":" );
		sb.append( seconds );
		
		return sb.toString();
	}
	private JPanel getButtonPanel()
	{
		if( buttonPanel == null )
		{
			buttonPanel = new JPanel();
			buttonPanel.setLayout( new FlowLayout() );
			
			buttonPanel.add( getCheckButton() );
			buttonPanel.add( getExitButton() );
		}
		
		return buttonPanel;
	}

	private JButton getExitButton()
	{
		if( exitButton == null )
		{
			exitButton = new JButton( "Exit" );
			exitButton.addActionListener( this );
			exitButton.setEnabled( false );
		}
		
		return exitButton;
	}

	private JButton getCheckButton()
	{
		if( checkButton == null )
		{
			checkButton = new JButton( "Check Answers" );
			checkButton.addActionListener( this );
		}
		
		return checkButton;
	}

	private JPanel getMainPanel()
	{
		if( mainPanel == null )
		{
			mainPanel = new JPanel();
			mainPanel.setLayout( new BorderLayout() );
			
			scrollPanel = new JScrollPane( getProblemsPanel() );
			
			mainPanel.add( scrollPanel, BorderLayout.CENTER );
		}
		return mainPanel;
	}

	private JPanel getProblemsPanel()
	{
		if( problemsPanel == null )
		{
			problemsPanel = new JPanel();
			problemsPanel.setLayout( new GridLayout( 0, 10, 8, 8 ) );
			
			problemList = generateProblemPanelList();
			
			for( MathProblemPanel m : problemList )
			{
				problemsPanel.add( m );
			}
		}
		return problemsPanel;
	}

	private ArrayList< MathProblemPanel > generateProblemPanelList()
	{
		ArrayList< MathProblemPanel > list = new ArrayList< MathProblemPanel >();
		
		for( int i = 0; i < getNumberOfProblems(); i++ )
		{
			list.add( new MathProblemPanel( MathProblemFactory.generateMathProblem( getConstraint() ) ) );
		}
		return list;
	}
	
	private int getNumberOfProblems()
	{
		return numberOfProblems;
	}
	
	public static final void main( String[] args )
	{
		ProblemConstraints constraint = new ProblemConstraints( ProblemConstraints.ADDITION_OPERATION, ProblemConstraints.NO_CARRY, 1, 20, 1, 9, 12 );

		MathFactsFrame frame = new MathFactsFrame( constraint );
		
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		frame.setVisible( true );			
	}

	public ProblemConstraints getConstraint()
	{
		return constraint;
	}

	public void setConstraint( ProblemConstraints constraint )
	{
		this.constraint = constraint;
	}

	public void actionPerformed( ActionEvent event )
	{
		if( event.getSource() == getCheckButton() )
		{
			checkAnswers();
		}
		else if( event.getSource() == getExitButton() )
		{
			stopTimer();
			writeStatistics();
			dispose();
		}
	}

	private void writeStatistics()
	{
		int numberOfProblems = problemList.size();
		StringBuilder sb = new StringBuilder();
		
		sb.append( "*********** Statistics ***********" );
		sb.append( '\n' );
		sb.append( new Date().toString() );
		sb.append( '\n' );
		sb.append( "Number of Problems: " );
		sb.append( numberOfProblems );
		sb.append( '\n' );
		sb.append( getConstraint() );
		sb.append( '\n' );
		sb.append( "Elapsed time: " );
		sb.append( getElapsedTime() );
		
		try
		{
			PrintWriter pw = new PrintWriter( new BufferedOutputStream( new FileOutputStream( new File( "statistics.txt" ), true ) ) );
			pw.print( sb.toString() );
			pw.close();
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
	}

	private void checkAnswers()
	{
		boolean bAllCorrect =true;
		
		for( MathProblemPanel p : problemList )
		{
			boolean bAnswerCorrect = p.checkAnswer();
			if( !bAnswerCorrect )
			{
				bAllCorrect = false;
			}
		}
		
		if( bAllCorrect )
		{
			getExitButton().setEnabled( true );
		}
	}
}
