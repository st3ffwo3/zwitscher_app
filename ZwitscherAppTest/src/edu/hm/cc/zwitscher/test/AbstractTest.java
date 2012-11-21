package edu.hm.cc.zwitscher.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import edu.hm.cc.zwitscher.api.object.ApiConstants;
import edu.hm.cc.zwitscher.test.config.ConfigurationConstants;
import edu.hm.cc.zwitscher.test.config.ConfigurationSingleton;

/**
 * Abstrakte Basisklasse für alle JUnit Testklassen.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public abstract class AbstractTest
{

	private static String m_lastLogClass;

	private static String m_lastLogMethod;

	/**
	 * Liefert die URL für REST-Calls.
	 * 
	 * @return URL
	 */
	protected static String getRestUrl()
	{
		String hostname = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.HOSTNAME );
		String port = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.PORT );

		StringBuilder sb = new StringBuilder();
		sb.append( "http://" ).append( hostname ).append( ":" ).append( port );
		return sb.toString();
	}

	/**
	 * Logt Nachrichten.
	 * 
	 * @param clazz
	 *            Aufrufende Testklasse
	 * @param <T>
	 *            Klassentyp
	 * @param method
	 *            Aufrufende Testmethode
	 * @param message
	 *            Nachricht
	 */
	protected static <T> void log( Class<T> clazz, String method, String message )
	{
		if (!StringUtils.equals( m_lastLogClass, clazz.getSimpleName() ) || !StringUtils.equals( m_lastLogMethod, method ))
		{
			System.out.println( "\n" + method.toUpperCase() + " - " + clazz.getName() );
			System.out.println( "=====================================================================================" );
		}

		DateFormat format = new SimpleDateFormat( ApiConstants.ZWITSCHER_DATE_FORMAT );
		String date = format.format( new Date() );

		System.out.println( "[" + date + "] " + message );

		m_lastLogClass = clazz.getSimpleName();
		m_lastLogMethod = method;
	}

}
