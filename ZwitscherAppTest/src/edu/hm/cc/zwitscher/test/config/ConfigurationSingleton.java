package edu.hm.cc.zwitscher.test.config;

/**
 * Singleton Implementierung für den globalen Zugriff auf die Konfiguration.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public final class ConfigurationSingleton extends ZwitscherAppConfiguration
{

	private static ConfigurationSingleton m_Config = new ConfigurationSingleton();

	/**
	 * Konstruktor.
	 */
	private ConfigurationSingleton()
	{
		super( ConfigurationConstants.CONFIG_FILE_NAME );
	}

	/**
	 * Singleton Factory Methode.
	 * 
	 * @return unique Instanz von {@link ConfigurationSingleton}
	 */
	public static ConfigurationSingleton getInstance()
	{
		synchronized (m_Config)
		{
			return m_Config;
		}
	}
}
