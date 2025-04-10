package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private final List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente modificado = null;
        if (!coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        if (nombre == null && telefono == null) {
            modificado = null;
        } else if (telefono == null) {
            cliente.setNombre(nombre);
            modificado = cliente;
        } else if(nombre == null){
            cliente.setTelefono(telefono);
            modificado = cliente;
        }else {
            cliente.setTelefono(telefono);
            cliente.setNombre(nombre);
            modificado = cliente;
        }
        return modificado;
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        Cliente buscado;
        if (!coleccionClientes.contains(cliente)) {
            buscado = null;
        } else {
            buscado = coleccionClientes.get(coleccionClientes.indexOf(cliente));
        }
        return buscado;
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }

}