package edu.hm.cc.zwitscher.internal.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.spi.BadRequestException;

import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal;
import edu.hm.cc.zwitscher.internal.converter.DbZwitscherFeedConverter;
import edu.hm.cc.zwitscher.internal.converter.FacebookMessageConverter;
import edu.hm.cc.zwitscher.internal.converter.TwitterMessageConverter;
import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;
import edu.hm.cc.zwitscher.internal.object.resource.facebook.FacebookMessage;
import edu.hm.cc.zwitscher.internal.object.resource.twitter.TwitterMessage;
import edu.hm.cc.zwitscher.internal.util.loader.DbZwitscherFeedLoader;
import edu.hm.cc.zwitscher.internal.util.loader.FacebookLoader;
import edu.hm.cc.zwitscher.internal.util.loader.TwitterLoader;

/**
 * Abstrakte Basisklasse für alle Beans der ZwitscherApp Server Anteile.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public abstract class AbstractBean
{

	/**
	 * Referenz auf die Zwitscher FeedDAO Bean.
	 */
	@EJB
	protected IZwitscherFeedDAOLocal m_zwitscherFeedDAOBean;

	/**
	 * Holt von allen Services die entsprechenden Nachrichten ab und gibt sie als ZwitscherFeed zurück.
	 * 
	 * @param keyword
	 *            Suchbegriff
	 * @param untilDate
	 *            Bis-Datumswert
	 * @param count
	 *            Anzahl
	 * @return ZwitscherFeed Objekte
	 */
	protected List<ZwitscherFeed> getZwitscherFeeds( String keyword, Date untilDate, Integer count )
	{
		if (StringUtils.isBlank( keyword ))
		{
			throw new BadRequestException( "query parameter 'keyword' missing" );
		}

		List<ZwitscherFeed> feeds = new ArrayList<ZwitscherFeed>();
		List<FacebookMessage> fbMessages = null;
		List<TwitterMessage> twMessages = null;
		List<EntityZwitscherFeed> dbMessages = null;

		Integer calcCount = calculateCountPhase1( count );
		// Zwitscher-Nachrichten aus Datenbank holen
		dbMessages = DbZwitscherFeedLoader.getMessages( m_zwitscherFeedDAOBean, keyword, untilDate, calcCount );

		calcCount = calculateCountPhase2( count, dbMessages.size() );
		// Facebook-Nachrichten holen
		fbMessages = FacebookLoader.getMessages( keyword, untilDate, calcCount );
		// Twitter-Nachrichten holen
		twMessages = TwitterLoader.getMessages( keyword, untilDate, calcCount );

		// Facebook-Nachrichten umwandeln
		feeds.addAll( FacebookMessageConverter.convertMessagesToZwitscherFeeds( fbMessages ) );
		// Twitter-Nachrichten umwandeln
		feeds.addAll( TwitterMessageConverter.convertMessagesToZwitscherFeeds( twMessages ) );
		// Db-Zwitscher-Feed-Nachricht einfügen
		feeds.addAll( DbZwitscherFeedConverter.convertMessagesToZwitscherFeeds( dbMessages ) );

		// Zwitscher-Nachrichten sortieren
		Collections.sort( feeds );

		if (count != null)
		{
			// Älteste Element löschen, falls zu viele geholt wurden
			while (feeds.size() > count)
			{
				feeds.remove( 0 );
			}
		}

		return feeds;
	}

	private Integer calculateCountPhase1( Integer count )
	{
		// Wenn count nicht gesetzt, dann alles holen was vorhanden ist
		if (count == null)
		{
			return null;
		}
		// Wenn count durch 3 teilbar, dann jeweils ein drittel von jedem service holen
		else if (count % 3 == 0)
		{
			return count / 3;
		}
		// Ansonsten wenn count nicht durch 3 teilbar, dann jeweils ein drittel +1 von jedem service holen
		else
		{
			return count / 3 + 1;
		}
	}

	private Integer calculateCountPhase2( Integer count, Integer foundMessages )
	{
		// Wenn count nicht gesetzt, dann alles holen was vorhanden ist
		if (count == null)
		{
			return null;
		}
		// Wenn count abzüglich gefundener nachrichten durch 2 teilbar, dann jeweils die hälfte von jedem service holen
		else if ((count - foundMessages) % 2 == 0)
		{
			return (count - foundMessages) / 2;
		}
		// Ansonsten wenn count abzüglich gefundener nachrichten durch 2 teilbar, dann jeweils die hälfte +1 von jedem
		// service holen
		else
		{
			return (count - foundMessages) / 2 + 1;
		}
	}

}
