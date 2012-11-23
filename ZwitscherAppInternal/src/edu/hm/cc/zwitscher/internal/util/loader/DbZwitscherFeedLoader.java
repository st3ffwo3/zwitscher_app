package edu.hm.cc.zwitscher.internal.util.loader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.hm.basic.logging.BasicLogger;
import edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal;
import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;

/**
 * Utility-Klasse zum Auslesen von Zwitscher-Feed-Nachrichten aus der Datenbank.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class DbZwitscherFeedLoader
{

	/**
	 * Privater Konstruktor.
	 */
	private DbZwitscherFeedLoader()
	{

	}

	/**
	 * Liest die Zwitscher-Feed-Nachrichten gefiltert nach den Übergabenparametern aus.
	 * 
	 * @param zwitscherFeedDAOBean
	 *            Db-Zwitscher-Manager
	 * @param keyword
	 *            Suchbegriff
	 * @param untilDate
	 *            Bis-Datum
	 * @param count
	 *            Anzahl
	 * @return Zwitscher-Feed-Nachrichten
	 */
	public static List<EntityZwitscherFeed> getMessages( IZwitscherFeedDAOLocal zwitscherFeedDAOBean, String keyword,
			Date untilDate, Integer count )
	{
		List<EntityZwitscherFeed> messages = new ArrayList<EntityZwitscherFeed>();

		try
		{
			BasicLogger.logInfo( FacebookLoader.class, "DB Request: keyword=" + keyword + ", untilDate=" + untilDate + ", count="
					+ count );

			if (StringUtils.isNotBlank( keyword ) && untilDate != null && count != null)
			{
				messages = zwitscherFeedDAOBean.read( keyword, untilDate, count );
			}
			else if (StringUtils.isNotBlank( keyword ) && untilDate != null)
			{
				messages = zwitscherFeedDAOBean.read( keyword, untilDate );
			}
			else if (StringUtils.isNotBlank( keyword ) && count != null)
			{
				messages = zwitscherFeedDAOBean.read( keyword, count );
			}
			else if (StringUtils.isNotBlank( keyword ))
			{
				messages = zwitscherFeedDAOBean.read( keyword );
			}
			else
			{
				throw new IllegalAccessException( "keyword is null" );
			}

			BasicLogger.logInfo( DbZwitscherFeedLoader.class,
					"Zwitscher-Feed Messages loaded successfully! (count=" + messages.size() + ")" );
		}
		catch (Exception e)
		{
			BasicLogger.logError( DbZwitscherFeedLoader.class, e.getMessage() );
		}

		return messages;
	}

}
