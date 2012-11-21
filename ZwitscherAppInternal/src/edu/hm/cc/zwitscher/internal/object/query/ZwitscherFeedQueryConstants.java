package edu.hm.cc.zwitscher.internal.object.query;

/**
 * Konstanten für Queries der Zwitscher-Feed Entität.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
public final class ZwitscherFeedQueryConstants
{

	private ZwitscherFeedQueryConstants()
	{

	}

	/**
	 * Name der "Alle Zwitscher-Feeds"-Query.
	 */
	public static final String GET_ALL_FEEDS = "GetAllZwitscherFeedsQuery";

	/**
	 * Alle Zwitscher Feeds.
	 */
	public static final String GET_ALL_FEEDS_QUERY = "SELECT f FROM EntityZwitscherFeed f ORDER BY f.id DESC";

	/**
	 * Name der "Alle Zwitscher-Feeds from Keyword"-Query.
	 */
	public static final String GET_ALL_FEEDS_FROM_KEYWORD = "GetAllZwitscherFeedsFromKeywordQuery ";

	/**
	 * Alle Zwitscher Feeds für Suchstring.
	 */
	public static final String GET_ALL_FEEDS_FROM_KEYWORD_QUERY = "SELECT f FROM EntityZwitscherFeed f "
			+ "WHERE f.author LIKE :keyword OR f.message LIKE :keyword ORDER BY f.id DESC";

	/**
	 * Namde der "Alle Zwitscher Feeds für Suchstring begrenzt bis zu einem Datum"-Query.
	 */
	public static final String GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE = "GetAllFeedsFromKeywordAndUntilDateQuery";

	/**
	 * Alle Zwitscher Feeds für Suchstring begrenzt bis zu einem Datum.
	 */
	public static final String GET_ALL_FEEDS_FROM_KEYWORD_AND_UNTILDATE_QUERY = "SELECT f FROM EntityZwitscherFeed f "
			+ "WHERE ( f.creationDate < :untilDate OR f.updateDate < :untilDate ) " + ""
			+ "AND ( f.author LIKE :keyword OR f.message LIKE :keyword ) ORDER BY f.id DESC";

}
