package edu.hm.cc.zwitscher.services.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import edu.hm.cc.zwitscher.api.communication.request.IAtomFeedService;
import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.internal.bean.AbstractBean;
import edu.hm.cc.zwitscher.internal.converter.ZwitscherFeedConverter;
import edu.hm.cc.zwitscher.internal.interceptor.LoggingInterceptor;

/**
 * Feed Rest-WebService für ATOM-Ausgaben.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class AtomFeedService extends AbstractBean implements IAtomFeedService
{

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.api.communication.request.IAtomFeedService#getFeeds(javax.ws.rs.core.UriInfo,
	 *      java.lang.String, java.util.Date, java.lang.Integer)
	 */
	@Override
	public Feed getFeeds( UriInfo uri, String keyword, Date untilDate, Integer count )
	{
		// Feeds laden (Twitter, Facebook, DB)
		List<ZwitscherFeed> feeds = getZwitscherFeeds( keyword, untilDate, count );

		// AtomFeed erzeugen
		Feed feed = ZwitscherFeedConverter.createAtomFeed( uri.getAbsolutePath() );
		// AtomFeed Einträge erzeugen
		ZwitscherFeedConverter.convertMessagesToAtomEntries( uri.getAbsolutePath(), feed, feeds );

		return feed;
	}

}
