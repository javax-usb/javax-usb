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
 * Interface describing the policy to claim an interface with.
 * @author Dan Streetman
 */
public interface UsbInterfacePolicy
{
	/**
	 * Whether to allow the UsbInterface to be released or not.
	 * <p>
	 * This is called when the claimed interface is released.
	 * @param usbInterface The UsbInterface to release.
	 * @param key The Object key passed to UsbInterface.release, or null.
	 * @return If the UsbInterface should be released.
	 */
	public boolean release(UsbInterface usbInterface, Object key);

	/**
	 * Whether to allow the UsbPipe to be opened or not.
	 * <p>
	 * This is called when a UsbPipe belonging to the claimed interface
	 * is opened.
	 * @param usbPipe The UsbPipe being opened.
	 * @param key The Object key passed to UsbPipe.open, or null.
	 * @return If the UsbPipe should be opened.
	 */
	public boolean open(UsbPipe usbPipe, Object key);

	/**
	 * If the claim should be forced.
	 * <p>
	 * This will try to forcibly claim the UsbInterface.
	 * This is only intended as a flag to the implementation
	 * to try everything possible to allow a successful claim.
	 * The implementation may try to override any other driver(s)
	 * that have the interface claimed.
	 * <p>
	 * The implementation is not required to use this flag.
	 * <p>
	 * <strong>WARNING</strong>: This should <i>only</i> be used
	 * if you are <i>absolutely sure</i> you want to drive the
	 * interface.
	 * @param usbInterface The UsbInterface being claimed.
	 * @return If the interface should be forcibly claimed.
	 */
	public boolean forceClaim(UsbInterface usbInterface);

}
