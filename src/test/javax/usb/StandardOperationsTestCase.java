package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

import junit.framework.*;

import javax.usb.*;
import javax.usb.util.*;
import javax.usb.test.*;

/**
 * A JUnit Test for StandardOperations interface and implementing class
 * @author E. Michael Maximilien
 */
public class StandardOperationsTestCase extends UsbTestCase
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	public StandardOperationsTestCase( String name ) { super( name ); }

	//-------------------------------------------------------------------------
	// Protected overridden methods
	//

	protected void setUp() 
	{
		try
		{
			UsbRootHub rootHub = UsbHostManager.getInstance().getUsbServices().getUsbRootHub();
			dfsDevicesIterator = UsbHostManager.getInstance().getUsbServices().dfsUsbDevices( rootHub );
			requestFactory = UsbHostManager.getInstance().getUsbServices().getRequestFactory();
		}
		catch( UsbException e ) { fail( "Error accessing USB devices! UsbException.message = " + e.getMessage() ); }
	
		assertTrue( "Got null dfsDevicesIterator", dfsDevicesIterator != null );
		assertTrue( "Got null requestFactory", requestFactory != null );
	}

	protected void tearDown() 
	{
		dfsDevicesIterator = null;
		requestFactory = null;
	}

	//-------------------------------------------------------------------------
	// Public testXyz methods
	//

	public void testClearFeature() throws RequestException, UsbException
	{
	}

	public void testGetConfiguration() throws RequestException, UsbException
	{
		byte[] configData = new byte[ 1 ];

		while( dfsDevicesIterator.hasNext() )
		{
			UsbDevice usbDevice = (UsbDevice)dfsDevicesIterator.nextUsbInfo();

			if( usbDevice.isUsbHub() ) continue;

			StandardOperations standardOps = usbDevice.getStandardOperations();
			
			assertTrue( "Got a null UsbDevice object", usbDevice != null );
			assertTrue( "Got a null StandardOperations object", standardOps != null );

			Request request = standardOps.getConfiguration( configData );
			
			assertTrue( "standardOps.getConfiguration( configData ) returned null Request",
						request != null );
			
			assertTrue( "request.getData should be configData", request.getData() == configData );

			//<temp>
			System.out.println( "request.getData() == [ " + UsbUtil.toFormatedHexString( request.getData() ) + " ]" );
			//<temp>
		}
	}

	public void testGetDescriptor() throws RequestException, UsbException
	{
	}

	public void testGetInterface() throws RequestException, UsbException
	{
	}

	public void testGetStatus() throws RequestException, UsbException
	{
	}

	public void testSetAddress() throws RequestException, UsbException
	{
	}

	public void testSetConfiguration() throws RequestException, UsbException
	{
	}

	public void testSetDescriptor() throws RequestException, UsbException
	{
	}

	public void testSetFeature() throws RequestException, UsbException
	{
	}

	public void testSetInterface() throws RequestException, UsbException
	{
	}

	public void testSynchFrame() throws RequestException, UsbException
	{
	}

	//-------------------------------------------------------------------------
	// Instance variables
	//

	private UsbInfoIterator dfsDevicesIterator = null;
	private RequestFactory requestFactory = null;
}
