package edu.hm.cc.zwitscher.internal.bean.database;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import edu.hm.cc.zwitscher.internal.object.entity.EntityZwitscherFeed;

/**
 * Interface der Bean für den Datenbankzugriff auf die Zwitscher Feeds.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Local
public interface IZwitscherFeedDAOLocal
{

	/**
	 * Estellt einen neuen Zwitscher-Feed in der Datenbank.
	 * 
	 * @param zwitscherFeed
	 *            Zu erstellender Feed
	 * @return Erstellter Feed
	 */
	EntityZwitscherFeed create( EntityZwitscherFeed zwitscherFeed );

	/**
	 * Liest aller Zwitscher-Feeds aus der Datenbank aus.
	 * 
	 * @return Liste mit allen Zwitscher-Feeds
	 */
	List<EntityZwitscherFeed> readAll();

	/**
	 * Liest einen Zwitscher-Feed anhand des Feed-Identifikators aus der Datenbank aus.
	 * 
	 * @param id
	 *            Feed-Identifikator
	 * @return ZwitscherFeed
	 */
	EntityZwitscherFeed read( int id );

	/**
	 * Aktualisiert einen Zwitscher-Feed in der Datenbank.
	 * 
	 * @param zwitscherFeed
	 *            Zu aktualisierender Zwitscher-Feed
	 * @return Aktualiserter Zwitscher-Feed
	 */
	EntityZwitscherFeed update( EntityZwitscherFeed zwitscherFeed );

	/**
	 * Entfernt einen Zwitscher-Feed aus der Datenbank.
	 * 
	 * @param zwitscherFeed
	 *            Zu entfernender Feed
	 */
	void delete( EntityZwitscherFeed zwitscherFeed );

	/**
	 * Liest Zwitscher-Feeds anhand des Keywords, Bis-Datum aus der Datenbank aus. Die Anzahl wird dabei beschränkt auf
	 * den übergebenen Parameter count.
	 * 
	 * @param keyword
	 *            Suchstring
	 * @param untilDate
	 *            Maximaldatum des Feeds
	 * @param count
	 *            Maximalanzahl der Feeds
	 * @return Liste mit jeweiligen Zwitscher-Feeds
	 */
	List<EntityZwitscherFeed> read( String keyword, Date untilDate, Integer count );

	/**
	 * Liest Zwitscher-Feeds anhand des Keywords und Bis-Datum aus der Datenbank aus.
	 * 
	 * @param keyword
	 *            Suchstring
	 * @param untilDate
	 *            Maximaldatum des Feeds
	 * @return Liste mit jeweiligen Zweitscher-Feeds
	 */
	List<EntityZwitscherFeed> read( String keyword, Date untilDate );

	/**
	 * Liest Zwitscher-Feeds anhand des Keywords aus der Datenbank aus. Die Anzahl wird dabei beschränkt auf den
	 * übergebenen Parameter count.
	 * 
	 * @param keyword
	 *            Suchstring
	 * @param count
	 *            Maximalanzahl der Feeds
	 * @return Liste mit jeweiligen Zwitscher-Feeds
	 */
	List<EntityZwitscherFeed> read( String keyword, Integer count );

	/**
	 * Liest Zwitscher-Feeds anhand des Keywords aus der Datenbank aus.
	 * 
	 * @param keyword
	 *            Suchstring
	 * @return Liste mit jeweiligen Zwitscher-Feeds
	 */
	List<EntityZwitscherFeed> read( String keyword );

}
