package edu.hm.cc.zwitscher.internal.object.entity;

import java.net.URL;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.hm.cc.zwitscher.api.object.resource.enumeration.ZwitscherSource;
import edu.hm.cc.zwitscher.internal.object.AbstractEntityObject;
import edu.hm.cc.zwitscher.internal.object.query.ZwitscherFeedQueryConstants;

/**
 * Entität für den Zwitscher-Feed. Es werden alle benötigten Feed-Information in dieser Klasse gehalten, wie
 * beispielsweise der Name, die Adresse und eMail-Adresse.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Entity
@Table( name = "feed" )
@NamedQueries( {
		@NamedQuery( name = ZwitscherFeedQueryConstants.GET_ALL_FEEDS, query = ZwitscherFeedQueryConstants.GET_ALL_FEEDS_QUERY ),
		@NamedQuery(	name = ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD,
						query = ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD_QUERY ),
		@NamedQuery(	name = ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE,
						query = ZwitscherFeedQueryConstants.GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE_QUERY )

} )
public class EntityZwitscherFeed extends AbstractEntityObject
{

	private static final long serialVersionUID = -5524766868204953707L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer id;

	private String author;

	private String message;

	private URL link;

	@Enumerated( value = EnumType.ORDINAL )
	private ZwitscherSource source;

	@Column( name = "creation_date" )
	private Date creationDate;

	@Column( name = "update_date" )
	private Date updateDate;

	@Column( name = "object_type" )
	private String objectType;

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Liefert das Attribut author.
	 * 
	 * @return author
	 */
	public String getAuthor()
	{
		return author;
	}

	/**
	 * Liefert das Attribut message.
	 * 
	 * @return message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Liefert das Attribut link.
	 * 
	 * @return link
	 */
	public URL getLink()
	{
		return link;
	}

	/**
	 * Liefert das Attribut source.
	 * 
	 * @return source
	 */
	public ZwitscherSource getSource()
	{
		return source;
	}

	/**
	 * Liefert das Attribut creationDate.
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * Liefert das Attribut updateDate.
	 * 
	 * @return updateDate
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}

	/**
	 * Liefert das Attribut objectType.
	 * 
	 * @return objectType
	 */
	public String getObjectType()
	{
		return objectType;
	}

	/**
	 * Setzt das Attribut id.
	 * 
	 * @param id
	 *            zu setzender Wert für das Attribut id
	 */
	public void setId( Integer id )
	{
		this.id = id;
	}

	/**
	 * Setzt das Attribut author.
	 * 
	 * @param author
	 *            zu setzender Wert für das Attribut author
	 */
	public void setAuthor( String author )
	{
		this.author = author;
	}

	/**
	 * Setzt das Attribut message.
	 * 
	 * @param message
	 *            zu setzender Wert für das Attribut message
	 */
	public void setMessage( String message )
	{
		this.message = message;
	}

	/**
	 * Setzt das Attribut link.
	 * 
	 * @param link
	 *            zu setzender Wert für das Attribut link
	 */
	public void setLink( URL link )
	{
		this.link = link;
	}

	/**
	 * Setzt das Attribut source.
	 * 
	 * @param source
	 *            zu setzender Wert für das Attribut source
	 */
	public void setSource( ZwitscherSource source )
	{
		this.source = source;
	}

	/**
	 * Setzt das Attribut creationDate.
	 * 
	 * @param creationDate
	 *            zu setzender Wert für das Attribut creationDate
	 */
	public void setCreationDate( Date creationDate )
	{
		this.creationDate = creationDate;
	}

	/**
	 * Setzt das Attribut updateDate.
	 * 
	 * @param updateDate
	 *            zu setzender Wert für das Attribut updateDate
	 */
	public void setUpdateDate( Date updateDate )
	{
		this.updateDate = updateDate;
	}

	/**
	 * Setzt das Attribut objectType.
	 * 
	 * @param objectType
	 *            zu setzender Wert für das Attribut objectType
	 */
	public void setObjectType( String objectType )
	{
		this.objectType = objectType;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#getExclusionList()
	 */
	@Override
	protected String[] getExclusionList()
	{
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode( INITIAL_NON_ZERO_ODD_NUMBER, MULTIPLIER_NON_ZERO_ODD_NUMBER, this, true,
				EntityZwitscherFeed.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, EntityZwitscherFeed.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( EntityZwitscherFeed.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}
}
