package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.util.UsbInfoListIterator;

/**
 * Defines a USB device interface.
 * <p>
 * This object actually represents a specific 'alternate' setting for a USB interface.
 * One and only one setting is active per interface, and all settings share the same interface
 * number.  If this interface setting is not active, it cannot be claimed or released;
 * the active interface setting should be used for claiming and releasing ownership of
 * the interface.  No action may be taken on any objects belonging to this interface
 * setting (if the setting is not active).  Any attempt to perform action on objects
 * belonging to an inactive interface setting will throw a
 * {@link javax.usb.NotActiveException NotActiveException}.
 * <p>
 * See the USB 1.1 specification sec 9.6.3 for more information on USB device interfaces.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbInterface extends UsbInfo
{
	/**
	 * Claim this interface.
	 * <p>
	 * This will only succeed if the interface is not claimed (in Java)
	 * and the native platform claim (if applicable) succeeds.
	 * This will attempt whatever claiming the native platform provides,
	 * if any.  If the native platform does not provide a interface claiming
	 * mechanism, this only claims the interface inside Java (this JVM).
	 * <p>
	 * This must be done before opening and/or using any UsbPipes.
	 * @throws javax.usb.UsbException if the interface could not be claimed.
	 * @throws javax.usb.NotActiveException if this interface setting is not
	 * {@link #isActive() active}.
	 */
	public void claim() throws UsbException;

	/**
	 * Release this interface.
	 * <p>
	 * This will only succeed if the interface is claimed (in Java).
	 * This will release the claim in Java and attempt to release whatever
	 * native claims were made (if any).
	 * <p>
	 * This should be done after the interface is no longer being used.
	 * @throws javax.usb.UsbException if the interface could not be released.
	 * @throws javax.usb.NotActiveException if this interface setting is not
	 * {@link #isActive() active}.
	 */
	public void release() throws UsbException;

	/**
	 * If this interface is claimed (in Java).
	 * <p>
	 * This returns true only if this interface is claimed (in Java).
	 * This will not return true if the interface is claimed natively,
	 * but not in Java (but the claim call will fail).
	 * @return if this interface is claimed (in Java).
	 * @throws javax.usb.NotActiveException if this interface setting is not
	 * {@link #isActive() active}.
	 */
	public boolean isClaimed();

    /**
	 * Get this UsbInterface's declared number.
	 * <p>
	 * This is the interface number as reported by the
	 * {@link javax.usb.InterfaceDescriptor#getInterfaceNumber() interface descriptor}.
	 * It is actually an <i>unsigned byte</i>, however Java does not
	 * provide unsigned numbers.  You can use
	 * {@link javax.usb.util.UsbUtil#unsignedInt(byte) UsbUtil} to convert
	 * it to an unsigned integer.
	 * @return this interface's number
	 * @see javax.usb.UsbConfig#getUsbInterfaces()
	 */
    public byte getInterfaceNumber();

    /**
	 * Get the class of this UsbInterface.
	 * <p>
	 * The class codes are defined by the USB Implementor's Forum.
	 * See the <a href="http://www.usb.org">main USB website</a>
	 * or the <a href="http://www.usb.org/developers/devclass_docs.html#approved">
	 * website for Approved Class Specification Documents</a>
	 * for more information on classes.
	 * @return this interface's class
	 */
    public byte getInterfaceClass();

    /**
	 * Get the subclass of this UsbInterface.
	 * <p>
	 * The subclass codes are defined by the specific class they belong to.
	 * See the <a href="http://www.usb.org">main USB website</a>
	 * or the <a href="http://www.usb.org/developers/devclass_docs.html#approved">
	 * website for Approved Class Specification Documents</a>
	 * for more information on subclasses.
	 * @return this interface's subclass
	 */
    public byte getInterfaceSubClass();

    /**
	 * Get the protocol of this UsbInterface.
	 * <p>
	 * The protocol codes are defined by the specific class and subclass they belong to.
	 * See the <a href="http://www.usb.org">main USB website</a>
	 * or the <a href="http://www.usb.org/developers/devclass_docs.html#approved">
	 * website for Approved Class Specification Documents</a>
	 * for more information on protocols.
	 * @return this interface's protocol
	 */
    public byte getInterfaceProtocol();

    /**
	 * Get the number of UsbEndpoints under this interface.
	 * @return this interface's number of endpoints
	 */
    public byte getNumEndpoints();

	/**
	 * If this interface alternate setting is active.
	 * <p>
	 * The interface itself is active if and only if its parent
	 * configuration is {@link javax.usb.UsbConfig#isActive() active}.
	 * If the interface itself is not active, none of its alternate settings
	 * are active.
	 * @return if this interface alternate setting is active.
	 */
	public boolean isActive();

	/**
	 * Get the number of alternate settings for this interface.
	 * @return the number of alternate settings for this interface.
	 */
	public byte getNumAlternateSettings();

    /**
	 * Get the number of this alternate setting.
	 * @return this interface's alternate setting
	 */
    public byte getAlternateSettingNumber();

	/**
	 * Get the number of the active alternate setting.
	 * <p>
	 * By default, alternate setting 0 is the active setting;
	 * see the USB 1.1 specification section 9.6.5.
	 * The 'parent' UsbConfig's getUsbInterface methods will return
	 * the active alternate setting UsbInterface object.
	 * @return the active setting for this interface
	 * @throws javax.usb.NotActiveException if the interface (and parent config) is not
	 * {@link javax.usb.UsbConfig#isActive() active}.
	 */
	public byte getActiveAlternateSettingNumber();

	/**
	 * Get the active alternate setting.
	 * <p>
	 * By default, alternate setting 0 is the active setting;
	 * see the USB 1.1 specification section 9.6.5.
	 * The 'parent' UsbConfig's getUsbInterface methods will return
	 * the active alternate setting UsbInterface object.
	 * @return the active setting UsbInterface object for this interface
	 * @throws javax.usb.NotActiveException if this interface (and parent config) is not
	 * {@link javax.usb.UsbConfig#isActive() active}.
	 */
	public UsbInterface getActiveAlternateSetting();

	/**
	 * Get the alternate setting with the specified number.
	 * @return the alternate setting with the specified number.
	 * @throws javax.usb.UsbRuntimeException if this does not contain a setting with the specified number.
	 */
	public UsbInterface getAlternateSetting( byte number );

	/**
	 * If the specified alternate setting exists.
	 * @param number the number of the alternate setting to check.
	 * @return if the alternate setting exists.
	 */
	public boolean containsAlternateSetting( byte number );

	/**
	 * Get an iterator of all alternate settings for this interface.
	 * <p>
	 * This UsbInterface setting is included in the returned iteration,
	 * as well as all other (alternate) settings.
	 * All UsbInfo objects in the returned iteration implement UsbInterface.
	 * @return an iteration of this interface's UsbInterface objects (with different 'alternate settings')
	 */
	public UsbInfoListIterator getAlternateSettings();

    /**
	 * Get an iteration of the UsbEndpoints belonging to this UsbInteface setting.
	 * <p>
	 * All UsbInfo objects in the returned iterator implement UsbEndpoint.
	 * @return an iteration of this interface's endpoints
	 */
    public UsbInfoListIterator getUsbEndpoints();

	/**
	 * Get a specific UsbEndpoint.
	 * @param index the index of the UsbEndpoint to get
	 * @return a UsbEndpoint with the specified address
	 * @throws javax.usb.UsbRuntimeException if this does not contain an endpoint with the specified address.
	 */
	public UsbEndpoint getUsbEndpoint( byte address );

	/**
	 * If the specified UsbEndpoint is contained in this UsbInterface.
	 * @param address the address of the UsbEndpoint to check.
	 * @return if this UsbInterface contains the specified UsbEndpoint.
	 */
	public boolean containsUsbEndpoint( byte address );

	/**
	 * Get the bundle of UsbPipes contained in this interface setting.
	 * @return the bundle of UsbPipes contained in this interface setting.
	 */
	public UsbPipeBundle getUsbPipes();

	/**
	 * Get the 'parent' UsbDevice that this UsbInterface belongs to.
	 * @return the UsbDevice that this interface belongs to.
	 */
	public UsbDevice getUsbDevice();

    /**
	 * Get the 'parent' UsbConfig that this UsbInterface belongs to.
	 * @return the UsbConfig that this interface belongs to.
	 */
    public UsbConfig getUsbConfig();

	/**
	 * Get the Descriptor for this UsbInterface.
	 * <p>
	 * See the USB 1.1 specification sec 9.6.3 for details on interfaces and their
	 * associated descriptors.  All methods in this Class that refer to interface
	 * descriptor fields/methods will agree.
	 * For example, <code>getInterfaceClass() == getInterfaceDescriptor().getInterfaceClass()</code>.
	 * <p>
	 * This descriptor may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link javax.usb.StandardOperations StandardOperations}.
	 * @return the descriptor for this UsbInterface.
	 */
	public InterfaceDescriptor getInterfaceDescriptor();

	/**
	 * Get the String for this interface's description.
	 * <p>
	 * This gets the String from the device's
	 * {@link javax.usb.StringDescriptor StringDescriptor} at the index specified by the 
	 * {@link javax.usb.InterfaceDescriptor#getInterfaceIndex() InterfaceDescriptor}.
	 * <p>
	 * This String may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link javax.usb.StandardOperations StandardOperations}.
	 * @return the String (or null) for this interface's description.
	 */
	public String getInterfaceString();
}
