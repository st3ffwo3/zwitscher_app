package edu.hm.cc.zwitscher.api.communication.request;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.hm.cc.zwitscher.api.object.resource.ZwitscherFeed;
import edu.hm.cc.zwitscher.api.object.unmarschaller.ZwitscherDateFormat;

/**
 * Interface für den JSON REST-Feed-Service.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Local
@Path( "/json/feed" )
public interface IJsonFeedService
{

	/**
	 * Liefert die Zwitscher Feeds im JSON Format zurück.
	 * 
	 * @param keyword
	 *            (Required from Client) Zu verwendender Suchbegriff für Abfrage bei Twitter und Facebook
	 * @param untilDate
	 *            (Optional from Client) Filter für das "Bis"-Datum. Es werden nur Nachrichten zurückgegeben, die älter
	 *            als der angegebene Datumswert sind. Erwartetes Datumsformat: "yyyy-MM-dd HH:mm:ss"
	 * @param count
	 *            (Optional form Client) Filter für die maximale Anzahl an Ergebnissen die zurückgebenen werden sollen
	 * @return Zwitscher Feeds (JSON)
	 */
	@GET
	@Path( "" )
	@Produces( { MediaType.APPLICATION_JSON } )
	List<ZwitscherFeed> getFeeds( @QueryParam( "keyword" ) String keyword,
			@QueryParam( "untilDate" ) @ZwitscherDateFormat Date untilDate, @QueryParam( "count" ) Integer count );

	/**
	 * Erzeugt einen neuen Feed in der Zweitscher-Feed Datenbank.
	 * 
	 * @param zwitscherFeed
	 *            Json-Repräsentation des Zwitscher-Feeds
	 * @return Neu erzeugtes Zwitscher-Feed
	 */
	@POST
	@Path( "" )
	@Produces( { MediaType.APPLICATION_JSON } )
	ZwitscherFeed createFeed( ZwitscherFeed zwitscherFeed );

}
