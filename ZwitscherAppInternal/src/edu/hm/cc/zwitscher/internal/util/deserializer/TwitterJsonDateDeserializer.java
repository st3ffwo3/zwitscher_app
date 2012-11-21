package edu.hm.cc.zwitscher.internal.util.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import edu.hm.basic.logging.BasicLogger;
import edu.hm.cc.zwitscher.api.object.ApiConstants;

/**
 * Deselerialisierer für Twitter Datumsformat.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public class TwitterJsonDateDeserializer extends JsonDeserializer<Date>
{

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.codehaus.jackson.map.JsonDeserializer#deserialize(org.codehaus.jackson.JsonParser,
	 *      org.codehaus.jackson.map.DeserializationContext)
	 */
	@Override
	public Date deserialize( JsonParser parser, DeserializationContext context ) throws IOException
	{
		String dateString = parser.getText();

		SimpleDateFormat format = new SimpleDateFormat( ApiConstants.TWITTER_RESPONSE_DATE_FORMAT, Locale.ENGLISH );
		format.setTimeZone( TimeZone.getTimeZone( "GMT" ) );

		try
		{
			return format.parse( dateString );
		}
		catch (ParseException e)
		{
			BasicLogger.logError( this, e.getMessage() );
		}

		format = new SimpleDateFormat( ApiConstants.TWITTER_RESPONSE_DATE_FORMAT, Locale.GERMAN );
		format.setTimeZone( TimeZone.getTimeZone( "GMT" ) );

		try
		{
			return format.parse( dateString );
		}
		catch (ParseException e)
		{
			BasicLogger.logError( this, e.getMessage() );
			throw new RuntimeException( e );
		}
	}

}
