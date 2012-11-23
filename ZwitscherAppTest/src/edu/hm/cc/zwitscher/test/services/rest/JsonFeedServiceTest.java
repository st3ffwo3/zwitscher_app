package edu.hm.cc.zwitscher.test.services.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;

import edu.hm.cc.zwitscher.api.object.ApiConstants;
import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.test.JsonMapper;

import junit.framework.Assert;

/**
 * Testklasse für den JSON Feed REST-WebService.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public class JsonFeedServiceTest extends AbstractRestServiceProxyTest
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
		ClientRequest request = getClient( "json/feed?keyword=test", MediaType.APPLICATION_JSON );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		List<ZwitscherFeed> zwitscherFeeds = JsonMapper.fromJSONArray( response.getEntity(), ZwitscherFeed.class );
		Assert.assertNotNull( zwitscherFeeds );
		Assert.assertTrue( zwitscherFeeds.size() >= 20 );

		for (ZwitscherFeed zwitscherFeed : zwitscherFeeds)
		{
			log( this.getClass(), "Get_Feeds", zwitscherFeed.toString() );
		}
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
		ClientRequest request = getClient( "json/feed?keyword=test&count=10", MediaType.APPLICATION_JSON );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		List<ZwitscherFeed> zwitscherFeeds = JsonMapper.fromJSONArray( response.getEntity(), ZwitscherFeed.class );
		Assert.assertNotNull( zwitscherFeeds );
		Assert.assertEquals( 10, zwitscherFeeds.size() );

		for (ZwitscherFeed zwitscherFeed : zwitscherFeeds)
		{
			log( this.getClass(), "Get_Feeds_With_Count", zwitscherFeed.toString() );
		}
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

		ClientRequest request = getClient( "json/feed?keyword=test&untilDate=" + df.format( cal.getTime() ),
				MediaType.APPLICATION_JSON );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		List<ZwitscherFeed> zwitscherFeeds = JsonMapper.fromJSONArray( response.getEntity(), ZwitscherFeed.class );
		Assert.assertNotNull( zwitscherFeeds );
		Assert.assertTrue( zwitscherFeeds.size() >= 20 );

		for (ZwitscherFeed zwitscherFeed : zwitscherFeeds)
		{
			Assert.assertTrue( cal.getTimeInMillis() >= zwitscherFeed.getCreationDate().getTime() );
			log( this.getClass(), "Get_Feeds_With_Date", zwitscherFeed.toString() );
		}
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

		ClientRequest request = getClient( "json/feed?keyword=test&count=10&untilDate=" + df.format( cal.getTime() ),
				MediaType.APPLICATION_JSON );
		ClientResponse<String> response = request.get( String.class );
		Assert.assertEquals( HttpStatus.SC_OK, response.getStatus() );

		List<ZwitscherFeed> zwitscherFeeds = JsonMapper.fromJSONArray( response.getEntity(), ZwitscherFeed.class );
		Assert.assertNotNull( zwitscherFeeds );
		Assert.assertEquals( 10, zwitscherFeeds.size() );

		for (ZwitscherFeed zwitscherFeed : zwitscherFeeds)
		{
			Assert.assertTrue( cal.getTimeInMillis() >= zwitscherFeed.getCreationDate().getTime() );
			log( this.getClass(), "Get_Feeds_With_Date", zwitscherFeed.toString() );
		}
	}

}
