package edu.hm.cc.zwitscher.internal.object.resource.twitter;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import edu.hm.cc.zwitscher.api.object.AbstractRessourceObject;
import edu.hm.cc.zwitscher.internal.util.deserializer.TwitterJsonDateDeserializer;

/**
 * Twitter-Nachricht.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class TwitterMessage extends AbstractRessourceObject
{

	private static final long serialVersionUID = 8831381008635572564L;

	private String m_id;

	@JsonProperty( "from_user" )
	private String m_from;

	@JsonProperty( "text" )
	private String m_message;

	@JsonDeserialize( using = TwitterJsonDateDeserializer.class )
	@JsonProperty( "created_at" )
	private Date m_creationDate;

	/**
	 * Standardkonstruktor.
	 */
	public TwitterMessage()
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
	public String getFrom()
	{
		return m_from;
	}

	/**
	 * Setzt das Attribut from.
	 * 
	 * @param from
	 *            zu setzender Wert für das Attribut from
	 */
	public void setFrom( String from )
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
				TwitterMessage.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, TwitterMessage.class, getExclusionList() );
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
		rsb.setUpToClass( TwitterMessage.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}

}
