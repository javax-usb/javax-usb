package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import java.util.*;

import javax.usb.Request;
import javax.usb.RequestBundle;
import javax.usb.UsbRuntimeException;

/**
 * Default implementation of the RequestBundle interface.
 * This is implemented using the List part of the Collections API in Java 2.
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class DefaultRequestBundle extends Object implements RequestBundle
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	// 

	/** Creates a new empty RequestBundle */
	public DefaultRequestBundle() {}

	//-------------------------------------------------------------------------
	// Public methods
	// 

	/** @return true if this RequestBundle is currently being submitted */
	public boolean isInSubmission() { return inSubmission; }

	/**
	 * Adds a Request to the bundle
	 * @param request the Request to add
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot accept new Request 
	 * at this time (e.g. its being submitted at that instant).  Also since only
	 * Class or Vendor request can be submitted via bundles an Exception is also thrown
	 * if the Request is a StandardRequest
	 */
	public synchronized void add( Request request ) throws UsbRuntimeException
	{
		if( isInSubmission() ) throw new UsbRuntimeException( "Cannot add to a RequestBundle that is in submission!" );

		//<temp>
		//Need to enforce that StandardRequest cannot be added to bundles
		//</temp>

		list.add( request );
	}

	/**
	 * Removes the Request from the bundle, if it is there
	 * @param request the Request to remove
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot be changed 
	 * at this time (e.g. its being submitted at that instant)
	 */
	public synchronized void remove( Request request ) throws UsbRuntimeException
	{
		if( isInSubmission() ) throw new UsbRuntimeException( "Cannot remove from a RequestBundle that is in submission!" );

		list.remove( request );
	}

	/**
	 * Removes all Request from the bundle
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot be changed 
	 * at this time (e.g. its being submitted at that instant)
	 */
	public void removeAll() throws UsbRuntimeException
	{
		if( isInSubmission() ) throw new UsbRuntimeException( "Cannot remove from a RequestBundle that is in submission!" );

		Iterator iterator = list.iterator();

		while( iterator.hasNext() )
			list.remove( iterator.next() );
    }

	/** @return the current size of this bundle, that is the number of Request in bundle */
	public int size() { return list.size(); }

    /** @return true if this bundle is empty */
    public boolean isEmpty() { return list.isEmpty(); }

	/** @return a RequestIterator to iterate over the Request in this bundle */
	public RequestIterator requestIterator()
	{
		throw new RuntimeException( "Not yet implemented!" );
	}

	/** 
	 * Recycles this bundle so it may be returned when clients call ask the
	 * RequestFactory to create a new bundle.  Should be called once client is
	 * done using this bundle.
	 * @see javax.usb.RequestFactory
	 */
	public void recycle()
	{
		removeAll();
	}

	//-------------------------------------------------------------------------
	// Instance variables
	// 

	private boolean inSubmission = false;

	private List list = new ArrayList();
}
