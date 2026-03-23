package com.universidad.tienda.decorator;

// Decorator base — delega al componente interno
public abstract class OrdenServicioDecorator implements OrdenServicio {
    protected final OrdenServicio wrapped;
    public OrdenServicioDecorator(OrdenServicio wrapped) {
        this.wrapped = wrapped;
    }
}
