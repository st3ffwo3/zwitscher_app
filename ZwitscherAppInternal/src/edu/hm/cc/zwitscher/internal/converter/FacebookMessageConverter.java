package edu.hm.cc.zwitscher.internal.converter;

import java.util.ArrayList;
import java.util.List;

import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.api.object.resource.enumeration.ZwitscherSource;
import edu.hm.cc.zwitscher.internal.object.resource.facebook.FacebookMessage;

/**
 * Konverter für Facebook-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class FacebookMessageConverter
{

	/**
	 * Privater Konstruktor.
	 */
	private FacebookMessageConverter()
	{

	}

	/**
	 * Konvertiert Facebook-Nachricht in Zwitscher Nachricht.
	 * 
	 * @param fbMessage
	 *            Facebook-Nachricht
	 * @return Zwitscher Nachricht
	 */
	public static ZwitscherFeed convertMessageToZwitscherFeed( FacebookMessage fbMessage )
	{
		if (fbMessage == null)
		{
			return null;
		}

		ZwitscherFeed feed = new ZwitscherFeed();
		if (fbMessage.getFrom() != null)
		{
			feed.setAuthor( fbMessage.getFrom().getName() );
		}
		feed.setCreationDate( fbMessage.getCreationDate() );
		feed.setId( fbMessage.getId() );
		feed.setLink( fbMessage.getLink() );
		feed.setMessage( fbMessage.getMessage() );
		feed.setUpdateDate( fbMessage.getUpdateTime() );
		feed.setObjectType( fbMessage.getType() );
		feed.setSource( ZwitscherSource.FACEBOOK );

		return feed;
	}

	/**
	 * Konvertiert Facebook-Nachrichten in Zwitscher Nachrichten.
	 * 
	 * @param fbMessages
	 *            Facebook-Nachrichten
	 * @return Zwitscher Nachrichten
	 */
	public static List<ZwitscherFeed> convertMessagesToZwitscherFeeds( List<FacebookMessage> fbMessages )
	{
		List<ZwitscherFeed> feeds = new ArrayList<ZwitscherFeed>();

		if (fbMessages != null)
		{
			for (FacebookMessage fbMessage : fbMessages)
			{
				feeds.add( convertMessageToZwitscherFeed( fbMessage ) );
			}
		}

		return feeds;
	}

}
