package edu.hm.cc.zwitscher.internal.object.resource.twitter;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.hm.cc.zwitscher.api.object.AbstractRessourceObject;

/**
 * Twitter Response-Objekt.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class TwitterResponse extends AbstractRessourceObject
{

	private static final long serialVersionUID = -7802015880078797782L;

	private List<TwitterMessage> m_results;

	/**
	 * Standardkonstruktor.
	 */
	public TwitterResponse()
	{

	}

	/**
	 * Liefert das Attribut results.
	 * 
	 * @return results
	 */
	public List<TwitterMessage> getResults()
	{
		return m_results;
	}

	/**
	 * Setzt das Attribut results.
	 * 
	 * @param results
	 *            zu setzender Wert für das Attribut results
	 */
	public void setResults( List<TwitterMessage> results )
	{
		m_results = results;
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
				TwitterResponse.class, getExclusionList() );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.hm.basic.object.AbstractBasicObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return EqualsBuilder.reflectionEquals( this, obj, true, TwitterResponse.class, getExclusionList() );
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
		rsb.setUpToClass( TwitterResponse.class );
		rsb.setExcludeFieldNames( getExclusionList() );
		return rsb.toString();
	}

}
