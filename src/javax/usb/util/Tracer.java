package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Tracing class to help in the debugging of the JCL and JavaPOS controls
 * <b>NOTE:</b> this class is a Singleton (see GoF Design Pattern book)
 *         access the sole instance by doing: Tracer.getInstance() call
 * <b>NOTE2:</b> Will allow (in the future) the option of defining different
 *          ouput for the tracer
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class Tracer extends Object
{
    //-------------------------------------------------------------------------
    // Ctor
    //

    /** Make ctor private to avoid construction (this is a Singleton class) */
    private Tracer() {}

    //-------------------------------------------------------------------------
    // Public class methods
    //

    /** @return the sole instance of this class (creating it if necessary) */
    public static Tracer getInstance()
    {
        if( instance == null )
        {
            instance = new Tracer();

            instance.init();
        }

        return instance;
    }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
     * Prints a string appended with a new line to the tracer output
     * @param s the String to print
     */
    public void println( String s ) { getTracerOutput().println( s ); }

    /**
     * Prints a string appended without a new line to the tracer output
     * @param s the String to print
     */
    public void print( String s ) { getTracerOutput().print( s ); }

    /**
     * Sets this tracer ON or OFF
     * @param b the boolean parameter
     */
    public void setOn( boolean b ) { tracerOn = b; }

    /** @return true if the tracer is ON (i.e. enabled) */
    public boolean isOn() { return tracerOn; }
     
    //-------------------------------------------------------------------------
    // Private methods
    //

    /** Intialize the current instance using the DefaultProperties class */
    private void init()
    {
        UsbProperties props = new DefaultProperties();
        props.loadProperties();

        if( !props.isPropertyDefined( UsbProperties.JUSB_TRACING_PROP_NAME ) )
            setOn( false );
        else
        {
            String tracingPropValue = props.getPropertyString( UsbProperties.JUSB_TRACING_PROP_NAME );

            if( tracingPropValue.equalsIgnoreCase( UsbProperties.JUSB_TRACING_ON_PROP_VALUE ) ||
                tracingPropValue.equalsIgnoreCase( UsbProperties.JUSB_TRACING_TRUE_PROP_VALUE ) )
                setOn( true );
        }
    }

    /** @return the tracerOutput object for the Tracer */
    private TracerOutput getTracerOutput() 
    { 
        return ( isOn() ? onTracerOutput : offTracerOutput );
    }

    //-------------------------------------------------------------------------
    // Private instance variables
    //

    private boolean tracerOn = false;

    private TracerOutput onTracerOutput = new DefaultTracerOutput();
    private TracerOutput offTracerOutput =  new TracerOutput()
                                            {
                                                public void println( String s ) {}
                                                public void print( String s ) {}
                                            };

    //-------------------------------------------------------------------------
    // Private class variables
    //

    private static Tracer instance = null;

    //-------------------------------------------------------------------------
    // Private static inner classes
    //

    /**
     * Inner class for a default TracerOutput.  Just prints out info to System.err
     * @author E. Michael Maximilien
     */
    static class DefaultTracerOutput extends Object implements TracerOutput
    {
        /** Default ctor */
        public DefaultTracerOutput() {}

        //---------------------------------------------------------------------
        // Public methods
        //
        
        /**
         * Prints a string appended with a new line to the tracer output
         * @param s the String to print
         */
        public void println( String s ) { System.err.println( s ); }

        /**
         * Prints a string appended without a new line to the tracer output
         * @param s the String to print
         */
        public void print( String s ) { System.err.print( s ); }
    }
}
