package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.util.UsbInfoListIterator;

/**
 * Defines a USB device configuration.
 * <p>
 * This represents a configuration of a USB device.  The device may have multiple
 * configurations; only one configuration (if any) can be currently active.
 * If the device is in an unconfigured state none of its configurations will be active.
 * If this configuration is not active, its device model may be browsed, but no action
 * can be taken on any object belonging to this configuration.
 * <p>
 * See the USB 1.1 specification sec 9.6.2 for more information on USB device configurations.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbConfig extends UsbInfo
{
    /**
	 * Get this configuration's declared number.
	 * <p>
	 * Note that configuration numbers are 1-based, and not necessarily sequential.
	 * The number is actually an <i>unsigned byte</i>, but since
	 * Java does not provide unsigned numbers, an unsigned byte above
	 * 127 will appear negative.  You can use
	 * {@link javax.usb.util.UsbUtil#unsignedInt(byte) UsbUtil} to
	 * convert the number to an unsigned integer.
	 * @return this configuration's number.
	 */
    public byte getConfigNumber();

    /**
	 * Get the number of interfaces this configuration has.
	 * @return this configuration's number of interfaces.
	 */
    public byte getNumInterfaces();

    /**
	 * Get the attributes for this configuration.
	 * <p>
	 * See the USB 1.1 specification sec 9.6.2 and table 9.8 for information
	 * on configuration attributes.
	 * @return the attributes bitmap for this configuration.
	 */
    public byte getAttributes();

    /**
	 * Get the max power needed for this configuration.
	 * <p>
	 * See the USB 1.1 specification sec 9.6.2 and table 9.8 for more information
	 * on configuration max power.
	 * @return the maximum power needed for this configuration.
	 */
    public byte getMaxPower();

	/**
	 * If this UsbConfig is active.
	 * @return if this UsbConfig is active.
	 */
	public boolean isActive();

    /**
	 * Get an iteration of this UsbInterfaces belonging to this UsbConfig.
	 * <p>
	 * The returned iteration consists of interface settings dependent on
	 * whether this configuration (and by association its contained interfaces)
	 * is active or not:
	 * <ul>
	 * <li>If this configuration is active, the iteration will contain each
	 * iterface's active alternate setting UsbInterface.</li>
	 * <li>If this configuration is not active, no contained interfaces
	 * are active, so they have no active alternate settings.  The iteration
	 * will then contain an implementation-dependent alternate setting UsbInterface
	 * for each iterface.  To get a specific alternate setting, call
	 * {@link javax.usb.UsbInterface#getAlternateSetting(byte)
	 * UsbInterface.getAlternateSetting(byte number)}.</li>
	 * </ul>
	 * @return the list of USB device interfaces for this configuration.
	 */
    public UsbInfoListIterator getUsbInterfaces();

	/**
	 * Get the UsbInterface with the specified interface number.
	 * <p>
	 * The returned interface setting will be the current active
	 * alternate setting if this configuration (and thus the contained interface)
	 * is {@link #isActive() active}.  If this configuration is not active,
	 * the returned interface setting will be an implementation-dependent alternate setting.
	 * To get a specific alternate setting, call
	 * {@link javax.usb.UsbInterface#getAlternateSetting(byte)
	 * UsbInterface.getAlternateSetting(byte number)}.
	 * @param the number of the interface to get.
	 * @return a UsbInterface with the given number.
	 * @throws javax.usb.UsbRuntimeException if this does not contain a UsbInterface with the specified number.
	 */
	public UsbInterface getUsbInterface( byte number );

	/**
	 * If the specified UsbInterface is contained in this UsbConfig.
	 * @param number the number of the UsbInterface to check.
	 * @return if this config contains the specified UsbInterface.
	 */
	public boolean containsUsbInterface( byte number );

    /**
	 * Get the 'parent' UsbDevice that this UsbConfig belongs to.
	 * @return the UsbDevice that this UsbConfig belongs to.
	 */
    public UsbDevice getUsbDevice();

	/**
	 * Get the Descriptor for this UsbConfig.
	 * <p>
	 * See the USB specification sec 9.6.2 for details on configurations
	 * and their associated descriptors.  All methods in this Class that refer
	 * to configuration descriptor fields/methods will agree.
	 * For example, <code>getNumInterfaces() == getConfigDescriptor().getNumInterfaces()</code>.
	 * <p>
	 * This descriptor may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link javax.usb.StandardOperations StandardOperations}.
	 * @return the descriptor for this UsbConfig.
	 */
	public ConfigDescriptor getConfigDescriptor();

	/**
	 * Get the String for this config's description.
	 * <p>
	 * This gets the String from the device's
	 * {@link javax.usb.StringDescriptor StringDescriptor}
	 * at the index specified by the
	 * {@link javax.usb.ConfigDescriptor#getConfigIndex() ConfigDescriptor}.
	 * <p>
	 * This String may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link javax.usb.StandardOperations StandardOperations}.
	 * @return the String (or null) for this config's description.
	 */
	public String getConfigString();
}
