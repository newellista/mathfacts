package org.newell.mathfacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ProfileManager
{
	protected ArrayList< Profile > profileList;
	protected static ProfileManager profileManager;
	private static final String COUNT_KEY = "NumberOfProfiles";
	private static final String NAME_KEY = "Name.";
	private static final String NUM_PROBLEMS_KEY = "NumberOfProblems.";
	private static final String OPERATION_KEY = "Operation.";
	private static final String FLB_KEY = "FirstLowerBound.";
	private static final String FUB_KEY = "FirstUpperBound.";
	private static final String SLB_KEY = "SecondLowerBound.";
	private static final String SUB_KEY = "SecondUpperBound.";
	private static final String CARRY_KEY = "CarryFlag.";
	private static final String FONT_SIZE_KEY = "FontSize.";

	private static final String profileName = "./profiles.ini";

	private ProfileManager()
	{
		loadProfiles();
	}

	private void loadProfiles()
	{
		Properties p = new Properties();
		try
		{
			p.load( new FileInputStream( profileName ) );
			String numProfilesString = p.getProperty( COUNT_KEY );

			if ( numProfilesString != null )
			{
				int numProfiles = Integer.parseInt( numProfilesString );

				for ( int i = 0; i < numProfiles; i++ )
				{
					Profile profile = new Profile();

					profile.setName( p.getProperty( NAME_KEY + i ) );
					profile.setNumberOfProblems( p
							.getProperty( NUM_PROBLEMS_KEY + i ) );
					String operation = p.getProperty( OPERATION_KEY + i );
					String flb = p.getProperty( FLB_KEY + i );
					String fub = p.getProperty( FUB_KEY + i );
					String slb = p.getProperty( SLB_KEY + i );
					String sub = p.getProperty( SUB_KEY + i );
					String carryFlag = p.getProperty( CARRY_KEY + i );
					String fontSize = p.getProperty( FONT_SIZE_KEY + i );

					if( fontSize == null )
					{
						fontSize = "12";
					}
					
					ProblemConstraints problemConstraint = new ProblemConstraints(
							Integer.parseInt( operation ), Integer
									.parseInt( carryFlag ), Integer
									.parseInt( flb ), Integer.parseInt( fub ),
							Integer.parseInt( slb ), Integer.parseInt( sub ), Integer.parseInt( fontSize ) );

					profile.setProblemConstraints( problemConstraint );

					getProfileList().add( profile );
				}
			}
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public static ProfileManager getInstance()
	{
		if ( profileManager == null )
		{
			profileManager = new ProfileManager();
		}

		return profileManager;
	}

	public Profile getNamedProfile( String profileName )
	{
		Profile foundProfile = null;

		for ( Profile p : getProfileList() )
		{
			if ( p.getName().compareToIgnoreCase( profileName ) == 0 )
			{
				foundProfile = p;
				break;
			}
		}
		return foundProfile;
	}

	public ArrayList< Profile > getProfileList()
	{
		if ( profileList == null )
		{
			profileList = new ArrayList< Profile >();
		}

		return profileList;
	}

	public void save()
	{
		Properties p = new Properties();

		p.setProperty( COUNT_KEY, Integer.toString( getProfileList().size() ) );

		for ( int i = 0; i < getProfileList().size(); i++ )
		{
			Profile profile = getProfileList().get( i );

			p.setProperty( NAME_KEY + i, profile.getName() );
			p.setProperty( NUM_PROBLEMS_KEY + i, Integer.toString( profile.getNumberOfProblems() ) );

			ProblemConstraints pc = profile.getProblemConstraints();
			p.setProperty( OPERATION_KEY + i, Integer.toString( pc.getOperation() ) );
			p.setProperty( FLB_KEY + i, Integer.toString( pc.getFirstLowerBound() ) );
			p.setProperty( FUB_KEY + i, Integer.toString( pc.getFirstUpperBound() ) );
			p.setProperty( SLB_KEY + i, Integer.toString( pc.getSecondLowerBound() ) );
			p.setProperty( SUB_KEY + i, Integer.toString( pc.getSecondUpperBound() ) );
			p.setProperty( CARRY_KEY + i, Integer.toString( pc.getCarryFlag() ) );
			p.setProperty( FONT_SIZE_KEY + i, Integer.toString( pc.getFontSize() ) );
		}
		try
		{
			p.store( new FileOutputStream( profileName ), "" );
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
