package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.EventListener;

/**
 * Super interface for all UsbPipeEvent listeners
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbPipeListener extends EventListener
{
    /**
     * Called when a UsbPipeErrorEvent occurred.
	 * <p>
	 * This indicates that an error has occurred during a submission.
	 * See {@link javax.usb.event.UsbPipeErrorEvent UsbPipeErrorEvent} for details.
     * @param event the UsbPipeErrorEvent object.
     */
    public void errorEventOccurred( UsbPipeErrorEvent event );

    /**
     * Called when a UsbPipeDataEvent occurred.
	 * <p>
	 * This indicates that a submission has successfully completed.
	 * See {@link javax.usb.event.UsbPipeDataEvent UsbPipeDataEvent} for details.
     * @param event the UsbPipeDataEvent object.
     */
    public void dataEventOccurred( UsbPipeDataEvent event );

}
