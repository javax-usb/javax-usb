package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import java.util.Vector;

import javax.usb.UsbInfo;

/**
 * Abstract implementation of the UsbInfoList interface using a Vector class
 * Subclasses should override this if they need to change the implementation
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public abstract class AbstractUsbInfoList extends Object implements UsbInfoList
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

    //-------------------------------------------------------------------------
    // UsbInfoList interface methods
    //

    /**
     * Adds the UsbInfo object at the specified index
     * @param index the position to add
     * @param usbInfo the UsbInfo object to add
     */
    public void addUsbInfo( int index, UsbInfo usbInfo ) { vector.insertElementAt( usbInfo, index ); }

    /**
     * Add this UsbInfo object to the end of the list
     * @param usbInfo the UsbInfo object to add
     */
    public void addUsbInfo( UsbInfo usbInfo ) { vector.addElement( usbInfo ); }

    /**
     * Add all this UsbInfoList's UsbInfo objects to the end of the list
     * @param usbInfoList the UsbInfoList object to add/combine
     */
    public void addUsbInfoList( UsbInfoList usbInfoList )
    {
        UsbInfo[] usbInfoArray = usbInfoList.toUsbInfoArray();

        for ( int i=0; i<usbInfoArray.length; i++ )
            addUsbInfo( usbInfoArray[i] );
    }

    /** Clears the list of all its elements */
    public void clear() { vector.clear(); }

    /** @return true if the list contains this UsbInfo object */
    public boolean contains( UsbInfo usbInfo ) { return vector.contains( usbInfo ); }

    /**
     * @return the UsbInfo object at the specified index
     * @param index the index of the UsbInfo object to get
     */
    public UsbInfo getUsbInfo( int index ) { return (UsbInfo)vector.elementAt( index ); }

	/** @return a (shallow) copy of this UsbInfoList */
	public UsbInfoList copy()
	{
		UsbInfoList newList = new DefaultUsbInfoList();

		for (int i=0; i<size(); i++)
			newList.addUsbInfo(getUsbInfo(i));

		return newList;
	}

    /**
     * @return the index of the UsbInfo object passed
     * @param usbInfo the UsbInfo object
     */
    public int indexOf( UsbInfo usbInfo ) { return vector.indexOf( usbInfo ); }

    /** @return true if this list is empty */
    public boolean isEmpty() { return vector.isEmpty(); }

    /** @return the size of this list */
    public int size() { return vector.size(); }

    /**
     * @return the UsbInfo object at the specified index
     * @param index the index of the UsbInfo to remove
     */
    public UsbInfo removeUsbInfo( int index ) 
    { 
        UsbInfo usbInfo = (UsbInfo)vector.elementAt( index );

        vector.removeElement( usbInfo );

        return usbInfo;
    }

    /**
     * @return true if the UsbInfo object specified could be removed
     * @param usbInfo the UsbInfo object to remove
     */
    public boolean removeUsbInfo( UsbInfo usbInfo ) { return vector.removeElement( usbInfo ); }

    /**
     * Sets the UsbInfo object at the index specified
     * @param index the index where to set the specified UsbInfo object
     * @param usbInfo the UsbInfo object that should be set to the index specified
     * @return the previous element
     */
    public UsbInfo setUsbInfo( int index, UsbInfo usbInfo )
    {
        UsbInfo previousUsbInfo = (UsbInfo)vector.elementAt( index );

        vector.setElementAt( usbInfo, index );

        return previousUsbInfo;
    }

    /** @return a array of UsbInfo objects */
    public UsbInfo[] toUsbInfoArray()
    {
        UsbInfo[] usbInfoArray = new UsbInfo[ vector.size() ];

        for( int i = 0; i < vector.size(); ++i )
            usbInfoArray[ i ] = (UsbInfo)vector.elementAt( i );

        return usbInfoArray;
    }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    protected Vector vector = new Vector();
}
