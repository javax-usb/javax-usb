package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import java.util.EventListener;

/**
 * Super interface for all UsbServices event listeners
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbServicesListener extends EventListener
{
    /**
     * Called when a UsbDevice is attached to the host
     * @param event the UsbServicesEvent object
     */
    public void usbDeviceAttached( UsbServicesEvent event );

    /**
     * Called when a UsbDevice is detached to the host
     * @param event the UsbServicesEvent object
     */
    public void usbDeviceDetached( UsbServicesEvent event );
}
