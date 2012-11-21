package edu.hm.cc.zwitscher.internal.object.resource.facebook;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.hm.cc.zwitscher.api.object.AbstractRessourceObject;

/**
 * Facebook Response-Objekt.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class FacebookResponse extends AbstractRessourceObject
{

	private static final long serialVersionUID = -3568721224703847910L;

	private List<FacebookMessage> m_data;

	/**
	 * Standardkonstruktor.
	 */
	public FacebookResponse()
	{

	}

	/**
	 * Liefert das Attribut data.
	 * 
	 * @return data
	 */
	public List<FacebookMessage> getData()
	{
		return m_data;
	}

	/**
	 * Setzt das Attribut data.
	 * 
	 * @param data
	 *            zu setzender Wert für das Attribut data
	 */
	public void setData( List<FacebookMessage> data )
	{
		m_data = data;
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
				FacebookResponse.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, FacebookResponse.class, getExclusionList() );
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
		rsb.setUpToClass( FacebookResponse.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}

}
