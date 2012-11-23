package edu.hm.cc.zwitscher.test.services.rest;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ProxyFactory;

import edu.hm.cc.zwitscher.api.communication.request.IAtomFeedService;
import edu.hm.cc.zwitscher.api.communication.request.IJsonFeedService;
import edu.hm.cc.zwitscher.test.AbstractTest;

/**
 * Abstrakte Basisklasse für alle REST Service Proxy Tests.
 * 
 * @author Moritz Munte, Franz Mathauser, Stefan Wörner
 */
public abstract class AbstractRestServiceProxyTest extends AbstractTest
{

	/**
	 * Liefert das Proxy-Objekt für den JSON Feed Service.
	 * 
	 * @return JSON Feed Proxy
	 */
	protected static IJsonFeedService getJsonFeedProxy()
	{
		return ProxyFactory.create( IJsonFeedService.class, getRestUrl() );
	}

	/**
	 * Liefert das Proxy-Objekt für den ATOM Feed Service.
	 * 
	 * @return ATOM Feed Proxy
	 */
	protected static IAtomFeedService getAtomFeedProxy()
	{
		return ProxyFactory.create( IAtomFeedService.class, getRestUrl() );
	}

	/**
	 * Liefert das ClientRequest-Objekt für den übergebenen Pfad.
	 * 
	 * @param path
	 *            URI Pfad
	 * @param mediaType
	 *            MediaType
	 * @return ClientRequest Objekt
	 */
	protected static ClientRequest getClient( String path, String mediaType )
	{
		ClientRequest request = new ClientRequest( getRestUrl() + "/" + path );
		if (StringUtils.isNotBlank( mediaType ))
		{
			request.accept( mediaType );
		}

		return request;
	}

}
