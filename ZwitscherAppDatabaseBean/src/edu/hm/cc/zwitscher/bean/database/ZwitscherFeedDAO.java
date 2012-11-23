package edu.hm.cc.zwitscher.bean.database;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.hm.cc.zwitscher.internal.bean.AbstractBean;
import edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal;
import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;
import edu.hm.cc.zwitscher.internal.object.query.ZwitscherFeedQueryConstants;

/**
 * Bean für den Datenbankzugriff auf die Zwitscher Feeds Entität.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Stateless
public class ZwitscherFeedDAO extends AbstractBean implements IZwitscherFeedDAOLocal
{

	@PersistenceContext( unitName = "ZwitscherManager" )
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#create(edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed)
	 */
	@Override
	public EntityZwitscherFeed create( EntityZwitscherFeed zwitscherFeed )
	{
		em.persist( zwitscherFeed );
		em.flush();
		return zwitscherFeed;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#readAll()
	 */
	@Override
	public List<EntityZwitscherFeed> readAll()
	{
		TypedQuery<EntityZwitscherFeed> query = em.createNamedQuery( ZwitscherFeedQueryConstants.GET_ALL_FEEDS,
				EntityZwitscherFeed.class );
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#read(int)
	 */
	@Override
	public EntityZwitscherFeed read( int id )
	{
		return em.find( EntityZwitscherFeed.class, id );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#update(edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed)
	 */
	@Override
	public EntityZwitscherFeed update( EntityZwitscherFeed zwitscherFeed )
	{
		zwitscherFeed = em.merge( zwitscherFeed );
		em.flush();
		return zwitscherFeed;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#delete(edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed)
	 */
	@Override
	public void delete( EntityZwitscherFeed zwitscherFeed )
	{
		em.remove( zwitscherFeed );
		em.flush();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#read(java.lang.String, java.util.Date,
	 *      java.lang.Integer)
	 */
	@Override
	public List<EntityZwitscherFeed> read( String keyword, Date untilDate, Integer count )
	{
		TypedQuery<EntityZwitscherFeed> query = em.createNamedQuery(
				ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE, EntityZwitscherFeed.class );
		query.setParameter( "keyword", "%" + keyword + "%" );
		query.setParameter( "untilDate", untilDate );
		query.setMaxResults( count );
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#read(java.lang.String, java.util.Date)
	 */
	@Override
	public List<EntityZwitscherFeed> read( String keyword, Date untilDate )
	{
		TypedQuery<EntityZwitscherFeed> query = em.createNamedQuery(
				ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE, EntityZwitscherFeed.class );
		query.setParameter( "keyword", "%" + keyword + "%" );
		query.setParameter( "untilDate", untilDate );
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#read(java.lang.String)
	 */
	@Override
	public List<EntityZwitscherFeed> read( String keyword )
	{
		TypedQuery<EntityZwitscherFeed> query = em.createNamedQuery( ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD,
				EntityZwitscherFeed.class );
		query.setParameter( "keyword", "%" + keyword + "%" );
		return query.getResultList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.cc.zwitscher.internal.bean.database.IZwitscherFeedDAOLocal#read(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<EntityZwitscherFeed> read( String keyword, Integer count )
	{
		TypedQuery<EntityZwitscherFeed> query = em.createNamedQuery( ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD,
				EntityZwitscherFeed.class );
		query.setParameter( "keyword", "%" + keyword + "%" );
		query.setMaxResults( count );
		return query.getResultList();
	}

}
