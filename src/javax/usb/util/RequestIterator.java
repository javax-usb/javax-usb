package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.*;

/**
 * Defines an iterator to iterate over Request objects.  Used by the RequestBundle
 * to give clients accessed to Request objects in the bundle
 * <p>
 * NOTE that this iterator interface is based on the Java 2 collection API Iterator
 * interfaces.  The methods are prefixed with request to avoid name clashes and 
 * also to indicate that a Request object thus avoiding a cast by client.
 * </p>
 * @see javax.usb.RequestBundle
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface RequestIterator
{
	//-------------------------------------------------------------------------
	// Public methods
	// 

    /** @return true if this Iterator is not yet at the end */
    public boolean hasNext();

    /** @return the next Request in this iteration */
    public Request nextRequest();
}
