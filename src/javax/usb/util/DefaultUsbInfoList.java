package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Default implemetation of the UsbInfoList interface
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class DefaultUsbInfoList extends AbstractUsbInfoList
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

    /** Constructor */
    public DefaultUsbInfoList() {}

    //-------------------------------------------------------------------------
    // UsbInfoList interface methods
    //

    /** @return a UsbInfoIterator for the UsbInfo contents */
    public UsbInfoIterator usbInfoIterator() { return (UsbInfoIterator)usbInfoListIterator(); }

    /** @return a UsbInfoIterator for the UsbInfo contents */
    public UsbInfoListIterator usbInfoListIterator() { return new DefaultUsbInfoList.Iterator(); }

    //-------------------------------------------------------------------------
    // Inner classes
    //

    /** Simple inner class class implementing the UsbInfoListIterator */
    protected class Iterator extends DefaultUsbInfoListIterator
    {
        Iterator() { super( DefaultUsbInfoList.this ); }
    }
}
