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
 * Defines a Visitor interface for all UsbInfo objects
 * <P>
 * Visitors are a pattern that in a nutshell allows the easy extension by clients
 * to a stable hierarchy.  Clients can use visitors to add methods to a hierarchy w/o
 * modifying or accessing the code to the hierarchy interfaces/classes.  
 * <P>
 * Please see GoF (Design Pattern book) page 331 for details about the visitor pattern.
 * For concrete example of usage of this visitor then see the javax.usb.util.UsbInfoToStringV
 * class and UsbHubUtility class.
 * </P>
 * </P>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 * @see javax.usb.util.UsbInfoToStringV
 * @see javax.usb.util.UsbHubUtility
 */
public interface UsbInfoVisitor
{
    //-------------------------------------------------------------------------
    // Public visitXyz visitor methods
    //

    /**
     * Visits a UsbInfo
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbInfo( UsbInfo usbInfo );

    /**
     * Visits a UsbDevice
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbDevice( UsbInfo usbInfo );

    /**
     * Visits a UsbHub
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbHub( UsbInfo usbInfo );

    /**
     * Visits a UsbRootHub
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbRootHub( UsbInfo usbInfo );

    /**
     * Visits a UsbPort
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbPort( UsbInfo usbInfo );

    /**
     * Visits a UsbConfig
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbConfig( UsbInfo usbInfo );

    /**
     * Visits a UsbInterface
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbInterface( UsbInfo usbInfo );

    /**
     * Visits a UsbEndpoint
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbEndpoint( UsbInfo usbInfo );
}
