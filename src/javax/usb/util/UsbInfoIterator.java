package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.UsbInfo;

/**
 * Defines a basic iterator for UsbInfo objects.
 * <p>
 * NOTE: this iterator interface is based on the Java 2 collection API java.util.Iterator
 * it avoids client to have to do an extra cast and some methods suffixed with UsbInfo
 * to indicate that a UsbInfo is returned.
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbInfoIterator
{
    /**
	 * If this iteration has a UsbInfo after the current element.
	 * <p>
	 * This returns true if and only if there is a UsbInfo
	 * after the current element.
	 * <p>
	 * If this iteration is empty, this will always return false.
	 * @return true if this Iterator is not yet at the end.
	 */
    public boolean hasNext();

    /**
	 * Return the next UsbInfo.
	 * <p>
	 * The first call to this will return the first element in the iteration.
	 * @return the next UsbInfo in this iteration.
	 * @throws java.util.NoSuchElementException if there is no next UsbInfo.
	 */
    public UsbInfo nextUsbInfo();
}
