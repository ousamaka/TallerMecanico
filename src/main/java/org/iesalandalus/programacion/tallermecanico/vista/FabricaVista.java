package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public enum FabricaVista {
    TEXTO{
        @Override
        public org.iesalandalus.programacion.tallermecanico.vista.Vista crear() {
            return new VistaTexto();
        }
    };

    public abstract org.iesalandalus.programacion.tallermecanico.vista.Vista crear();
}