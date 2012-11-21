package edu.hm.cc.zwitscher.test.services.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import edu.hm.cc.zwitscher.api.object.ApiConstants;

import junit.framework.Assert;

/**
 * Testklasse für den ATOM Feed REST-WebService.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public class AtomFeedServiceTest extends AbstractRestServiceProxyTest
{

	/**
	 * Testet den JSON Feed Service.
	 * 
	 * @throws Exception
	 *             Fehler bei der Abfrage
	 */
	@Test
	public void testGetFeeds() throws Exception
	{
		ClientRequest request = getClient( "feed?keyword=test", null );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		Assert.assertNotNull( response );
	}

	/**
	 * Testet den JSON Feed Service mit Anzahl.
	 * 
	 * @throws Exception
	 *             Fehler bei der Abfrage
	 */
	@Test
	public void testGetFeedsWithCount() throws Exception
	{
		ClientRequest request = getClient( "feed?keyword=test&count=10", null );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		Assert.assertNotNull( response );
	}

	/**
	 * Testet den JSON Feed Service mit Datum.
	 * 
	 * @throws Exception
	 *             Fehler bei der Abfrage
	 */
	@Test
	public void testGetFeedsWithDate() throws Exception
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime( new Date() );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MILLISECOND, 0 );

		DateFormat df = new SimpleDateFormat( ApiConstants.ZWITSCHER_DATE_FORMAT );

		ClientRequest request = getClient( "feed?keyword=test&untilDate=" + df.format( cal.getTime() ), null );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		Assert.assertNotNull( response );
	}

	/**
	 * Testet den JSON Feed Service mit Anzahl und Datum.
	 * 
	 * @throws Exception
	 *             Fehler bei der Abfrage
	 */
	@Test
	public void testGetFeedsWithCountAndDate() throws Exception
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime( new Date() );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MILLISECOND, 0 );

		DateFormat df = new SimpleDateFormat( ApiConstants.ZWITSCHER_DATE_FORMAT );

		ClientRequest request = getClient( "feed?keyword=test&count=10&untilDate=" + df.format( cal.getTime() ), null );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		Assert.assertNotNull( response );
	}

}
