package javax.usb.os;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.*;

import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;

/**
 * Defines an interface for all necessary USB OS services.
 * <p>
 * Each platform needs to provide a class implementing this interface.  That class
 * <b>must</b> define a no-argument (default) constructor which will used via reflection
 * to bootstrap the javax.usb implementation for the particular platform in question.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbServices
{
    /**
	 * Get the virtual UsbRootHub to which all Host Controller UsbHubs are attached.
	 * <p>
	 * See the javax.usb.UsbRootHub Class for more details.
     * @return the 'virtual' UsbRootHub object
     * @exception javax.usb.UsbException if something goes wrong trying to get the root hub
	 * @throws java.lang.SecurityException if current client not configured to access javax.usb
	 * @see javax.usb.UsbRootHub
     */
    public UsbRootHub getUsbRootHub() throws UsbException, SecurityException;

    /**
     * Adds a new UsbServicesListener object to receive events when the USB host
     * has changes.  For instance a new device is plugged in or unplugged.
     * @param listener the UsbServicesListener to register     
     */
    public void addUsbServicesListener( UsbServicesListener listener );

    /**
     * Adds a new UsbServicesListener object to receive events when the USB host
     * has changes.  For instance a new device is plugged in or unplugged.
     * @param listener the UsbServicesListener to register     
     */
    public void removeUsbServicesListener( UsbServicesListener listener );

	/**
	 * Get the (minimum) version number of the javax.usb API
	 * that this UsbServices implements.
	 * <p>
	 * This should correspond to the output of (some version of) the
	 * {@link javax.usb.Version#getApiVersion() javax.usb.Version}.
	 * @return the version number of the minimum API version.
	 */
	public String getApiVersion();

	/**
	 * Get the version number of the UsbServices implementation.
	 * <p>
	 * The format should be <major>.<minor>.<revision>
	 * @return the version number of the UsbServices implementation.
	 */
	public String getImpVersion();

	/**
	 * Get a description of this UsbServices implementation.
	 * <p>
	 * The format is implementation-specific, but should include at least
	 * the following:
	 * <ul>
	 * <li>The company or individual author(s).</li>
	 * <li>The license, or license header.</li>
	 * <li>Contact information.</li>
	 * <li>The minimum or expected version of Java.</li>
	 * <li>The Operating System(s) supported (usually one per implementation).</li>
	 * <li>Any other useful information.</li>
	 * </ul>
	 * @return a description of the implementation.
	 */
	public String getImpDescription();
}
