package edu.hm.cc.zwitscher.api.object.resource;

import java.net.URL;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import edu.hm.cc.zwitscher.api.object.AbstractRessourceObject;
import edu.hm.cc.zwitscher.api.object.resource.enumeration.ZwitscherSource;

/**
 * Zwitscher Feed.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@XmlRootElement( name = "Feed" )
@XmlAccessorType( XmlAccessType.FIELD )
@JsonSerialize( include = Inclusion.NON_NULL )
@JsonPropertyOrder( { "id", "author", "message", "objectType", "source", "link", "creationDate", "updateDate" } )
public class ZwitscherFeed extends AbstractRessourceObject implements Comparable<ZwitscherFeed>
{

	private static final long serialVersionUID = -6323440892249657176L;

	@XmlElement( name = "id" )
	private String m_id;

	@XmlElement( name = "author" )
	private String m_author;

	@XmlElement( name = "message" )
	private String m_message;

	@XmlElement( name = "link" )
	private URL m_link;

	@XmlElement( name = "source" )
	private ZwitscherSource m_source;

	@XmlElement( name = "creation-date" )
	private Date m_creationDate;

	@XmlElement( name = "update-date" )
	private Date m_updateDate;

	@XmlElement( name = "message-type" )
	private String m_objectType;

	/**
	 * Standardkonstruktor.
	 */
	public ZwitscherFeed()
	{

	}

	/**
	 * Liefert das Attribut id.
	 * 
	 * @return id
	 */
	public String getId()
	{
		return m_id;
	}

	/**
	 * Setzt das Attribut id.
	 * 
	 * @param id
	 *            zu setzender Wert für das Attribut id
	 */
	public void setId( String id )
	{
		m_id = id;
	}

	/**
	 * Liefert das Attribut author.
	 * 
	 * @return author
	 */
	public String getAuthor()
	{
		return m_author;
	}

	/**
	 * Setzt das Attribut author.
	 * 
	 * @param author
	 *            zu setzender Wert für das Attribut author
	 */
	public void setAuthor( String author )
	{
		m_author = author;
	}

	/**
	 * Liefert das Attribut message.
	 * 
	 * @return message
	 */
	public String getMessage()
	{
		return m_message;
	}

	/**
	 * Setzt das Attribut message.
	 * 
	 * @param message
	 *            zu setzender Wert für das Attribut message
	 */
	public void setMessage( String message )
	{
		m_message = message;
	}

	/**
	 * Liefert das Attribut link.
	 * 
	 * @return link
	 */
	public URL getLink()
	{
		return m_link;
	}

	/**
	 * Setzt das Attribut link.
	 * 
	 * @param link
	 *            zu setzender Wert für das Attribut link
	 */
	public void setLink( URL link )
	{
		m_link = link;
	}

	/**
	 * Liefert das Attribut source.
	 * 
	 * @return source
	 */
	public ZwitscherSource getSource()
	{
		return m_source;
	}

	/**
	 * Setzt das Attribut source.
	 * 
	 * @param source
	 *            zu setzender Wert für das Attribut source
	 */
	public void setSource( ZwitscherSource source )
	{
		m_source = source;
	}

	/**
	 * Liefert das Attribut creationDate.
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate()
	{
		return m_creationDate;
	}

	/**
	 * Setzt das Attribut creationDate.
	 * 
	 * @param creationDate
	 *            zu setzender Wert für das Attribut creationDate
	 */
	public void setCreationDate( Date creationDate )
	{
		m_creationDate = creationDate;
	}

	/**
	 * Liefert das Attribut updateDate.
	 * 
	 * @return updateDate
	 */
	public Date getUpdateDate()
	{
		return m_updateDate;
	}

	/**
	 * Setzt das Attribut updateDate.
	 * 
	 * @param updateDate
	 *            zu setzender Wert für das Attribut updateDate
	 */
	public void setUpdateDate( Date updateDate )
	{
		m_updateDate = updateDate;
	}

	/**
	 * Liefert das Attribut objectType.
	 * 
	 * @return objectType
	 */
	public String getObjectType()
	{
		return m_objectType;
	}

	/**
	 * Setzt das Attribut objectType.
	 * 
	 * @param objectType
	 *            zu setzender Wert für das Attribut objectType
	 */
	public void setObjectType( String objectType )
	{
		m_objectType = objectType;
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
				ZwitscherFeed.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, ZwitscherFeed.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#toString()
	 */
	@Override
	public String toString()
	{
		ReflectionToStringBuilder rsb = new ReflectionToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
		rsb.setAppendStatics( false );
		rsb.setAppendTransients( true );
		rsb.setUpToClass( ZwitscherFeed.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( ZwitscherFeed feed )
	{
		if (this.getCreationDate() == null && feed.getCreationDate() == null)
		{
			return 0;
		}

		if (this.getCreationDate() == null)
		{
			return 1;
		}

		if (feed.getCreationDate() == null)
		{
			return -1;
		}

		return this.getCreationDate().compareTo( feed.getCreationDate() );
	}
}
