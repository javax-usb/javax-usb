package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.io.*;
import java.util.*;

import javax.usb.*;
import javax.usb.event.*;

/**
 * General utility methods.
 * @author Dan Streetman
 */
public class UsbUtil
{
	/**
	 * Get the specified byte's value as an unsigned short.
	 * <p>
	 * This converts the specified byte into a short.
	 * The least significant byte (8 bits) of the short
	 * will be identical to the byte (8 bits) provided,
	 * and the most significant byte (8 bits) of the
	 * short will be zero.
	 * <p>
	 * For many of the values in this USB API, unsigned bytes are used.
	 * However, since Java does not include unsigned bytes in the language,
	 * those unsigned bytes must be converted to a larger storage type
	 * before being used in unsigned calculations.
	 * @param b the byte to convert.
	 * @return An unsigned short representing the specified byte.
	 */
	public static short unsignedShort( byte b ) { return (short)(0x00ff & b); }

	/**
	 * Get the specified byte's value as an unsigned integer.
	 * <p>
	 * This converts the specified byte into an integer.
	 * The least significant byte (8 bits) of the integer
	 * will be identical to the byte (8 bits) provided,
	 * and the most significant 3 bytes (24 bits) of the
	 * integer will be zero.
	 * <p>
	 * For many of the values in this USB API, unsigned bytes are used.
	 * However, since Java does not include unsigned bytes in the language,
	 * those unsigned bytes must be converted to a larger storage type
	 * before being used in unsigned calculations.
	 * @param b the byte to convert.
	 * @return An unsigned int representing the specified byte.
	 */
	public static int unsignedInt( byte b ) { return 0x000000ff & b; }

	/**
	 * Get the specified short's value as an unsigned integer.
	 * <p>
	 * This converts the specified byte into an integer.
	 * The least significant short (16 bits) of the integer
	 * will be identical to the short (16 bits) provided,
	 * and the most significant 2 bytes (16 bits) of the
	 * integer will be zero.
	 * <p>
	 * For many of the values in this USB API, unsigned shorts are used.
	 * However, since Java does not include unsigned short in the language,
	 * those unsigned shorts must be converted to a larger storage type
	 * before being used in unsigned calculations.
	 * @param s the short to convert.
	 * @return An unsigned int representing the specified short.
	 */
	public static int unsignedInt( short s ) { return 0x0000ffff & s; }

	/**
	 * Get the specified byte's value as an unsigned long.
	 * <p>
	 * This converts the specified byte into a long.
	 * The least significant byte (8 bits) of the long
	 * will be identical to the byte (8 bits) provided,
	 * and the most significant 7 bytes (56 bits) of the
	 * long will be zero.
	 * <p>
	 * For many of the values in this USB API, unsigned bytes are used.
	 * However, since Java does not include unsigned bytes in the language,
	 * those unsigned bytes must be converted to a larger storage type
	 * before being used in unsigned calculations.
	 * @param b the byte to convert.
	 * @return An unsigned long representing the specified byte.
	 */
	public static long unsignedLong( byte b ) { return 0x00000000000000ff & b; }

	/**
	 * Get the specified short's value as an unsigned long.
	 * <p>
	 * This converts the specified byte into a long.
	 * The least significant short (16 bits) of the long
	 * will be identical to the short (16 bits) provided,
	 * and the most significant 6 bytes (48 bits) of the
	 * long will be zero.
	 * <p>
	 * For many of the values in this USB API, unsigned shorts are used.
	 * However, since Java does not include unsigned short in the language,
	 * those unsigned shorts must be converted to a larger storage type
	 * before being used in unsigned calculations.
	 * @param s the short to convert.
	 * @return An unsigned long representing the specified short.
	 */
	public static long unsignedLong( short s ) { return 0x000000000000ffff & s; }

	/**
	 * Get the specified int's value as an unsigned long.
	 * <p>
	 * This converts the specified int into a long.
	 * The least significant int (32 bits) of the long
	 * will be identical to the int (32 bits) provided,
	 * and the most significant int (32 bits) of the
	 * long will be zero.
	 * @param i the int to convert.
	 * @return An unsigned long representing the specified int.
	 */
	public static long unsignedLong( int i ) { return 0x00000000ffffffff & i; }

	/**
	 * Format a byte into a proper length hex String.
	 * <p>
	 * This is identical to Long.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param b the byte to convert
	 */
	public static String toHexString( byte b )
	{
		return toHexString( unsignedLong( b ), '0', 2, 2 );
	}

	/**
	 * Format a short into a proper length hex String.
	 * <p>
	 * This is identical to Long.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param s the short to convert
	 */
	public static String toHexString( short s )
	{
		return toHexString( unsignedLong( s ), '0', 4, 4 );
	}

	/**
	 * Format a int into a proper length hex String.
	 * <p>
	 * This is identical to Long.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param i the integer to convert
	 */
	public static String toHexString( int i )
	{
			return toHexString( unsignedLong( i ), '0', 8, 8 );
	}

	/**
	 * Format a long into the specified length hex String.
	 * <p>
	 * This is identical to Long.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param l the long to convert
	 */
	public static String toHexString( long l )
	{
			return toHexString( l, '0', 16, 16 );
	}

	/**
	 * Format a long into the specified length hex String.
	 * <p>
	 * This is identical to Long.toHexString()
	 * except this pads (with 0's), or truncates, to the specified size.
	 * If max < min the functionaliy is exactly as Long.toHexString().
	 * @param l the long to convert
	 * @param c the character to use for padding
	 * @param min the min length of the resulting String
	 * @param max the max length of the resulting String
	 */
	public static String toHexString( long l, char c, int min, int max )
	{
		StringBuffer sb = new StringBuffer( Long.toHexString( l ) );

		if ( max < min )
			return sb.toString();

		while (sb.length() < max)
			sb.insert(0, c);

		return sb.substring(0, min);
	}

	/**
	 * Get a String description of the specified device-speed Object.
	 * <p>
	 * The String will be one of:
	 * <ul>
	 * <li>Low</li>
	 * <li>Full</li>
	 * <li>Unknown</li>
	 * <li>null</li>
	 * <li>Invalid</li>
	 * </ul>
	 * The string "null" is used for a null Object.
	 * The string "Invalid" is used for an Object that
	 * does not correspond to any of those defined in
	 * {@link javax.usb.UsbConst UsbConst}.
	 * @param object The device-speed Object.
	 * @return A String representing the speed Object.
	 * @see UsbConst#DEVICE_SPEED_LOW Low Speed.
	 * @see UsbConst#DEVICE_SPEED_FULL Full Speed.
	 * @see UsbConst#DEVICE_SPEED_UNKNOWN Unknown Speed.
	 */
	public static String getSpeedString(Object object)
	{
		if (UsbConst.DEVICE_SPEED_LOW == object)
			return "Low";
		if (UsbConst.DEVICE_SPEED_FULL == object)
			return "Full";
		if (UsbConst.DEVICE_SPEED_UNKNOWN == object)
			return "Unknown";
		if (null == object)
			return "null";

		return "Invalid";
	}

	/**
	 * Create a synchronized UsbDevice.
	 * @param usbDevice The unsynchronized UsbDevice.
	 * @return A synchronized UsbDevice.
	 */
	public static UsbDevice synchronizedUsbDevice(UsbDevice usbDevice) { return new UsbUtil.SynchronizedUsbDevice(usbDevice); }

	/**
	 * Create a synchronized UsbPipe.
	 * @param usbPipe The unsynchronized UsbPipe.
	 * @return A synchronized UsbPipe.
	 */
	public static UsbPipe synchronizedUsbPipe(UsbPipe usbPipe) { return new UsbUtil.SynchronizedUsbPipe(usbPipe); }

	/**
	 * A synchronized UsbDevice wrapper implementation.
	 */
	public static class SynchronizedUsbDevice implements UsbDevice
	{
		public SynchronizedUsbDevice(UsbDevice usbDevice) { this.usbDevice = usbDevice; }

		public UsbPort getParentUsbPort() { return usbDevice.getParentUsbPort(); }
		public boolean isUsbHub() { return usbDevice.isUsbHub(); }
		public String getManufacturerString() throws UsbException,UnsupportedEncodingException
		{ synchronized (submitLock) { return usbDevice.getManufacturerString(); } }
		public String getSerialNumberString() throws UsbException,UnsupportedEncodingException
		{ synchronized (submitLock) { return usbDevice.getSerialNumberString(); } }
		public String getProductString() throws UsbException,UnsupportedEncodingException
		{ synchronized (submitLock) { return usbDevice.getProductString(); } }
		public Object getSpeed() { return usbDevice.getSpeed(); }
		public List getUsbConfigs() { return usbDevice.getUsbConfigs(); }
		public UsbConfig getUsbConfig( byte number ) { return usbDevice.getUsbConfig(number); }
		public boolean containsUsbConfig( byte number ) { return usbDevice.containsUsbConfig(number); }
		public byte getActiveUsbConfigNumber() { return usbDevice.getActiveUsbConfigNumber(); }
		public UsbConfig getActiveUsbConfig() { return usbDevice.getActiveUsbConfig(); }
		public boolean isConfigured() { return usbDevice.isConfigured(); }
		public DeviceDescriptor getDeviceDescriptor() { return usbDevice.getDeviceDescriptor(); }
		public StringDescriptor getStringDescriptor( byte index ) throws UsbException
		{ synchronized (submitLock) { return usbDevice.getStringDescriptor(index); } }
		public String getString( byte index ) throws UsbException,UnsupportedEncodingException
		{ synchronized (submitLock) { return usbDevice.getString(index); } }
		public void syncSubmit( ControlUsbIrp irp ) throws UsbException
		{ synchronized (submitLock) { usbDevice.syncSubmit(irp); } }
		public void asyncSubmit( ControlUsbIrp irp ) throws UsbException
		{ synchronized (submitLock) { usbDevice.asyncSubmit(irp); } }
		public void syncSubmit( List list ) throws UsbException
		{ synchronized (submitLock) { usbDevice.syncSubmit(list); } }
		public void asyncSubmit( List list ) throws UsbException
		{ synchronized (submitLock) { usbDevice.asyncSubmit(list); } }
		public ControlUsbIrp createControlUsbIrp(byte bmRequestType, byte bRequest, short wValue, short wIndex)
		{ return usbDevice.createControlUsbIrp(bmRequestType, bRequest, wValue, wIndex); }
		public void addUsbDeviceListener( UsbDeviceListener listener )
		{ synchronized (listenerLock) { usbDevice.addUsbDeviceListener(listener); } }
		public void removeUsbDeviceListener( UsbDeviceListener listener )
		{ synchronized (listenerLock) { usbDevice.removeUsbDeviceListener(listener); } }

		public UsbDevice usbDevice = null;
		protected Object submitLock = new Object();
		protected Object listenerLock = new Object();
	}

	/**
	 * A synchronized UsbPipe wrapper implementation.
	 * <p>
	 * Not all methods are synchronized; the open/close methods are
	 * synchronized to each other, and the submission and abort methods
	 * are synchronized to each other.
	 */
	public static class SynchronizedUsbPipe implements UsbPipe
	{
		public SynchronizedUsbPipe(UsbPipe usbPipe) { this.usbPipe = usbPipe; }

		public void open() throws UsbException,NotActiveException
		{ synchronized (openLock) { usbPipe.open(); } }
		public void close() throws UsbException,NotActiveException
		{ synchronized (openLock) { usbPipe.close(); } }
		public boolean isActive() { return usbPipe.isActive(); }
		public boolean isOpen() { return usbPipe.isOpen(); }
		public UsbEndpoint getUsbEndpoint() { return usbPipe.getUsbEndpoint(); }
		public int syncSubmit( byte[] data ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { return usbPipe.syncSubmit(data); } }
		public UsbIrp asyncSubmit( byte[] data ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { return usbPipe.asyncSubmit(data); } }
		public void syncSubmit( UsbIrp irp ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { usbPipe.syncSubmit(irp); } }
		public void asyncSubmit( UsbIrp irp ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { usbPipe.asyncSubmit(irp); } }
		public void syncSubmit( List list ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { usbPipe.syncSubmit(list); } }
		public void asyncSubmit( List list ) throws UsbException,NotOpenException
		{ synchronized (submitLock) { usbPipe.asyncSubmit(list); } }
		public void abortAllSubmissions() throws NotOpenException
		{ synchronized (submitLock) { usbPipe.abortAllSubmissions(); } }
		public UsbIrp createUsbIrp()
		{ return usbPipe.createUsbIrp(); }
		public ControlUsbIrp createControlUsbIrp(byte bmRequestType, byte bRequest, short wValue, short wIndex)
		{ return usbPipe.createControlUsbIrp(bmRequestType, bRequest, wValue, wIndex); }
		public void addUsbPipeListener( UsbPipeListener listener )
		{ usbPipe.addUsbPipeListener(listener); }
		public void removeUsbPipeListener( UsbPipeListener listener )
		{ usbPipe.removeUsbPipeListener(listener); }

		public UsbPipe usbPipe = null;
		protected Object openLock = new Object();
		protected Object submitLock = new Object();
	}

}
