package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.util.*;

/**
 * Defines an aggregation of Request objects (i.e. a bundle) which can be used to send
 * uninterrupted requests to a device.  Only Vendor and/or Class request can be bundled
 * standard request cannot be added to a BundleRequest.  If a Standard request is added
 * to a RequestBundle this action will result in an exception being thrown.
 * <p>
 * Note that this bundle is not a Request, it just contains them and allows for
 * aggregation of Request objects only.
 * </p>
 * <p>Like Request objects RequestBundles are created using the RequestFactory that can be
 * accessed in the UsbServices.  They should also be recycled like Request objects
 * </p>
 * @see javax.usb.os.UsbServices#getRequestFactory
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface RequestBundle
{
	//-------------------------------------------------------------------------
	// Public methods
	// 

	/** @return true if this RequestBundle is currently being submitted */
	public boolean isInSubmission();

	/**
	 * Adds a Request to the bundle
	 * @param request the Request to add
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot accept new Request 
	 * at this time (e.g. its being submitted at that instant).  Also since only
	 * Class or Vendor request can be submitted via bundles an Exception is also thrown
	 * if the Request is a StandardRequest
	 */
	public void add( Request request ) throws UsbRuntimeException;

	/**
	 * Removes the Request from the bundle, if it is there
	 * @param request the Request to remove
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot be changed 
	 * at this time (e.g. its being submitted at that instant)
	 */
	public void remove( Request request ) throws UsbRuntimeException;

	/**
	 * Removes all Request from the bundle
	 * @throws javax.usb.UsbRuntimeException if the bundle cannot be changed 
	 * at this time (e.g. its being submitted at that instant)
	 */
	public void removeAll() throws UsbRuntimeException;

	/** @return the current size of this bundle, that is the number of Request in bundle */
	public int size();

    /** @return true if this bundle is empty */
    public boolean isEmpty();

	/** @return a RequestIterator to iterate over the Request in this bundle */
	public RequestIterator requestIterator();

	/** 
	 * Recycles this bundle so it may be returned when clients call ask the
	 * RequestFactory to create a new bundle.  Should be called once client is
	 * done using this bundle.
	 * @see javax.usb.RequestFactory
	 */
	public void recycle();
}
