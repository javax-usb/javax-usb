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
 * Defines an enhanced iterator for UsbInfo objects.
 * <p>
 * NOTE: this iterator interface is based on the Java 2 java.util.ListIterator.
 * <p>
 * This is an immutable iterator (unlike the Java ListIterator).  Additionally,
 * direct indexing is added via the {@link #getUsbInfo(int) getUsbInfo(int index)}
 * method.
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbInfoListIterator extends UsbInfoIterator
{
    /**
	 * If this iteration has a UsbInfo previous to the current element.
	 * <p>
	 * This returns true if and only if there is a UsbInfo previous
	 * to the current element.
	 * <p>
	 * If this iteration is empty, this will always return false.
	 * @return true if this Iterator is not at the begining.
	 */
    public boolean hasPrevious();

    /**
	 * Return the previous UsbInfo.
	 * @return the previous UsbInfo in this iteration.
	 * @throws java.util.NoSuchElementException if there is no previous UsbInfo.
	 */
    public UsbInfo previousUsbInfo();

    /**
	 * Return the index of the next UsbInfo in this iteration.
	 * <p>
	 * The iteration is 0-based (the first index is 0).
	 * <p>
	 * If the current UsbInfo is the last element, this returns
	 * the last index + 1, which is the size of the iteration.
	 * @return the next UsbInfo's index in this iteration.
	 */
    public int nextIndex();

    /**
	 * Return the index of the previous UsbInfo in this iteration.
	 * <p>
	 * The iteration is 0-based (the first index is 0).
	 * <p>
	 * If the current UsbInfo is the first element, this returns
	 * the first index - 1, which is -1.
	 * @return the previous UsbInfo's index in this iteration.
	 */
    public int previousIndex();

	/**
	 * Get the UsbInfo at the specified index.
	 * <p>
	 * If the index is valid, this returns the UsbInfo at the specified index
	 * and sets the index position to the specified index.
	 * If the index is invalid, a NoSuchElementException is thrown.
	 * @param index the index of the UsbInfo to get.
	 * @return the UsbInfo at the specified index.
	 * @throws java.util.NoSuchElementException if the specified index is invald.
	 */
	public UsbInfo getUsbInfo( int index );

    /**
	 * Get the total numer of UsbInfos contained in this iteration.
	 * @return the size of this iteration.
	 */
    public int size();
}
