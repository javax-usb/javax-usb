package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

/**
 * Defines an interface for outputing tracing info
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface TracerOutput
{
    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
     * Prints a string appended with a new line to the tracer output
     * @param s the String to print
     */
    public void println( String s );

    /**
     * Prints a string appended without a new line to the tracer output
     * @param s the String to print
     */
    public void print( String s );
}
