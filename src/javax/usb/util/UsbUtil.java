package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.*;

/**
 * General utility methods
 * @author Dan Streetman
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbUtil {

	/** Private constructor */
	private UsbUtil() { }

	/**
	 * Get the specified byte's value as an unsigned integer.
	 * <p>
	 * This converts the specified byte into an integer.
	 * The least significant byte (8 bits) of the integer
	 * will be identical to the byte (8 bits) provided,
	 * and the most significant 3 bytes (24 bits) of the
	 * integer will be zero.
	 * @param b the byte to convert.
	 */
	public static int unsignedInt( byte b ) { return 0x000000ff & ((int)b); }

	/**
	 * Get the specified short's value as an unsigned integer.
	 * <p>
	 * This converts the specified byte into an integer.
	 * The least significant short (16 bits) of the integer
	 * will be identical to the short (16 bits) provided,
	 * and the most significant 2 bytes (16 bits) of the
	 * integer will be zero.
	 * @param s the short to convert.
	 */
	public static int unsignedInt( short s ) { return 0x0000ffff & ((int)s); }

	/**
	 * Get the endpoint type String for the specified endpoint type
	 * @param type the endpoint type
	 * @return the type of endpoint
	 */
	public static String getEndpointTypeString( byte type )
	{
		switch ( type & UsbInfoConst.ENDPOINT_TYPE_MASK ) {
			case UsbInfoConst.ENDPOINT_TYPE_CONTROL:
				return "Control";
			case UsbInfoConst.ENDPOINT_TYPE_BULK:
				return "Bulk";
			case UsbInfoConst.ENDPOINT_TYPE_INT:
				return "Interrupt";
			case UsbInfoConst.ENDPOINT_TYPE_ISOC:
				return "Isochronous";
			default:
				return "?";
		}
	}

	/**
	 * Get the endpoint direction String
	 * @param address the endpoint's address (MSb is direction indicator)
	 * @param type the endpoint's type (Control endpoints are bidirectional)
	 * @return the endpoint's direction String
	 */
	public static String getEndpointDirectionString( byte address, byte type )
	{
		byte epType = (byte)(type & UsbInfoConst.ENDPOINT_TYPE_MASK);

		if ( UsbInfoConst.ENDPOINT_TYPE_CONTROL == epType )
			return "Bidirectional";

		byte epDirection = (byte)(address & UsbInfoConst.ENDPOINT_DIRECTION_MASK);

		if ( UsbInfoConst.ENDPOINT_DIRECTION_IN == epDirection )
			return "In";
		else
			return "Out";
	}

	/**
	 * Format a byte into a proper length hex String.
	 * <p>
	 * This is identical to Integer.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param b the byte to convert
	 */
	public static String toHexString( byte b )
	{
		return toHexString( unsignedInt( b ), '0', 2, 2 );
	}

	/**
	 * Format a short into a proper length hex String.
	 * <p>
	 * This is identical to Integer.toHexString()
	 * except this pads (with 0's) to the proper size.
	 * @param s the short to convert
	 */
	public static String toHexString( short s )
	{
		return toHexString( unsignedInt( s ), '0', 4, 4 );
	}

	/**
	 * Format a int into the specified length hex String.
	 * <p>
	 * This is identical to Integer.toHexString()
	 * except this pads (with 0's), or truncates, to the specified size.
	 * If max < min the functionaliy is exactly as Integer.toHexString().
	 * @param i the integer to convert
	 * @param c the character to use for padding
	 * @param min the min length of the resulting String
	 * @param max the max length of the resulting String
	 */
	public static String toHexString( int i, char c, int min, int max )
	{
		StringBuffer sb = new StringBuffer( Integer.toHexString( i ) );

		if ( max < min )
			return sb.toString();

		while (sb.length() < max)
			sb.insert(0, c);

		return sb.substring(0, min);
	}

	/**
	 * Format parameters into a Control submission Setup byte[]
	 * @param bmRequestType see USB spec sec 9.3
	 * @param bRequest see USB spec sec 9.3
	 * @param wValue see USB spec sec 9.3
	 * @param wIndex see USB spec sec 9.3
	 * @param wLength see USB spec sec 9.3
	 * @return a byte[] representing the Setup packet
	 */
	public static byte[] createControlSetupPacket(
		byte bmRequestType,
		byte bRequest,
		short wValue,
		short wIndex,
		short wLength )
	{ return createControlSetupPacket( bmRequestType, bRequest, wValue, wIndex, wLength, new byte[8] ); }

	/**
	 * Format parameters into a Control submission Setup byte[]
	 * @param bmRequestType see USB spec sec 9.3
	 * @param bRequest see USB spec sec 9.3
	 * @param wValue see USB spec sec 9.3
	 * @param wIndex see USB spec sec 9.3
	 * @param wLength see USB spec sec 9.3
	 * @return a byte[] representing the Setup packet
	 */
	public static byte[] createControlSetupPacket(
		int bmRequestType,
		int bRequest,
		int wValue,
		int wIndex,
		int wLength )
	{ return createControlSetupPacket( bmRequestType, bRequest, wValue, wIndex, wLength, new byte[8] ); }

	/**
	 * Format parameters into a Control submission Setup byte[]
	 * @param bmRequestType see USB spec sec 9.3
	 * @param bRequest see USB spec sec 9.3
	 * @param wValue see USB spec sec 9.3
	 * @param wIndex see USB spec sec 9.3
	 * @param wLength see USB spec sec 9.3
	 * @param setup the setup packet is created in the first 8 bytes of this
	 * @return a byte[] representing the Setup packet
	 * @exception ArrayIndexOutOfBoundsException if the setup byte[] is less than 8 bytes
	 */
	public static byte[] createControlSetupPacket(
		int bmRequestType,
		int bRequest,
		int wValue,
		int wIndex,
		int wLength,
		byte[] setup )
	{
		return createControlSetupPacket(
			(byte)bmRequestType,
			(byte)bRequest,
			(short)wValue,
			(short)wIndex,
			(short)wLength,
			setup );
	}

	/**
	 * Format parameters into a Control submission Setup byte[]
	 * @param bmRequestType see USB spec sec 9.3
	 * @param bRequest see USB spec sec 9.3
	 * @param wValue see USB spec sec 9.3
	 * @param wIndex see USB spec sec 9.3
	 * @param wLength see USB spec sec 9.3
	 * @param setup the setup packet is created in the first 8 bytes of this
	 * @return a byte[] representing the Setup packet
	 * @exception ArrayIndexOutOfBoundsException if the setup byte[] is less than 8 bytes
	 */
	public static byte[] createControlSetupPacket(
		byte bmRequestType,
		byte bRequest,
		short wValue,
		short wIndex,
		short wLength,
		byte[] setup )
	{
		setup[0] = bmRequestType;
		setup[1] = bRequest;
		setup[2] = (byte)wValue;
		setup[3] = (byte)(wValue >> 8);
		setup[4] = (byte)wIndex;
		setup[5] = (byte)(wIndex >> 8);
		setup[6] = (byte)wLength;
		setup[7] = (byte)(wLength >> 8);

		return setup;
	}

	/**
	 * Get the direction of an active UsbIrp
	 * @return the direction of the UsbIrp
	 * @throws javax.usb.UsbException if the direction cannot be determined
	 * @see javax.usb.UsbPipeConst#PIPE_DIRECTION_IN
	 * @see javax.usb.UsbPipeConst#PIPE_DIRECTION_OUT
	 */
	public static byte getDirection( UsbIrp irp ) throws UsbException
	{
		try {
			if ( UsbPipeConst.PIPE_TYPE_CONTROL == irp.getUsbPipe().getType() )
				return (byte)( UsbPipeConst.PIPE_DIRECTION_MASK & irp.getData()[0] );
			else
				return (byte)( UsbPipeConst.PIPE_DIRECTION_MASK & irp.getUsbPipe().getEndpointAddress() );
		} catch ( NullPointerException npE ) {
			throw new UsbException( "Could not determine UsbIrp direction (Null)" );
		} catch ( ArrayIndexOutOfBoundsException aioobE ) {
			throw new UsbException( "Could not determine UsbIrp direction (Data array too short)" );
		}
	}


	/**
	 * @return the low byte of the short value passed
	 * @param s the short value
	 */
	public static byte lowByte( short s ) { return (byte)s; }

	/**
	 * @return the high byte of the short value passed
	 * @param s the short value
	 */
	public static byte highByte( short s ) { return (byte)( s >> 8 ); }

	/**
	 * @return a formatted hex string from the byte[] passed
	 * @param byteArray the byte[] object
	 * <b>NOTE: this will be improved to do nicer hexdump like formatting</b>
	 */
	public static String toFormatedHexString( byte[] byteArray )
	{
		StringBuffer sb = new StringBuffer();

		for( int i = 0; i < byteArray.length; ++i )
			sb.append( toHexString( byteArray[ i ] ) + " " );

		return sb.toString();
	}
}
