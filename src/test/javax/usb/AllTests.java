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

/**
 * A TestSuite that puts together all the test classes from
 * all javax.usb.* packages
 * @author Helen Li
 * @author Dan Streetman
 */
public class AllTests extends TestSuite
{
	//-------------------------------------------------------------------------
	// Public class methods
	//

	public static TestSuite suite()
	{
		TestSuite suite = new AllTests();

		/* This package */
		suite.addTestSuite( ClassOperationsTestCase.class );
		suite.addTestSuite( RequestBundleTestCase.class );
		suite.addTestSuite( RequestFactoryTestCase.class );
		suite.addTestSuite( StandardOperationsTestCase.class );
		suite.addTestSuite( UsbCompositeIrpTestCase.class );
		suite.addTestSuite( UsbConfigTestCase.class );
		suite.addTestSuite( UsbDeviceTestCase.class );
		suite.addTestSuite( UsbEndpointTestCase.class );
		suite.addTestSuite( UsbHostManagerTestCase.class );
		suite.addTestSuite( UsbHubTestCase.class );
		suite.addTestSuite( UsbInterfaceTestCase.class );
		suite.addTestSuite( UsbIrpTestCase.class );
		suite.addTestSuite( UsbPipeTestCase.class );
		suite.addTestSuite( UsbPortTestCase.class );
		suite.addTestSuite( UsbRootHubTestCase.class );
		suite.addTestSuite( UsbTestCase.class );
		suite.addTestSuite( VendorOperationsTestCase.class );

		/* Subpackages */
		suite.addTest( javax.usb.event.AllTests.suite() );
		suite.addTest( javax.usb.os.AllTests.suite() );
		suite.addTest( javax.usb.test.AllTests.suite() );
		suite.addTest( javax.usb.util.AllTests.suite() );

		return suite;
	}
}

