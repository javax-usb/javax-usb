package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

/**
 * Defines a default superclass for DescriptorVisitor implementation classes
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class DefaultDescriptorV extends Object implements DescriptorVisitor
{
    //-------------------------------------------------------------------------
    // Visitor interface methods
    //

    /**
     * Default visit method for all Descriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitDescriptor( Descriptor descriptor ) {}

    /**
     * Visit method for InterfaceDescriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitInterfaceDescriptor( Descriptor descriptor ) {}

    /**
     * Visit method for ConfigDescriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitConfigDescriptor( Descriptor descriptor ) {}

    /**
     * Visit method for DeviceDescriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitDeviceDescriptor( Descriptor descriptor ) {}

    /**
     * Visit method for EndpointDescriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitEndpointDescriptor( Descriptor descriptor ) {}

    /**
     * Visit method for StringDescriptor
     * @param descriptor the Descriptor to visit
     */
    public void visitStringDescriptor( Descriptor descriptor ) {}
}
