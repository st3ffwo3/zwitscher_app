package edu.hm.cc.zwitscher.test.config;

/**
 * Diese Klasse enthält die Konstanten zum Auslesen aus der Konfiguration.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public final class ConfigurationConstants
{

	private ConfigurationConstants()
	{

	}

	/**
	 * ConfigFile Name.
	 */
	public static final String CONFIG_FILE_NAME = "zwitscher_app_config.xml";

	/**
	 * PropertiesFile Name.
	 */
	public static final String PROPERTIES_FILE_NAME = "zwitscher_app_user.properties";

	/**
	 * Hostname. Default: localhost.
	 */
	public static final String HOSTNAME = "cc.zwitscher.test.hostname";

	/**
	 * Port. Default: 80.
	 */
	public static final String PORT = "cc.zwitscher.test.port";

}
