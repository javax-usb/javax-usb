package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

/**
 * Defines the super-interface for all USB descriptor visitors
 * <P>
 * Visitors are a pattern that in a nutshell allows the easy extension by clients
 * to a stable hierarchy.  Clients can use visitors to add methods to a hierarchy w/o
 * modifying or accessing the code to the hierarchy interfaces/classes.  
 * <P>
 * Please see GoF (Design Pattern book) page 331 for details about the visitor pattern.
 * </P>
 * </P>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface DescriptorVisitor
{
    //-------------------------------------------------------------------------
    // Visitor interface methods
    //

    /**
     * Default visit method for all Descriptor
     * @param descriptor the Descriptor to visit
     */
    void visitDescriptor( Descriptor descriptor );

    /**
     * Visit method for InterfaceDescriptor
     * @param descriptor the Descriptor to visit
     */
    void visitInterfaceDescriptor( Descriptor descriptor );

    /**
     * Visit method for ConfigDescriptor
     * @param descriptor the Descriptor to visit
     */
    void visitConfigDescriptor( Descriptor descriptor );

    /**
     * Visit method for DeviceDescriptor
     * @param descriptor the Descriptor to visit
     */
    void visitDeviceDescriptor( Descriptor descriptor );

    /**
     * Visit method for EndpointDescriptor
     * @param descriptor the Descriptor to visit
     */
    void visitEndpointDescriptor( Descriptor descriptor );

    /**
     * Visit method for StringDescriptor
     * @param descriptor the Descriptor to visit
     */
    void visitStringDescriptor( Descriptor descriptor );
}
