package javax.usb.util;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

import javax.usb.Request;
import javax.usb.RequestConst;
import javax.usb.RequestBundle;
import javax.usb.UsbRuntimeException;

/**
 * Default implementation of the RequestBundle interface.
 * This is implemented using the List part of the Collections API in Java 2.
 * <p>
 * <i>NOTE: all methods are synchronized making this default implementation
 * thread safe (similar to the original java.util.Vector class)
 * </p>
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

		if( isStandardRequest( request ) ) throw new UsbRuntimeException( "Cannot add a StandardRequest to a RequestBundle" );

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
	public synchronized void removeAll() throws UsbRuntimeException
	{
		if( isInSubmission() ) throw new UsbRuntimeException( "Cannot remove from a RequestBundle that is in submission!" );

		list.clear();
    }

	/** @return the current size of this bundle, that is the number of Request in bundle */
	public synchronized int size() { return list.size(); }

    /** @return true if this bundle is empty */
    public synchronized boolean isEmpty() { return list.isEmpty(); }

	/** @return a RequestIterator to iterate over the Request in this bundle */
	public synchronized RequestIterator requestIterator() { return this.new DefaultRequestIterator( list ); }

	/** 
	 * Recycles this bundle so it may be returned when clients call ask the
	 * RequestFactory to create a new bundle.  Should be called once client is
	 * done using this bundle.
	 * @see javax.usb.RequestFactory
	 */
	public synchronized void recycle() 
	{ 
		clean();
	}

	/**
	 * Removes all Request objects from this RequestBundle.  Since these Request
	 * objects are not owned by this bundle (they were created and added to it) then
	 * we do not call the Request.recycle() method on them
	 */
	public void clean() { removeAll(); }

	//-------------------------------------------------------------------------
	// Protected methods
	// 

	/** @return true if the Request object is a StandardRequest otherwise false */
	protected boolean isStandardRequest( Request request )
	{
		byte requestCode = request.getRequestCode();

		switch( requestCode )
		{
			case RequestConst.REQUEST_GET_STATUS:
			case RequestConst.REQUEST_CLEAR_FEATURE:
			case RequestConst.REQUEST_GET_STATE:
			case RequestConst.REQUEST_SET_FEATURE:
			case RequestConst.REQUEST_SET_ADDRESS:
			case RequestConst.REQUEST_GET_DESCRIPTOR:
			case RequestConst.REQUEST_SET_DESCRIPTOR:
			case RequestConst.REQUEST_GET_CONFIGURATION:
			case RequestConst.REQUEST_SET_CONFIGURATION:
			case RequestConst.REQUEST_GET_INTERFACE:
			case RequestConst.REQUEST_SET_INTERFACE:
			case RequestConst.REQUEST_SYNCH_FRAME:
				return true;
		}

		return false;
	}

	//-------------------------------------------------------------------------
	// Instance variables
	// 

	protected boolean inSubmission = false;

	private List list = new ArrayList();

	//-------------------------------------------------------------------------
	// Inner classes
	// 

	/**
	 * This is a default implementation of the RequestIterator interface
	 * used by this class to allow iteration over the Request objects in this 
	 * RequestBundle
	 * @author E. Michael Maximilien
	 */
	private class DefaultRequestIterator extends Object implements RequestIterator
	{
		//---------------------------------------------------------------------
		// Ctor
		//

		/**
		 * Creates a DefaultRequestIterator with the List of Request objects
		 * @param requestList the Request List object
		 */
		public DefaultRequestIterator( List requestList )
		{
			listIterator = requestList.listIterator();
		}

		//---------------------------------------------------------------------
		// RequestIterator interface methods
		//

		/** @return true if this Iterator is not yet at the end */
		public boolean hasNext() { return listIterator.hasNext(); }

		/** @return the next Request in this iteration */
		public Request nextRequest() { return (Request)listIterator.next(); }

		//---------------------------------------------------------------------
		// Instance variables
		//

		private ListIterator listIterator = null;
	}
}
