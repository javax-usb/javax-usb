package javax.usb.test;

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
 * the javax.usb.test package
 * @author E. Michael Maximilien
 */
public class AllTests extends TestSuite
{
	//-------------------------------------------------------------------------
	// Public class methods
	//

	public static TestSuite suite()
	{
		TestSuite suite = new AllTests();

		suite.addTest( new JUnitUtilityTestCase( "testIsEqualsEnum" ) );
		suite.addTest( new JUnitUtilityTestCase( "testIsIdenticalEnum" ) );
		suite.addTest( new JUnitUtilityTestCase( "testIsEqualsVector" ) );

		return suite;
	}
}
