package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import junit.framework.*;

import javax.usb.util.*;
import javax.usb.os.*;

/**
 * A Test for the UsbHostManager
 * @author Dan Streetman
 */
public class UsbHostManagerTestCase extends UsbTestCase {

	/** Constructor */
	public UsbHostManagerTestCase( String name ) { super( name ); }

	//*************************************************************************
	// Protected methods

	/** Set up tests */
	protected void setUp()
	{
		hostManager = UsbHostManager.getInstance();
	}

	/** Tear down test */
	protected void tearDown()
	{
	}

	//*************************************************************************
	// Public test methods

	public void testUsbHostManager()
	{
		assertNotNull( "Couldn't get UsbHostManager", hostManager );
		assertSame( "UsbHostManager returned 2 different instances", hostManager, UsbHostManager.getInstance() );

		properties = hostManager.getUsbProperties();

		try { services = hostManager.getUsbServices(); }
		catch ( UsbException uE ) {
			fail( "Could not get UsbServices" );
		}
	}

	//*************************************************************************
	// Instance variables

	private UsbHostManager hostManager = null;
	private UsbProperties properties = null;
	private UsbServices services = null;

}
