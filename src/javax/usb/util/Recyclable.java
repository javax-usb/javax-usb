package javax.usb.util;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Recyclable interface.  Defines all recyclable objects in javax.usb
 * @author Dan Streetman
 */
public interface Recyclable
{
	/**
	 * Clean this object.
	 * <p>
	 * Perform whatever actions are needed to 'clean' this object
	 * so it is ready to be reused.
	 */
	public void clean();

	/**
	 * Recycle this object.
	 * <p>
	 * This possibly returns this object to its parent RecycleFactory.
	 */
	public void recycle();
}
