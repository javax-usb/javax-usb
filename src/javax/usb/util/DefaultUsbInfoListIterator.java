package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

import javax.usb.UsbInfo;

/**
 * Defines the default UsbInfoListIterator class.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class DefaultUsbInfoListIterator extends Object implements UsbInfoListIterator
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

    /** 
     * Default 1-arg ctor
     * @param usbInfoList the list to iterate over
     */
    public DefaultUsbInfoListIterator( UsbInfoList usbInfoList )
    { usbInfoArray = usbInfoList.toUsbInfoArray(); }

    //-------------------------------------------------------------------------
    // UsbInfoListIterator interface methods
    //

    /** @return true if this Iterator is not yet at the end */
    public boolean hasNext() { return !(usbInfoArray.length == next); }

    /** @return the next UsbInfo in this iteration */
    public synchronized UsbInfo nextUsbInfo() 
    {
        if (!hasNext())
			throw new NoSuchElementException( "No next UsbInfo" );

		previous++;
        return usbInfoArray[next++];
    }

    /** @return true if this Iterator is not at the begining */
    public boolean hasPrevious() { return !(-1 == previous); }

    /** @return the previous UsbInfo in this iteration */
    public synchronized UsbInfo previousUsbInfo()
    {
        if(!hasPrevious())
			throw new NoSuchElementException( "No Previous UsbInfo" );

		next--;
        return usbInfoArray[previous--];
    }

    /** @return the next UsbInfo's index in this iteration */
    public int nextIndex() { return next; }

    /** @return the previous UsbInfo's index in this iteration */
    public int previousIndex() { return previous; }

	/** @return the UsbInfo at the specified index */
	public synchronized UsbInfo getUsbInfo( int index )
	{
		if (0 > index || index >= usbInfoArray.length)
			throw new NoSuchElementException( "No UsbInfo at index " + index );

		previous = index;
		next = index+1;
		return usbInfoArray[index];
	}

    /** @return the size of this iteration */
    public int size() { return usbInfoArray.length; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

	private int previous = -1;
	private int next = 0;

    private UsbInfo[] usbInfoArray = null;
}
