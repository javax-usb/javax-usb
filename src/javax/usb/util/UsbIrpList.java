package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.UsbIrp;

/**
 * Defines a basic List interface to aggregate UsbIrp objects.
 * <p>
 * NOTE: this list interface is based on the java.util.List interface 
 * in the Java 2 API.  However to avoid name clashes, some methods have
 * been prefixed or suffixed with "UsbIrp".
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbIrpList
{
    /**
     * Adds the UsbInfo object at the specified index.
	 * <p>
	 * This puts the specified UsbIrp into this UsbIrpList at the specified index.
	 * All UsbIrps after the specified index are shifted up by one.  If the
	 * specified index is invalid (less than 0 or greater than this UsbIrpList's
	 * {@link #size() size}), java.util.ArrayIndexOutOfBoundsException is thrown.
     * @param index the position to add.
     * @param usbIrp the UsbIrp object to add.
	 * @throws java.util.ArrayIndexOutOfBoundsException if the specified index is invalid.
     */
    public void addUsbIrp( int index, UsbIrp usbIrp );

    /**
     * Add this UsbIrp object to the end of the list.
     * @param usbIrp the UsbIrp object to add.
     */
    public void addUsbIrp( UsbIrp usbIrp );

    /**
     * Add all this UsbIrpList's UsbIrp objects to the end of the list.
     * @param usbIrpList the UsbIrpList object to add/combine.
     */
    public void addUsbIrpList( UsbIrpList usbIrpList );

    /**
	 * Clears the list of all its elements.
	 * <p>
	 * The {@link #size() size} of this UsbIrpList will be
	 * zero after this method returns.
	 */
    public void clear();

    /**
	 * If this list contains the specified UsbIrp.
	 * @return true if the list contains this UsbIrp object.
	 */
    public boolean containsUsbIrp( UsbIrp usbIrp );

    /**
	 * If the specified Object is a UsbIrpList with identical contents.
	 * <p>
	 * This will perform a shallow equals() comparison of each list's
	 * contents in order.  It will return true only if all the contents
	 * are equal() at the same index in both lists.
	 * @return true if the Object passed is an indentical UsbIrpList.
	 */
    public boolean equals( Object obj );

    /**
	 * Get the UsbIrp at the specified index.
     * @return the UsbIrp object at the specified index.
     * @param index the index of the UsbIrp object to get.
     */
    public UsbIrp getUsbIrp( int index );

    /**
	 * Get the index of the specified UsbIrp.
     * @return the index of the UsbIrp object passed.
     * @param usbIrp the UsbIrp object.
     */
    public int indexOfUsbIrp( UsbIrp usbIrp );

    /**
	 * Get the number of UsbIrps in this list.
	 * @return the size of this list.
	 */
    public int size();

    /**
	 * If this list is empty.
	 * <p>
	 * This is true only if the {@link #size() size}
	 * of this UsbIrpList is zero.
	 * @return true if this list is empty
	 */
    public boolean isEmpty();

	/**
	 * Get a (shallow) copy of this UsbIrpList.
	 * <p>
	 * The returned list will be {@link #equals( Object obj ) equal}
	 * to this UsbIrpList (until either list is modified).
	 * @return a (shallow) copy of this UsbIrpList.
	 */
	public UsbIrpList copy();

    /**
	 * Get a UsbIrpIterator representing this UsbInfoList.
	 * <p>
	 * The resulting UsbIrpIterator is a shallow representation
	 * of this UsbIrpList, i.e. the UsbIrps contained are the
	 * same Objects.  However, the resulting UsbIrpIterator is
	 * not 'backed' by this UsbIrpList, i.e. changes to this
	 * UsbIrpList will not affect the UsbIrpIterator and changes
	 * to the UsbIrpIterator will not affect this UsbIrpList.
	 * @return a UsbIrpIterator for the UsbIrp contents.
	 */
    public UsbIrpIterator usbIrpIterator();

    /**
	 * Get a UsbIrpListIterator representing this UsbInfoList.
	 * <p>
	 * The resulting UsbIrpListIterator is a shallow representation
	 * of this UsbIrpList, i.e. the UsbIrps contained are the
	 * same Objects.  However, the resulting UsbIrpListIterator is
	 * not 'backed' by this UsbIrpList, i.e. changes to this
	 * UsbIrpList will not affect the UsbIrpListIterator and changes
	 * to the UsbIrpListIterator will not affect this UsbIrpList.
	 * @return a UsbIrpListIterator for the UsbIrp contents.
	 */
    public UsbIrpListIterator usbIrpListIterator();

    /**
	 * Remove the UsbIrp at the specified index.
	 * <p>
	 * This removes the UsbIrp at the specified index if it
	 * exists, and returns that UsbIrp.  All UsbIrps after the
	 * specified index are shifted down one index.  If the specified
	 * index is invalid a java.lang.ArrayIndexOutOfBoundsException is thrown.
     * @return the UsbIrp object at the specified index.
     * @param index the index of the UsbIrp to remove.
	 * @throws java.lang.ArrayIndexOutOfBoundsException if the specified index is invalid.
     */
    public UsbIrp removeUsbIrp( int index );

    /**
	 * Remove the specified UsbIrp from this UsbIrpList.
	 * <p>
	 * This removed the specified UsbIrp and returns true if the
	 * removal was successful.  If the specified UsbIrp is contained
	 * multiple times in this UsbIrpList only the first reference is removed.
     * @return true if the UsbIrp object specified could be removed.
     * @param usbIrp the UsbIrp object to remove.
     */
    public boolean removeUsbIrp( UsbIrp usbIrp );

    /**
     * Sets the UsbIrp object at the index specified.
	 * <p>
	 * This puts the specified UsbIrp into this UsbIrpList at the specified index.
	 * No other UsbIrps are affected by this, and the UsbIrp at the specified index
	 * is replaced.  If the specified index is invalid (less than 0 or greater than
	 * this UsbIrpList's {@link #size() size}), java.util.ArrayIndexOutOfBoundsException is thrown.
     * @param index the index where to set the specified UsbIrp object.
     * @param usbIrp the UsbIrp object that should be set in the index specified.
	 * @throws java.util.ArrayIndexOutOfBoundsException if the specified index is invalid.
     */
    public UsbIrp setUsbIrp( int index, UsbIrp usbIrp );

    /**
	 * Get an array of this UsbIrpList's contents.
	 * <p>
	 * The resulting array is a shallow representation
	 * of this UsbIrpList, i.e. the UsbIrps contained are
	 * the same Objects.  However, the resulting array is not
	 * 'backed' by this UsbIrpList, i.e. changes to this UsbIrpList
	 * will not affect the array and changes to the array will not
	 * affect this UsbIrpList.
	 * @return a array of UsbIrp objects.
	 */
    public UsbIrp[] toUsbIrpArray();
}
