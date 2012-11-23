package edu.hm.cc.zwitscher.api.object.unmarschaller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.jboss.resteasy.annotations.StringParameterUnmarshallerBinder;

import edu.hm.cc.zwitscher.api.object.ApiConstants;

/**
 * Annotation das RESTeasy Unmarschalling von Datumsweten die einer bestimmten Datumsformatierung entsprechen muessen.
 * 
 * @author Franz Mathauser, Moritz Munte, Stefan Wörner
 */
@Retention( RetentionPolicy.RUNTIME )
@StringParameterUnmarshallerBinder( ZwitscherDateFormatter.class )
public @interface ZwitscherDateFormat
{

	/**
	 * Legt das Datumsformat fest.
	 */
	String value() default ApiConstants.ZWITSCHER_DATE_FORMAT;

}
