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
 * </p>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbServices extends UsbTopologyServices
{
    //-------------------------------------------------------------------------
    // Public registration methods
    //

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

    //-------------------------------------------------------------------------
    // Public getter methods
    //

	/**
	 * @return the RequestFactory used to create Request object for the USB operations
	 * @see javax.usb.Request
	 * @see javax.usb.StandardOperations
	 */ 
	public RequestFactory getRequestFactory();

	/**
	 * Get a new instance of a RequestFactory.
	 * <p>
	 * Since the factory returned by {@link #getRequestFactory() getRequestFactory()}
	 * is a 'shared' instance, it may be used by multiple clients
	 * in the same JVM.  If this factory implements the
	 * {@link javax.usb.Request#recycle() recycling of Requests}, it is
	 * possible for a misbehaving client to retain a reference to, and
	 * modify parts of, a Request that the misbehaving client has recycled.
	 * If this potentially corrupt Request is given out to another (behaving)
	 * client, the misbehaving client may cause the behaving client to
	 * experience errors.
	 * <p>
	 * This method returns a newly created instance of a RequestFactory,
	 * so that a client can be sure they are using a 'clean' pool of
	 * Requests.  If a client uses this they should retain the reference
	 * instead of calling this multiple times.
	 */ 
	public RequestFactory getNewRequestFactory();

	/**
	 * @return a UsbIrpFactory
	 * @see javax.usb.UsbIrp
	 */
	public UsbIrpFactory getUsbIrpFactory();

	/**
	 * Get a new instance of a UsbIrpFactory.
	 * <p>
	 * Since the factory returned by {@link #getUsbIrpFactory() getUsbIrpFactory()}
	 * is a 'shared' instance, it may be used by multiple clients
	 * in the same JVM.  If this factory implements the
	 * {@link javax.usb.UsbIrp#recycle() recycling of UsbIrps}, it is
	 * possible for a misbehaving client to retain a reference to, and
	 * modify parts of, a UsbIrp that the misbehaving client has recycled.
	 * If this potentially corrupt UsbIrp is given out to another (behaving)
	 * client, the misbehaving client may cause the behaving client to
	 * experience errors.
	 * <p>
	 * This method returns a newly created instance of a UsbIrpFactory,
	 * so that a client can be sure they are using a 'clean' pool of
	 * UsbIrps.  If a client uses this they should retain the reference
	 * instead of calling this multiple times.
	 */
	public UsbIrpFactory getNewUsbIrpFactory();

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
