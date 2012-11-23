package edu.hm.cc.zwitscher.internal.util.loader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import edu.hm.basic.logging.BasicLogger;
import edu.hm.cc.zwitscher.api.object.ApiConstants;
import edu.hm.cc.zwitscher.internal.object.resource.twitter.TwitterMessage;
import edu.hm.cc.zwitscher.internal.object.resource.twitter.TwitterResponse;
import edu.hm.cc.zwitscher.internal.util.JsonMapper;

/**
 * Utility-Klasse zum Auslesen von Twitter-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class TwitterLoader
{

	/**
	 * Privater Konstruktor.
	 */
	private TwitterLoader()
	{

	}

	private static final String BASE_URL = "http://search.twitter.com/search.json?";

	private static final String QUERY_BY_KEYWORD_APPENDER = "q=%s";

	private static final String QUERY_UNTIL_APPENDER = "&until=%s";

	private static final String QUERY_COUNT_APPENDER = "&rpp=%s";

	/**
	 * Liest die Twitter-Nachrichte gefiltert nach den Übergabenparametern aus.
	 * 
	 * @param keyword
	 *            Suchbegriff
	 * @param untilDate
	 *            Bis-Datum
	 * @param count
	 *            Anzahl
	 * @return Twitter-Nachrichten
	 */
	public static List<TwitterMessage> getMessages( String keyword, Date untilDate, Integer count )
	{
		List<TwitterMessage> messages = new ArrayList<TwitterMessage>();
		TwitterResponse twResponse = null;

		try
		{
			String uri = null;
			DateFormat untilDateFormat = new SimpleDateFormat( ApiConstants.TWITTER_REQUEST_UNTIL_DATE_FORMAT );
			untilDateFormat.setTimeZone( TimeZone.getTimeZone( "GMT" ) );

			if (StringUtils.isNotBlank( keyword ) && untilDate != null && count != null)
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER + QUERY_UNTIL_APPENDER + QUERY_COUNT_APPENDER, keyword,
						untilDateFormat.format( untilDate ), count );
			}
			else if (StringUtils.isNotBlank( keyword ) && untilDate != null)
			{
				uri = String.format( BASE_URL + QUERY_BY_KEYWORD_APPENDER + QUERY_UNTIL_APPENDER, keyword,
						untilDateFormat.format( untilDate ) );
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

			BasicLogger.logInfo( TwitterLoader.class, "Twitter-URI: " + uri );
			ClientRequest request = new ClientRequest( uri );
			request.accept( MediaType.APPLICATION_JSON );

			ClientResponse<String> response = request.get( String.class );

			if (response.getStatus() != 200)
			{
				throw new RuntimeException( "Failed : HTTP error code : " + response.getStatus() );
			}

			twResponse = JsonMapper.fromJSON( response.getEntity(), TwitterResponse.class );
			messages = twResponse.getResults();
			BasicLogger.logInfo( TwitterLoader.class, "Twitter Messages loaded successfully! (count=" + messages.size() + ")" );
		}
		catch (Exception e)
		{
			BasicLogger.logError( TwitterLoader.class, e.getMessage() );
		}

		return messages;
	}
}
