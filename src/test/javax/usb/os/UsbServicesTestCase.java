package javax.usb.os;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.*;
import javax.usb.event.*;
import javax.usb.util.*;

import junit.framework.*;

/**
 * A Test for the UsbServices
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public class UsbServicesTestCase extends TestCase 
{
	//-------------------------------------------------------------------------
    // Ctor(s)
	//

    /** Constructor */
    public UsbServicesTestCase( String name ) { super( name ); }

	//-------------------------------------------------------------------------
    // Protected methods
	//

    protected void setUp() 
	{
        listener1 = new UsbServicesListener() 
					{
						public void usbDeviceAttached(UsbServicesEvent event) { }
						public void usbDeviceDetached(UsbServicesEvent event) { }
					};

        listener2 = new UsbServicesListener() 
					{
						public void usbDeviceAttached(UsbServicesEvent event) { }
						public void usbDeviceDetached(UsbServicesEvent event) { }
					};
    }

    protected void tearDown() 
	{
        listener2 = null;
        listener1 = null;
        rootHub = null;
        services = null;
    }

	//-------------------------------------------------------------------------
    // Public test methods
	//

	public void testGetUsbServices()
	{
        try 
		{ 
			services = UsbHostManager.getInstance().getUsbServices();

			assertTrue( "Got UsbServices that was null", services != null );
		}
        catch( Exception e ) 
		{ 
			fail( "Could not get UsbServices : " + e.getMessage() ); 
		}
	}

	public void testGetUsbRootHub()
	{
        try 
		{ 
			testGetUsbServices();
			rootHub = services.getUsbRootHub(); 
			
			assertTrue( "UsbServices.getUsbRootHub() returned null", rootHub != null );
		}
        catch( Exception e ) 
		{ fail( "Could not get Root Hub : " + e.getMessage() ); }
	}

	public void testGetRequestFactory()
	{
		try
		{
			testGetUsbServices();
			RequestFactory factory = services.getRequestFactory();

			assertTrue( "UsbServices.getRequestFactory() returned null", factory != null );
			assertTrue( "UsbServices.getRequestFactory() returned different factory object", 
					factory == services.getRequestFactory() );
		}
		catch( Exception e )
		{ fail( "Unexpected exception while getting RequestFactory, Exception.message = " + e.getMessage() ); }
	}

    public void testUsbServices() 
	{
		testGetUsbServices();
		testGetUsbRootHub();

        services.addUsbServicesListener( listener1 );
        services.addUsbServicesListener( listener2 );
        services.removeUsbServicesListener( listener1 );
        services.removeUsbServicesListener( listener2 );

        services.addUsbServicesListener( listener1 );
        services.addUsbServicesListener( listener1 );
        services.removeUsbServicesListener( listener1 );
        services.removeUsbServicesListener( listener2 );
        services.removeUsbServicesListener( listener1 );
    }

	public void testBfsDevices() throws UsbException
	{
		services = UsbHostManager.getInstance().getUsbServices();

		UsbInfoIterator bfsDevices = services.bfsUsbDevices( services.getUsbRootHub() );

		//<temp>
		while( bfsDevices.hasNext() )
			System.out.println( bfsDevices.nextUsbInfo().getName() );
		//</temp>
	}

	public void testDfsDevices() throws UsbException
	{
		services = UsbHostManager.getInstance().getUsbServices();

		UsbInfoIterator dfsDevices = services.dfsUsbDevices( services.getUsbRootHub() );

		//<temp>
		while( dfsDevices.hasNext() )
			System.out.println( dfsDevices.nextUsbInfo().getName() );
		//</temp>
	}

	//-------------------------------------------------------------------------
    // Instance variables
	//

    private UsbServices services = null;
    private UsbRootHub rootHub = null;
    private UsbServicesListener listener1 = null;
    private UsbServicesListener listener2 = null;
}
