package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.lang.reflect.*;

import junit.framework.*;

/**
 * Superclass for all javax.usb API TestCases
 * @author E. Mchael Maximilien
 * @author Dan Streetman
 */
public class UsbTestCase extends TestCase
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	/** 
	 * Constructor
	 * @param name the name of the test case
	 */
	public UsbTestCase( String name ) { super( name ); }

	//-------------------------------------------------------------------------
	// Protected methods
	//

	/** Set up tests */
	protected void setUp() {}

	/** Tear down tests */
	protected void tearDown() {}

	//-------------------------------------------------------------------------
	// Test methods
	//

	/**
	 * Warning test.
	 * <p>
	 * This is a simple test case that allows subclasses without test methods
	 * to be used (during development), but prints a warning reminder to implement methods.
	 * Subclasses with at least one test??? method implemented (besides this) will not
	 * generate a warning.
	 */
	public void testPrintWarningMessageIfSubclassHasNoTestMethods()
	{
		String thisMethodName = "testPrintWarningMessageIfSubclassHasNoTestMethods";
		Method[] methods = getClass().getMethods();

		for (int i=0; i<methods.length; i++) {
			String name = methods[i].getName();

			if (name.startsWith( "test" ) && !(name.equals( thisMethodName )))
				return;
		}

		System.err.println("WARNING : " + getClass().getName() + " does not implement any test methods!");
	}

}
