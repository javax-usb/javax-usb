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
public interface UsbIrpIterator
{
    /** @return true if this Iterator is not yet at the end */
    public boolean hasNext();

    /** @return the next UsbIrp in this iteration */
    public UsbIrp nextUsbIrp();
}
