package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.UsbIrp;

/**
 * Defines a basic Iterator object for all UsbIrp objects.
 * <p>
 * NOTE: this iterator interface is based on the
 * java.util.Enumeration interface in the Java 1 API.  However
 * to avoid name clashes, some names have been prefixed
 * or suffixed with "UsbIrp".
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbIrpListIterator extends UsbIrpIterator
{
    /**
	 * If this iteration has a UsbIrp previous to the current element.
	 * <p>
	 * This returns true if and only if there is a UsbIrp previous
	 * to the current element.
	 * <p>
	 * If this iteration is empty, this will always return false.
	 * @return true if this Iterator is not at the begining.
	 */
    public boolean hasPrevious();

    /**
	 * Return the previous UsbIrp.
	 * @return the previous UsbIrp in this iteration.
	 * @throws java.util.NoSuchElementException if there is no previous UsbIrp.
	 */
    public UsbIrp previousUsbIrp();

    /**
	 * Return the index of the next UsbIrp in this iteration.
	 * <p>
	 * The iteration is 0-based (the first index is 0).
	 * <p>
	 * If the current UsbIrp is the last element, this returns
	 * the last index + 1, which is the size of the iteration.
	 * @return the next UsbIrp's index in this iteration.
	 */
    public int nextIndex();

    /**
	 * Return the index of the previous UsbIrp in this iteration.
	 * <p>
	 * The iteration is 0-based (the first index is 0).
	 * <p>
	 * If the current UsbIrp is the first element, this returns
	 * the first index - 1, which is -1.
	 * @return the previous UsbIrp's index in this iteration.
	 */
    public int previousIndex();

	/**
	 * Get the UsbIrp at the specified index.
	 * <p>
	 * If the index is valid, this returns the UsbIrp at the specified index
	 * and increments the current index past it (i.e., this is true:
	 * <code>{@link #getUsbIrp(int) getUsbIrp()} == {@link #previousUsbIrp() previousUsbIrp()}</code>).
	 * If the index is invalid, a NoSuchElementException is thrown.
	 * @param index the index of the UsbIrp to get.
	 * @return the UsbIrp at the specified index.
	 * @throws java.util.NoSuchElementException if the specified index is invald.
	 */
	public UsbIrp getUsbIrp( int index );

    /**
	 * Get the total numer of UsbIrps contained in this iteration.
	 * @return the size of this iteration.
	 */
    public int size();
}
