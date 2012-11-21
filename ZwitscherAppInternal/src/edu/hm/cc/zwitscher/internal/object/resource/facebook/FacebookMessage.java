package edu.hm.cc.zwitscher.internal.object.resource.facebook;

import java.net.URL;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import edu.hm.cc.zwitscher.api.object.AbstractRessourceObject;

/**
 * Facebook-Nachricht.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class FacebookMessage extends AbstractRessourceObject
{

	private static final long serialVersionUID = -1084238952798858173L;

	private String m_id;

	private FacebookPerson m_from;

	private String m_message;

	private String m_type;

	private URL m_link;

	@JsonProperty( "created_time" )
	private Date m_creationDate;

	@JsonProperty( "updated_time" )
	private Date m_updateTime;

	/**
	 * Standardkonstruktor.
	 */
	public FacebookMessage()
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
	 * Liefert das Attribut from.
	 * 
	 * @return from
	 */
	public FacebookPerson getFrom()
	{
		return m_from;
	}

	/**
	 * Setzt das Attribut from.
	 * 
	 * @param from
	 *            zu setzender Wert für das Attribut from
	 */
	public void setFrom( FacebookPerson from )
	{
		m_from = from;
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
	 * Liefert das Attribut type.
	 * 
	 * @return type
	 */
	public String getType()
	{
		return m_type;
	}

	/**
	 * Setzt das Attribut type.
	 * 
	 * @param type
	 *            zu setzender Wert für das Attribut type
	 */
	public void setType( String type )
	{
		m_type = type;
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
	 * Liefert das Attribut updateTime.
	 * 
	 * @return updateTime
	 */
	public Date getUpdateTime()
	{
		return m_updateTime;
	}

	/**
	 * Setzt das Attribut updateTime.
	 * 
	 * @param updateTime
	 *            zu setzender Wert für das Attribut updateTime
	 */
	public void setUpdateTime( Date updateTime )
	{
		m_updateTime = updateTime;
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
				FacebookMessage.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, FacebookMessage.class, getExclusionList() );
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
		rsb.setUpToClass( FacebookMessage.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}

}
