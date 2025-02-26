package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {

    Cliente cliente;

    private List<Cliente> clientes;

    public void Clientes () {
        clientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes); // Devuelve copia
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (clientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        clientes.add(cliente);
    }

    public boolean modificar(Cliente cliente,String nombre, String telefono) {
        boolean cumpleRequisito = true;
        Objects.requireNonNull(cliente,"No se puede modificar un cliente nulo.");

        Cliente posicionEncontrado = buscar(cliente);
        if (posicionEncontrado == null) {
            throw new IllegalArgumentException("El cliente no esta en la lista Clientes");
        }
        if (telefono.isBlank() && nombre != null){
            posicionEncontrado.setNombre(nombre);
        }
        if (nombre.isBlank() && telefono != null){
            posicionEncontrado.setTelefono(telefono);
        }
        if (nombre != null && telefono != null){
            posicionEncontrado.setNombre(nombre);
            posicionEncontrado.setTelefono(telefono);

        }
        return cumpleRequisito;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        Cliente clienteEncontrado = null;
        int posicion = clientes.indexOf(cliente);
        if (posicion != -1) {
            clienteEncontrado = clientes.get(posicion);
        }
        return clienteEncontrado;
    }
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!clientes.remove(cliente)) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese dni.");
        }
    }
}


