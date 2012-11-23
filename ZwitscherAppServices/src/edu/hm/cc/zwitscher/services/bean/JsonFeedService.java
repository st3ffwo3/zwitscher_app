package edu.hm.cc.zwitscher.services.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import edu.hm.cc.zwitscher.api.communication.request.IJsonFeedService;
import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.internal.bean.AbstractBean;
import edu.hm.cc.zwitscher.internal.converter.DbZwitscherFeedConverter;
import edu.hm.cc.zwitscher.internal.interceptor.LoggingInterceptor;
import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;

/**
 * Feed Rest-WebService für JSON-Ausgaben.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Stateless
@Interceptors( LoggingInterceptor.class )
public class JsonFeedService extends AbstractBean implements IJsonFeedService
{

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.api.communication.request.IJsonFeedService#getFeeds(java.lang.String, java.util.Date,
	 *      java.lang.Integer)
	 */
	@Override
	public List<ZwitscherFeed> getFeeds( String keyword, Date untilDate, Integer count )
	{
		// Feeds laden (Twitter, Facebook, DB)
		List<ZwitscherFeed> feeds = getZwitscherFeeds( keyword, untilDate, count );

		return feeds;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.api.communication.request.IJsonFeedService#createFeed(edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed)
	 */
	@Override
	public ZwitscherFeed createFeed( ZwitscherFeed zwitscherFeed )
	{
		// Datumswerte setzen
		zwitscherFeed.setCreationDate( new Date() );
		zwitscherFeed.setUpdateDate( new Date() );

		// Ressource in Entität konvertieren
		EntityZwitscherFeed entityZwitscherFeed = DbZwitscherFeedConverter.convertZwitscherFeedToEntity( zwitscherFeed );
		// Entität in DB schreiben
		entityZwitscherFeed = m_zwitscherFeedDAOBean.create( entityZwitscherFeed );

		return DbZwitscherFeedConverter.convertMessageToZwitscherFeed( entityZwitscherFeed );
	}

}
