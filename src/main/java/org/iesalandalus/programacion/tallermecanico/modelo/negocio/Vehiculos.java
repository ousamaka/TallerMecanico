package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private final List<Vehiculo> Coleccionvehiculos;

    public Vehiculos() {
        Coleccionvehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(Coleccionvehiculos); // Devuelve copia defensiva
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (Coleccionvehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        Coleccionvehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        Vehiculo vehiculoEncontrado = null;
        if (Coleccionvehiculos.contains(vehiculo)) {
            vehiculoEncontrado = vehiculo;
        }
        return vehiculoEncontrado;
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (!Coleccionvehiculos.remove(vehiculo)) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
    }
}
