package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Defines a default implemention of the UsbInfoVisitor interface
 * <p>
 * This is a utility class to facilitate the implementation of Visitors for UsbInfo
 * objects.  Clients just need to create a visitor class extending this class and 
 * only implement the visitXyz method that they are interested in. All the methods
 * are just empty implementation.
 * </p>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class DefaultUsbInfoV extends Object implements UsbInfoVisitor
{
    //-------------------------------------------------------------------------
    // Public visitXyz visitor methods
    //

    /**
     * Default method to visit a UsbInfo
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbInfo( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbDevice
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbDevice( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbHub
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbHub( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbRootHub.
	 * <p><i>
	 * NOTE: since a UsbRootHub is a UsbHub and is just a token interface the
	 * implementation calls the visitUsbHub method.
	 * </i></p>
	 * @see javax.usb.DefaultUsbInfoV#visitUsbHub
	 * @see javax.usb.UsbRootHub
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbRootHub( UsbInfo usbInfo ) { visitUsbHub( usbInfo ); }

    /**
     * Default method to visit a UsbPort
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbPort( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbConfig
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbConfig( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbInterface
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbInterface( UsbInfo usbInfo ) {}

    /**
     * Default method to visit a UsbEndpoint
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbEndpoint( UsbInfo usbInfo ) {}
}
