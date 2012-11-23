package edu.hm.cc.zwitscher.internal.converter;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.plugins.providers.atom.Content;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.jboss.resteasy.plugins.providers.atom.Link;
import org.jboss.resteasy.plugins.providers.atom.Person;

import edu.hm.basic.logging.BasicLogger;
import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;

/**
 * Konverter für Twitter-Nachrichten.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class ZwitscherFeedConverter
{

	/**
	 * Privater Konstruktor.
	 */
	private ZwitscherFeedConverter()
	{

	}

	/**
	 * Erzeugt einen Atom Feed (noch ohne Entries).
	 * 
	 * @param uri
	 *            URI
	 * @return ATOM Feed
	 */
	public static Feed createAtomFeed( URI uri )
	{
		Feed feed = new Feed();
		feed.setTitle( "Zwitscher Feed" );
		feed.setUpdated( new Date() );

		if (uri != null)
		{
			feed.setId( uri );

			Link link = new Link();
			link.setHref( uri );
			link.setRel( "self" );
			feed.getLinks().add( link );
		}

		feed.getAuthors().add( new Person( "Moritz Munte" ) );
		feed.getAuthors().add( new Person( "Franz Mathauser" ) );
		feed.getAuthors().add( new Person( "Stefan Wörner" ) );

		return feed;
	}

	/**
	 * Fügt dem ATOM Feed einen neuen Eintrag für den Zwitscher Feed hinzu.
	 * 
	 * @param uri
	 *            URI
	 * @param feed
	 *            ATOM Feed
	 * @param zFeed
	 *            Zwitscher Feed
	 */
	public static void convertMessagesToAtomEntry( URI uri, Feed feed, ZwitscherFeed zFeed )
	{
		if (zFeed == null || uri == null || feed == null)
		{
			return;
		}

		try
		{
			Entry entry = new Entry();
			entry.setId( new URI( uri.toString() + "/entry/" + zFeed.getId() ) );
			entry.setUpdated( zFeed.getUpdateDate() );
			entry.setPublished( zFeed.getCreationDate() );

			if (StringUtils.isBlank( zFeed.getObjectType() ))
			{
				entry.setTitle( zFeed.getSource() + " MESSAGE from " + zFeed.getAuthor() );
			}
			else
			{
				entry.setTitle( zFeed.getSource() + " " + zFeed.getObjectType().toUpperCase() + " from " + zFeed.getAuthor() );
			}

			StringBuilder sbSummary = new StringBuilder();
			if (zFeed.getLink() != null)
			{
				sbSummary.append( "[Link: " ).append( zFeed.getLink() ).append( "] " );
			}
			if (StringUtils.isNotBlank( zFeed.getMessage() ))
			{
				sbSummary.append( zFeed.getMessage() );
			}
			entry.setSummary( sbSummary.toString() );

			if (zFeed != null)
			{
				Content content = new Content();
				content.setJAXBObject( zFeed );

				entry.setContent( content );
			}

			feed.getEntries().add( entry );
		}
		catch (Exception e)
		{
			BasicLogger.logError( TwitterMessageConverter.class, e.getMessage() );
		}
	}

	/**
	 * Fügt dem ATOM Feed neue Einträge für die Zwitscher Feeds hinzu.
	 * 
	 * @param uri
	 *            URI
	 * @param feed
	 *            ATOM Feed
	 * @param zFeeds
	 *            Zwitscher Feeds
	 */
	public static void convertMessagesToAtomEntries( URI uri, Feed feed, List<ZwitscherFeed> zFeeds )
	{
		if (zFeeds == null || uri == null || feed == null)
		{
			return;
		}

		for (ZwitscherFeed zFeed : zFeeds)
		{
			convertMessagesToAtomEntry( uri, feed, zFeed );
		}
	}

}
