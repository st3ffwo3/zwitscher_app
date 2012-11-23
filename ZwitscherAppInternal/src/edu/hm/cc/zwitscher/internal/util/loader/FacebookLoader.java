package edu.hm.cc.zwitscher.internal.util.loader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import edu.hm.basic.logging.BasicLogger;
import edu.hm.cc.zwitscher.internal.object.resource.facebook.FacebookMessage;
import edu.hm.cc.zwitscher.internal.object.resource.facebook.FacebookResponse;
import edu.hm.cc.zwitscher.internal.util.JsonMapper;

/**
 * Utility-Klasse zum Auslesen von Facebook-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class FacebookLoader
{

	/**
	 * Privater Konstruktor.
	 */
	private FacebookLoader()
	{

	}

	private static final String BASE_URL = "https://graph.facebook.com/search?type=post";

	private static final String QUERY_BY_KEYWORD_APPENDER = "&q=%s";

	private static final String QUERY_UNTIL_APPENDER = "&until=%s";

	private static final String QUERY_COUNT_APPENDER = "&limit=%s";

	/**
	 * Liest die Facebook-Nachrichten gefiltert nach den Übergabenparametern aus.
	 * 
	 * @param keyword
	 *            Suchbegriff
	 * @param untilDate
	 *            Bis-Datum
	 * @param count
	 *            Anzahl
	 * @return Facebook-Nachrichten
	 */
	public static List<FacebookMessage> getMessages( String keyword, Date untilDate, Integer count )
	{
		List<FacebookMessage> messages = new ArrayList<FacebookMessage>();
		FacebookResponse fbResponse = null;

		try
		{
			String uri = null;

			if (StringUtils.isNotBlank( keyword ) && untilDate != null && count != null)
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER + QUERY_UNTIL_APPENDER + QUERY_COUNT_APPENDER, keyword,
						untilDate, count );
			}
			else if (StringUtils.isNotBlank( keyword ) && untilDate != null)
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER + QUERY_UNTIL_APPENDER, keyword, untilDate );
			}
			else if (StringUtils.isNotBlank( keyword ) && count != null)
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER + QUERY_COUNT_APPENDER, keyword, count );
			}
			else if (StringUtils.isNotBlank( keyword ))
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER, keyword );
			}
			else
			{
				throw new IllegalAccessException( "keyword is null" );
			}

			BasicLogger.logInfo( FacebookLoader.class, "Facebook-URI: " + uri );
			ClientRequest request = new ClientRequest( uri );
			request.accept( MediaType.APPLICATION_JSON );

			ClientResponse<String> response = request.get( String.class );

			if (response.getStatus() != 200)
			{
				throw new RuntimeException( "Failed : HTTP error code : " + response.getStatus() );
			}

			fbResponse = JsonMapper.fromJSON( response.getEntity(), FacebookResponse.class );
			messages = fbResponse.getData();
			BasicLogger.logInfo( FacebookLoader.class, "Facebook Messages loaded successfully! (count=" + messages.size() + ")" );
		}
		catch (Exception e)
		{
			BasicLogger.logError( FacebookLoader.class, e.getMessage() );
		}

		return messages;
	}

}
