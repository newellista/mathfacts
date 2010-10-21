package org.newell.mathfacts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MathFactsPopupFrame extends JFrame
{
	protected HashMap< String, Pair > buttonMap;
	private JButton exitButton;
//	private JButton newProfileButton;
	private JPanel buttonPanel;

	public MathFactsPopupFrame()
	{
		initialize();
		pack();
	}

	protected void initialize()
	{
		JPanel p = new JPanel( new BorderLayout() );
		p.add( getButtonPanel(), BorderLayout.CENTER );
		
		getContentPane().add( p );
	}
	
	private JPanel getButtonPanel()
	{
		if( buttonPanel == null )
		{
			buttonPanel = new JPanel( new FlowLayout( FlowLayout.LEFT, 5, 5 ) );
			ArrayList< Profile > profiles = ProfileManager.getInstance().getProfileList();

			for ( Profile p : profiles )
			{
				Pair buttonPair = getNamedButton( p.getName(), p );
				
				buttonPanel.add( buttonPair.getButton() );
			}
			
			buttonPanel.add( getExitButton() );
		}
		
		return buttonPanel;
	}

	private JButton getExitButton()
	{
		if( exitButton == null )
		{
			exitButton = new JButton( "Exit" );
			exitButton.addActionListener( new ActionListener()
			{
				public void actionPerformed( ActionEvent e )
				{
					ProfileManager.getInstance().save();
					System.exit( 0 );
				}
			});
		}
		return exitButton;
	}

	private Pair getNamedButton( String name, Profile p )
	{
		Pair pair = getButtonMap().get( name );
		
		if( pair == null )
		{
			pair = new Pair();
			JButton button = new JButton( name );
			button.addActionListener( new ProfileButtonActionListener() );
			pair.setButton( button );
			pair.setProfile( p );
			getButtonMap().put( name, pair );
		}

		return pair;
	}

	public static void main( String[] args )
	{
		MathFactsPopupFrame frame = new MathFactsPopupFrame();
		
		frame.setVisible( true );
	}

	class ProfileButtonActionListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			JButton button = ( JButton ) e.getSource();
			String name = button.getText();
			Pair pair = getButtonMap().get( name );
			
			MathFactsFrame frame = new MathFactsFrame( pair.getProfile() );
			frame.setVisible( true );
		}
	}

	protected HashMap< String, Pair > getButtonMap()
	{
		if( buttonMap == null )
		{
			buttonMap = new HashMap< String, Pair >();
		}
		
		return buttonMap;
	}

	class Pair
	{
		protected JButton button;
		protected Profile profile;

		public JButton getButton()
		{
			return button;
		}

		public void setButton( JButton button )
		{
			this.button = button;
		}

		public Profile getProfile()
		{
			return profile;
		}

		public void setProfile( Profile profile )
		{
			this.profile = profile;
		}
	}
}
