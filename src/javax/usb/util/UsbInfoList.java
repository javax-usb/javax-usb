package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.UsbInfo;

/**
 * Defines a basic List interface to aggregate UsbInfo objects
 * NOTE: this list interface is based on the java.util.List interface 
 * in the Java 2 API.  However to avoid name clashes, some methods have
 * been prefixed or suffixed with "UsbInfo"
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbInfoList
{
    /**
     * Adds the UsbInfo object at the specified index
     * @param index the position to add
     * @param usbInfo the UsbInfo object to add
     */
    public void addUsbInfo( int index, UsbInfo usbInfo );

    /**
     * Add this UsbInfo object to the end of the list
     * @param usbInfo the UsbInfo object to add
     */
    public void addUsbInfo( UsbInfo usbInfo );

    /**
     * Add all this UsbInfoList's UsbInfo objects to the end of the list
     * @param usbInfoList the UsbInfoList object to add/combine
     */
    public void addUsbInfoList( UsbInfoList usbInfoList );

    /** Clears the list of all its elements */
    public void clear();

    /** @return true if the list contains this UsbInfo object */
    public boolean contains( UsbInfo usbInfo );

    /** @return true if the Object passed is the same UsbInfo object */
    public boolean equals( Object obj );

    /**
     * @return the UsbInfo object at the specified index
     * @param index the index of the UsbInfo object to get
     */
    public UsbInfo getUsbInfo( int index );

    /**
     * @return the index of the UsbInfo object passed
     * @param usbInfo the UsbInfo object
     */
    public int indexOf( UsbInfo usbInfo );

    /** @return the size of this list */
    public int size();

    /** @return true if this list is empty */
    public boolean isEmpty();

	/** @return a (shallow) copy of this UsbInfoList */
	public UsbInfoList copy();

    /** @return a UsbInfoIterator for the UsbInfo contents */
    public UsbInfoIterator usbInfoIterator();

    /** @return a UsbInfoListIterator for the UsbInfo contents */
    public UsbInfoListIterator usbInfoListIterator();

    /**
     * @return the UsbInfo object at the specified index
     * @param index the index of the UsbInfo to remove
     */
    public UsbInfo removeUsbInfo( int index );

    /**
     * @return true if the UsbInfo object specified could be removed
     * @param usbInfo the UsbInfo object to remove
     */
    public boolean removeUsbInfo( UsbInfo usbInfo );

    /**
     * Sets the UsbInfo object at the index specified
     * @param index the index where to set the specified UsbInfo object
     * @param usbInfo the UsbInfo object that should be set to the index specified
     */
    public UsbInfo setUsbInfo( int index, UsbInfo usbInfo );

    /** @return a array of UsbInfo objects */
    public UsbInfo[] toUsbInfoArray();
}
