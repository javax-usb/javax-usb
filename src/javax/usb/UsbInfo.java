package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

/**
 * Defines a superinterface for all USB model objects: UsbDevice, UsbInterface, UsbEndpoint, ...
 * <p>
 * This interface also sets up the Visitor pattern for the model objects and have other 
 * generic methods.
 * </p>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbInfo
{
    //-------------------------------------------------------------------------
    // Public methods
    //

    /** 
	 * Get the name of this USB device model object.
	 * <p>
	 * The name is implementation-specific, but the suggested naming convention is
	 * to include the following information in the name, in a way as concise as possible:
	 * <ul>
	 * <li>Type of device model object (device, hub, interface, endpoint, etc)</li>
	 * <li>Topology position of device/hub, or number/index of config/interface/endpoint/etc</li>
	 * </ul>
	 * @return the name of this USB device model object.
	 */
    public String getName();

	/** @return the descriptor for this USB device model object */
	public Descriptor getDescriptor();

    /**
     * Visitor.accept method.  Each class extending this interface (UsbXyz) implements
	 * this method by calling the correct visitXyz method.  For an example, please see
	 * the javax.usb.util.UsbInfoToStringV class
	 * @see javax.usb.DefaultUsbInfoV
	 * @see javax.usb.util.UsbInfoToStringV
     * @param visitor the UsbInfoVisitor visiting this UsbInfo
     */
    public void accept( UsbInfoVisitor visitor );
}
