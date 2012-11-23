package edu.hm.cc.zwitscher.internal.converter;

import java.util.ArrayList;
import java.util.List;

import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.api.object.resource.enumeration.ZwitscherSource;
import edu.hm.cc.zwitscher.internal.object.resource.twitter.TwitterMessage;

/**
 * Konverter für Zwitscher-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class TwitterMessageConverter
{

	/**
	 * Privater Konstruktor.
	 */
	private TwitterMessageConverter()
	{

	}

	/**
	 * Konvertiert Twitter-Nachricht in Zwitscher Nachricht.
	 * 
	 * @param twMessage
	 *            Twitter-Nachricht
	 * @return Zwitscher Nachricht
	 */
	public static ZwitscherFeed convertMessageToZwitscherFeed( TwitterMessage twMessage )
	{
		if (twMessage == null)
		{
			return null;
		}

		ZwitscherFeed feed = new ZwitscherFeed();
		feed.setAuthor( twMessage.getFrom() );
		feed.setCreationDate( twMessage.getCreationDate() );
		feed.setId( twMessage.getId() );
		// feed.setLink( twMessage.getLink() );
		feed.setMessage( twMessage.getMessage() );
		feed.setUpdateDate( twMessage.getCreationDate() );
		feed.setObjectType( "MESSAGE" );
		feed.setSource( ZwitscherSource.TWITTER );

		return feed;
	}

	/**
	 * Konvertiert Twitter-Nachrichten in Zwitscher Nachrichten.
	 * 
	 * @param twMessages
	 *            Twitter-Nachrichten
	 * @return Zwitscher Nachrichten
	 */
	public static List<ZwitscherFeed> convertMessagesToZwitscherFeeds( List<TwitterMessage> twMessages )
	{
		List<ZwitscherFeed> feeds = new ArrayList<ZwitscherFeed>();

		if (twMessages != null)
		{
			for (TwitterMessage twMessage : twMessages)
			{
				feeds.add( convertMessageToZwitscherFeed( twMessage ) );
			}
		}

		return feeds;
	}

}
