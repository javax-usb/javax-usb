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
 * Super interface for all USB device event listeners
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbDeviceListener extends EventListener
{
    /**
     * Called when UsbDevice is detached.
     * @param event the UsbDeviceEvent object
     */
    public void usbDeviceDetached( UsbDeviceEvent event );

    /**
     * Called when a UsbDeviceErrorEvent occurred.
	 * <p>
	 * This indicates that an error has occurred during a submission of a Request.
	 * See {@link javax.usb.event.UsbDeviceErrorEvent UsbDeviceErrorEvent} for details.
     * @param event the UsbDeviceErrorEvent object.
     */
    public void errorEventOccurred( UsbDeviceErrorEvent event );

    /**
     * Called when a UsbDeviceDataEvent occurred.
	 * <p>
	 * This indicates that a submission has successfully completed.
	 * See {@link javax.usb.event.UsbDeviceDataEvent UsbDeviceDataEvent} for details.
     * @param event the UsbDeviceDataEvent object.
     */
    public void dataEventOccurred( UsbDeviceDataEvent event );
}
