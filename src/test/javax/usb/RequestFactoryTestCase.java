package javax.usb;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import junit.framework.*;

import javax.usb.*;
import javax.usb.test.*;

/**
 * Defines a TestCase for the RequestFactory interface
 * @author E. Michael Maximilien
 */
public class RequestFactoryTestCase extends UsbTestCase
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	public RequestFactoryTestCase( String name ) { super( name ); }

	//-------------------------------------------------------------------------
	// Protected overridden methods
	//

	protected void setUp()
	{
		try
		{
			requestFactory = UsbHostManager.getInstance().getUsbServices().getRequestFactory();
		}
		catch( UsbException ue )
		{ fail( "Got UsbException.message = " + ue.getMessage() + " while getting RequestFactory" ); }

		byteArray1 = new byte[ 1 ];
		byteArray2 = new byte[ 2 ];
		byteArray8 = new byte[ 8 ];
	}

	protected void tearDown() 
	{
		requestFactory = null;

		byteArray1 = null;
		byteArray2 = null;
		byteArray8 = null;
	}

	//-------------------------------------------------------------------------
	// Public testXyz methods
	//

	public void testRecycle()
	{
	}

	public void testCreateRequestBundle()
	{
		RequestBundle bundle0 = requestFactory.createRequestBundle();
		RequestBundle bundle1 = requestFactory.createRequestBundle();

		assertTrue( "Got a null RequestBundle from RequestFactory", bundle0 != null );
		assertTrue( "Got a null RequestBundle from RequestFactory", bundle1 != null );
		assertTrue( "Made 2 createRequestBundle() calls and got same RequestBundle", 
					bundle0 != bundle1 );
	}

	public void testCreateVendorRequest() throws RequestException
	{
	}
	
	public void testCreateClassRequest() throws RequestException
	{
	}

	public void testCreateClearFeatureRequest() throws RequestException
	{
		Request clearFeatureRequest0 = requestFactory.createClearFeatureRequest( RequestConst.REQUEST_CLEAR_FEATURE,
									 											 (short)0x0000,
																				 (short)0x0000 );

		Request clearFeatureRequest1 = requestFactory.createClearFeatureRequest( RequestConst.REQUEST_CLEAR_FEATURE,
									 											 (short)0x0000,
																				 (short)0x0000 );

		assertTrue( "Got a null Request from createClearFeatureRequest() method call",
					clearFeatureRequest0 != null );
		assertTrue( "Got a null Request from createClearFeatureRequest() method call",
					clearFeatureRequest1 != null );
		assertTrue( "Made 2 createXyzRequest() calls w/o recycling and got the same Request",
					clearFeatureRequest0 != clearFeatureRequest1 );
	}

	public void testCreateGetConfigurationRequest() throws RequestException
	{
		Request getConfigurationRequest0 = requestFactory.
										   createGetConfigurationRequest( byteArray1 );

		Request getConfigurationRequest1 = requestFactory.
										   createGetConfigurationRequest( byteArray1 );

		assertTrue( "Got a null Request from createGetConfigurationRequest() method call",
					getConfigurationRequest0 != null );
		assertTrue( "Got a null Request from createGetConfigurationRequest() method call",
					getConfigurationRequest1 != null );
		assertTrue( "Made 2 createXyzRequest() calls w/o recycling and got the same Request",
					getConfigurationRequest0 != getConfigurationRequest1 );
	}

	public void testCreateGetDescriptorRequest() throws RequestException
	{
		/* 
		//<TEMP>
		Request getDescriptorRequest0 = requestFactory.
										createGetDescriptorRequest( (short)0x001,
																	(short)0x001,
																	 byteArray8 );

		Request getDescriptorRequest1 = requestFactory.
										createGetDescriptorRequest( (short)0x001,
																    (short)0x001,
										                            byteArray8 );

		assertTrue( "Got a null Request from createGetDescriptorRequest() method call",
					getDescriptorRequest0 != null );
		assertTrue( "Got a null Request from createGetDescriptorRequest() method call",
					getDescriptorRequest1 != null );
		assertTrue( "Made 2 createXyzRequest() calls w/o recycling and got the same Request",
					getDescriptorRequest0 != getDescriptorRequest1 );
		//</TEMP>
		*/
	}

	public void testCreateGetInterfaceRequest() throws RequestException
	{
	}

	public void testCreateGetStatusRequest() throws RequestException
	{
	}

	public void testCreateSetAddressRequest() throws RequestException
	{
	}

	public void testCreateSetConfigurationRequest() throws RequestException
	{
	}

	public void testCreateSetDescriptorRequest() throws RequestException
	{
	}

	public void testCreateSetFeatureRequest() throws RequestException
	{
	}

	public void testCreateSetInterfaceRequest() throws RequestException
	{
	}
	
	public void testCreateSynchFrameRequest() throws RequestException
	{
	}

	public void testCreateGetState() throws RequestException
	{
	}

	//-------------------------------------------------------------------------
	// Instance variables
	//

	private RequestFactory requestFactory = null;

	private byte[] byteArray1 = null;
	private byte[] byteArray2 = null;
	private byte[] byteArray8 = null;
}
