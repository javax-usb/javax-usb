package javax.usb;

/*
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
 * A JUnit Test for RequestBundle interface and implementing class
 * @author E. Michael Maximilien
 */
public class RequestBundleTestCase extends UsbTestCase
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	public RequestBundleTestCase( String name ) { super( name ); }

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

		createRequestBundles();
		createRequests();
	}

	protected void tearDown() 
	{
		requestFactory = null;

		bundle0 = null;
		bundle1 = null;

		for( int i = 0; i < REQUEST_ARRAY_SIZE; ++i )
			requestArray[ i ] = null;
	}

	//-------------------------------------------------------------------------
	// Private methods
	//

	private void createRequestBundles()
	{
		bundle0 = requestFactory.createRequestBundle();
		bundle1 = requestFactory.createRequestBundle();

		assertTrue( "Got a null RequestBundle from RequestFactory", bundle0 != null );
		assertTrue( "Got a null RequestBundle from RequestFactory", bundle1 != null );
		assertTrue( "Made 2 createRequestBundle() calls and got same RequestBundle", 
					bundle0 != bundle1 );
	}

	private void createRequests()
	{
		try
		{
			for( int i = 0; i < REQUEST_ARRAY_SIZE; ++i )
				requestArray[ i ] = requestFactory.
									createVendorRequest( (byte)0x01, (byte)RequestConst.REQUESTTYPE_TYPE_VENDOR,
									 					 (short)0x0000, (short)0x0001,
														 new byte[ 8 ] );
		}
		catch( RequestException re )
		{ fail( "Could not create Request objects.  RequestExcetpion.message == " + re.getMessage() ); }
	}

	//-------------------------------------------------------------------------
	// Public testXyz methods
	//

	public void testIsInSubmission()
	{
	}

	public void testAddStandardRequest() throws UsbRuntimeException
	{	
	}

	public void testAdd() throws UsbRuntimeException
	{	
		assertTrue( "bundle1.size() should be 0", bundle1.size() == 0 );

		bundle1.add( requestArray[ 1 ] );
		bundle1.add( requestArray[ 2 ] );
		bundle1.add( requestArray[ 3 ] );

		assertTrue( "bundle1.size() should be 3", bundle1.size() == 3 );
	}

	public void testRemove( Request request ) throws UsbRuntimeException
	{
		assertTrue( "bundle1.size() should be 0", bundle1.size() == 0 );

		bundle1.add( requestArray[ 1 ] );
		bundle1.add( requestArray[ 2 ] );
		bundle1.add( requestArray[ 3 ] );

		assertTrue( "bundle1.size() should be 3", bundle1.size() == 3 );

		bundle1.remove( requestArray[ 2 ] );
		
		assertTrue( "bundle1.size() should be 2", bundle1.size() == 2 );

		bundle1.remove( requestArray[ 1 ] );
		bundle1.remove( requestArray[ 3 ] );

		assertTrue( "bundle1.size() should be 0", bundle1.size() == 0 );
	}

	public void testRemoveAll() throws UsbRuntimeException
	{
		bundle1.add( requestArray[ 1 ] );
		bundle1.add( requestArray[ 2 ] );
		bundle1.add( requestArray[ 3 ] );

		assertTrue( "bundle1.size() should be 3", bundle1.size() == 3 );

		bundle1.removeAll();

		assertTrue( "bundle1.size() should be 0", bundle1.size() == 0 );
	}

	public void testSize()
	{
		assertTrue( "New bundle size is not 0", bundle0.size() == 0 );
		assertTrue( "New bundle size is not 0", bundle1.size() == 0 );

		bundle0.add( requestArray[ 0 ] );
		
		bundle1.add( requestArray[ 1 ] );
		bundle1.add( requestArray[ 2 ] );
		bundle1.add( requestArray[ 3 ] );

		assertTrue( "bundle0.size() should be 1", bundle0.size() == 1 );
		assertTrue( "bundle1.size() should be 3", bundle1.size() == 3 );
	}

    public void testIsEmpty()
	{
		assertTrue( "New bundle isEmpty() == false should be true", bundle0.isEmpty() == true );
		assertTrue( "New bundle isEmpty() == false should be true", bundle1.isEmpty() == true );

		bundle0.add( requestArray[ 0 ] );
		bundle1.add( requestArray[ 1 ] );
		bundle1.add( requestArray[ 2 ] );

		assertTrue( "New bundle isEmpty() == true should be false", bundle0.isEmpty() == false );
		assertTrue( "New bundle isEmpty() == true should be false", bundle1.isEmpty() == false );
	}

	public void testRequestIterator()
	{
		RequestIterator bundle0Iterator = bundle0.requestIterator();
		RequestIterator bundle1Iterator = bundle1.requestIterator();

		assertTrue( "RequestBundle.requestIterator() == null", bundle0Iterator != null );
		assertTrue( "RequestBundle.requestIterator() == null", bundle1Iterator != null );

		assertTrue( "Empty RequestBundle.requestIterator() should not have any element", 
					bundle0.isEmpty() == true && bundle0Iterator.hasNext() == false );
		
		assertTrue( "Empty RequestBundle.requestIterator() should not have any element", 
					bundle1.isEmpty() == true && bundle1Iterator.hasNext() == false );
	}

	public void testRecycle()
	{
		try
		{
			bundle0.add( requestArray[ 0 ] );
			bundle0.add( requestArray[ 1 ] );

			bundle0.recycle();
			bundle1.recycle();

			assertTrue( "RequestBundle.size() == 0 after recycle()", bundle0.size() == 0 );
			assertTrue( "RequestBundle.size() == 0 after recycle()", bundle1.size() == 0 );
		}
		catch( Exception e ) 
		{ fail( "RequestBundle.recycle() caused Exception.message == " + e.getMessage() ); }
	}

	//-------------------------------------------------------------------------
	// Instance variables
	//

	private RequestFactory requestFactory = null;

	private RequestBundle bundle0 = null;
	private RequestBundle bundle1 = null;

	private Request[] requestArray = new Request[ REQUEST_ARRAY_SIZE ];

	//-------------------------------------------------------------------------
	// Class constants
	//

	public static final int REQUEST_ARRAY_SIZE = 10;
}
