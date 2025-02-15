package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "([A-ZÁÉÍÓÚ][a-záéíóú]+)( [A-ZÁÉÍÓÚ][a-záéíóú]+)*";
    private static final String ER_DNI = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
    private static final String ER_TELEFONO = "\\d{9}";
    private static final char[] LETRAS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};


    //Crear Taller mecanico Excepcion
    String nombre,dni,telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    //Contructor copia:
    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"No es posible copiar un cliente nulo.");
        cliente.nombre = nombre;
        cliente.dni = dni;
        cliente.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
       Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni,"El DNI no puede ser nulo.");

        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        } else if (comprobarLetraDni(dni) == false) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni (String dni) {
        boolean formato = false;
        String cadenaDniSinLetra = dni.substring(0,dni.length() - 1);
        String cadenaLetraDni = String.valueOf(dni.charAt(dni.length()- 1));
        char letraDniChar = cadenaLetraDni.charAt(0);
        int enteroDNI = Integer.parseInt(cadenaDniSinLetra);

        if (LETRAS_DNI[enteroDNI%23] == letraDniChar) {
            formato = true;
        }
        return formato;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");

        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");

        }
        this.telefono = telefono;
    }

    public static Cliente get (String dni) {
        return new Cliente("Patricio Estrella<",dni,"950111111");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}

