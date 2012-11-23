package edu.hm.cc.zwitscher.internal.converter;

import java.util.ArrayList;
import java.util.List;

import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.api.object.resource.enumeration.ZwitscherSource;
import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;

/**
 * Konverter für Db-Zwitscher-Feed-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class DbZwitscherFeedConverter
{

	/**
	 * Privater Konstruktor.
	 */
	private DbZwitscherFeedConverter()
	{

	}

	/**
	 * Konvertiert Entity-Zwitscher-Nachricht in Zwitscher Nachricht.
	 * 
	 * @param entityZwitscherFeed
	 *            Entity-Zwitscher-Nachricht
	 * @return Zwitscher Nachricht
	 */
	public static ZwitscherFeed convertMessageToZwitscherFeed( EntityZwitscherFeed entityZwitscherFeed )
	{
		if (entityZwitscherFeed == null)
		{
			return null;
		}

		ZwitscherFeed feed = new ZwitscherFeed();

		feed.setAuthor( entityZwitscherFeed.getAuthor() );
		feed.setCreationDate( entityZwitscherFeed.getCreationDate() );
		feed.setId( entityZwitscherFeed.getId().toString() );
		feed.setLink( entityZwitscherFeed.getLink() );
		feed.setMessage( entityZwitscherFeed.getMessage() );
		feed.setUpdateDate( entityZwitscherFeed.getUpdateDate() );
		feed.setObjectType( entityZwitscherFeed.getObjectType() );
		feed.setSource( ZwitscherSource.ZWITSCHER );

		return feed;
	}

	/**
	 * Konvertiert Db-Zwitscher-Feed-Nachrichten in Zwitscher Nachrichten.
	 * 
	 * @param entityZwitscherFeeds
	 *            Entity-Zwitscher-Nachrichten
	 * @return Zwitscher Nachrichten
	 */
	public static List<ZwitscherFeed> convertMessagesToZwitscherFeeds( List<EntityZwitscherFeed> entityZwitscherFeeds )
	{
		List<ZwitscherFeed> feeds = new ArrayList<ZwitscherFeed>();

		if (entityZwitscherFeeds != null)
		{
			for (EntityZwitscherFeed entityZwitscherFeed : entityZwitscherFeeds)
			{
				feeds.add( convertMessageToZwitscherFeed( entityZwitscherFeed ) );
			}
		}

		return feeds;
	}

	/**
	 * Konvertiert eine Zwitscher-Nachricht in eine Db-Zwitscher-Feed-Nachricht.
	 * 
	 * @param zwitscherFeed
	 *            Ressource Object
	 * @return Zwitscher Nachricht
	 */
	public static EntityZwitscherFeed convertZwitscherFeedToEntity( ZwitscherFeed zwitscherFeed )
	{
		EntityZwitscherFeed entityZwitscherFeed = new EntityZwitscherFeed();
		entityZwitscherFeed.setAuthor( zwitscherFeed.getAuthor() );
		entityZwitscherFeed.setCreationDate( zwitscherFeed.getCreationDate() );
		if (zwitscherFeed.getId() != null)
		{
			entityZwitscherFeed.setId( Integer.parseInt( zwitscherFeed.getId() ) );
		}
		entityZwitscherFeed.setLink( zwitscherFeed.getLink() );
		entityZwitscherFeed.setMessage( zwitscherFeed.getMessage() );
		entityZwitscherFeed.setObjectType( zwitscherFeed.getObjectType() );
		entityZwitscherFeed.setSource( ZwitscherSource.ZWITSCHER );
		entityZwitscherFeed.setUpdateDate( zwitscherFeed.getUpdateDate() );

		return entityZwitscherFeed;
	}

}
