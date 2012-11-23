/**
 * 
 */
package edu.hm.cc.zwitscher.api.object;

/**
 * Klasse enthaelt alle Konstanten der ZwitscherApp API die auch fuer Clients notwendig bzw. sinnvoll sind.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class ApiConstants
{

	/**
	 * Privater Konstructor.
	 */
	private ApiConstants()
	{

	}

	/**
	 * Konstante mit der Versionsnummer der Server-Anteile.
	 */
	public static final String VERSION = "1.000.0";

	/**
	 * Datumsformatierungs-String.
	 */
	public static final String ZWITSCHER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Datumsformatierungs-String (Twitter Response).
	 */
	public static final String TWITTER_RESPONSE_DATE_FORMAT = "EEE, dd MMMM yyyy kk:mm:ss +SSSS";

	/**
	 * Datumsformatierungs-String (Twitter Request Until-Date).
	 */
	public static final String TWITTER_REQUEST_UNTIL_DATE_FORMAT = "yyyy-MM-dd";

}
