package javax.usb.os;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.*;
import javax.usb.util.UsbInfoIterator;

/**
 * Interface to access the USB device model topology tree.
 * <p>
 * This interface also includes utility methods to return UsbDevices from
 * a UsbHub in:
 * <ul>
 * <li>Breadth-first search order (BFS)</li>
 * <li>Depth-first search order (DFS)</li>
 * </ul>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbTopologyServices
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
	 * @return a UsbInfoIterator of UsbDevice in breadth-first search (BFS) order
	 * @param usbHub the UsbHub object whose children will be queried
	 * <i>NOTE: since UsbHub are UsbDevice then they are also included in return list</i>
	 */
	public UsbInfoIterator bfsUsbDevices( UsbHub usbHub );

	/**
	 * @return a UsbInfoIterator of UsbDevice in depth-first search (DFS) order
	 * @param usbHub the UsbHub object whose children will be queried
	 * <i>NOTE: since UsbHub are UsbDevice then they are also included in return list</i>
	 */
	public UsbInfoIterator dfsUsbDevices( UsbHub usbHub );
}
