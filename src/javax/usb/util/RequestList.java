package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.Request;

/**
 * Defines a basic List interface to aggregate Request objects
 * <p>
 * NOTE: this list interface is based on the java.util.List interface 
 * in the Java 2 API.  However to avoid name clashes, some methods have
 * been prefixed or suffixed with "Request"
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface RequestList
{
    /**
     * Adds the Request object at the specified index
     * @param index the position to add
     * @param request the Request object to add
     */
    public void addRequest( int index, Request request );

    /**
     * Add this Request object to the end of the list
     * @param request the Request object to add
     */
    public void addRequest( Request request );

    /**
     * Add all this RequestList's Request objects to the end of the list
     * @param requestList the RequestList object to add/combine
     */
    public void addRequestList( RequestList requestList );

    /** Clears the list of all its elements */
    public void clear();

    /** @return true if the list contains this Request object */
    public boolean contains( Request request );

    /** @return true if the Object passed is the same Request object */
    public boolean equals( Object obj );

    /**
     * @return the Request object at the specified index
     * @param index the index of the Request object to get
     */
    public Request getRequest( int index );

    /**
     * @return the index of the Request object passed
     * @param request the Request object
     */
    public int indexOf( Request request );

    /** @return the size of this list */
    public int size();

    /** @return true if this list is empty */
    public boolean isEmpty();

	/** @return a (shallow) copy of this RequestList */
	public RequestList copy();

    /** @return a RequestIterator for the Request contents */
    public RequestIterator requestIterator();

    /**
     * @return the Request object at the specified index
     * @param index the index of the Request to remove
     */
    public Request removeRequest( int index );

    /**
     * @return true if the Request object specified could be removed
     * @param request the Request object to remove
     */
    public boolean removeRequest( Request request );

    /**
     * Sets the Request object at the index specified
     * @param index the index where to set the specified Request object
     * @param request the Request object that should be set to the index specified
     */
    public Request setRequest( int index, Request request );

    /** @return a array of Request objects */
    public Request[] toRequestArray();
}
