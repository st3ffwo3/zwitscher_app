package edu.hm.cc.zwitscher.api.communication.request;

import java.util.Date;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import edu.hm.cc.zwitscher.api.object.unmarschaller.ZwitscherDateFormat;

/**
 * Interface für den ATOM REST-Feed-Service.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Local
@Path( "/feed" )
public interface IAtomFeedService
{

	/**
	 * Liefert die Zwitscher Feeds im ATOM Format zurück.
	 * 
	 * @param uri
	 *            (Injected by Server) REST URI Infos (Wird injiziert)
	 * @param keyword
	 *            (Required from Client) Zu verwendender Suchbegriff für Abfrage bei Twitter und Facebook
	 * @param untilDate
	 *            (Optional from Client) Filter für das "Bis"-Datum. Es werden nur Nachrichten zurückgegeben, die älter
	 *            als der angegebene Datumswert sind. Erwartetes Datumsformat: "yyyy-MM-dd HH:mm:ss"
	 * @param count
	 *            (Optional form Client) Filter für die maximale Anzahl an Ergebnissen die zurückgebenen werden sollen
	 * @return Zwitscher Feeds (ATOM)
	 */
	@GET
	@Path( "" )
	@Produces( { MediaType.APPLICATION_ATOM_XML } )
	Feed getFeeds( @Context UriInfo uri, @QueryParam( "keyword" ) String keyword,
			@QueryParam( "untilDate" ) @ZwitscherDateFormat Date untilDate, @QueryParam( "count" ) Integer count );

}
