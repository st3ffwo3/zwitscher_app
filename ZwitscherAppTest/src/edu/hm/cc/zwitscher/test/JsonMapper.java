package edu.hm.cc.zwitscher.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * Generische Hilfsklasse für das JSON Mapping.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public final class JsonMapper
{

	private ObjectMapper m_objectMapper;

	private static JsonMapper m_instance;

	private JsonMapper()
	{
		m_objectMapper = new ObjectMapper();
	}

	private static JsonMapper getInstance()
	{
		if (m_instance == null)
		{
			m_instance = new JsonMapper();
		}
		return m_instance;
	}

	/**
	 * Konvertiert ein POJO in die entsprechende JSON Representation.
	 * 
	 * @param resourceObject
	 *            Ressource POJO
	 * @return JSON String-Representation
	 * @throws IOException
	 *             IO Fehler
	 */
	public static String toJSON( final Object resourceObject ) throws IOException
	{
		if (resourceObject == null)
		{
			return null;
		}

		return getInstance().m_objectMapper.writeValueAsString( resourceObject );
	}

	/**
	 * Konvertiert die JSON Representation in die übergebene POJO Klasse.
	 * 
	 * @param json
	 *            JSON String-Representation
	 * @param clazz
	 *            POJO Klasse
	 * @param <T>
	 *            POJO Typ
	 * @return Ressource POJO
	 * @throws IOException
	 *             IO Fehler
	 */
	public static <T> T fromJSON( final InputStream json, final Class<T> clazz ) throws IOException
	{
		if (json == null)
		{
			return null;
		}

		return getInstance().m_objectMapper.readValue( json, clazz );
	}

	/**
	 * Konvertiert die JSON Representation in die übergebene POJO Klasse.
	 * 
	 * @param json
	 *            JSON String-Representation
	 * @param clazz
	 *            POJO Klasse
	 * @param <T>
	 *            POJO Typ
	 * @return Ressource POJO
	 * @throws IOException
	 *             IO Fehler
	 */
	public static <T> T fromJSON( final String json, final Class<T> clazz ) throws IOException
	{
		if (json == null)
		{
			return null;
		}

		return getInstance().m_objectMapper.readValue( json, clazz );
	}

	/**
	 * Konvertiert die JSON Representation eines Arrays in eine ArrayList vom Typ der übergebenen POJO Klasse.
	 * 
	 * @param json
	 *            JSON String-Representation
	 * @param clazz
	 *            POJO Klasse
	 * @param <T>
	 *            POJO Typ
	 * @return Liste mit Ressource POJOs
	 * @throws IOException
	 *             IO Fehler
	 */
	public static <T> List<T> fromJSONArray( final InputStream json, final Class<T> clazz ) throws IOException
	{
		if (json == null)
		{
			return null;
		}

		JavaType type = getInstance().m_objectMapper.getTypeFactory().constructCollectionType( List.class, clazz );
		return getInstance().m_objectMapper.readValue( json, type );
	}

	/**
	 * Konvertiert die JSON Representation eines Arrays in eine ArrayList vom Typ der übergebenen POJO Klasse.
	 * 
	 * @param json
	 *            JSON String-Representation
	 * @param clazz
	 *            POJO Klasse
	 * @param <T>
	 *            POJO Typ
	 * @return Liste mit Ressource POJOs
	 * @throws IOException
	 *             IO Fehler
	 */
	public static <T> List<T> fromJSONArray( final String json, final Class<T> clazz ) throws IOException
	{
		if (json == null)
		{
			return null;
		}

		JavaType type = getInstance().m_objectMapper.getTypeFactory().constructCollectionType( List.class, clazz );
		return getInstance().m_objectMapper.readValue( json, type );
	}
}
