package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(11, "Insertar un cliente."),
    BUSCAR_CLIENTE(12, "Buscar un cliente."),
    BORRAR_CLIENTE(13,"Borrar un cliente."),
    LISTAR_CLIENTES(14,"Listar todos los clientes."),
    MODIFICAR_CLIENTE(15,"Modificar un cliente."),
    INSERTAR_VEHICULO(21,"Insertar un vehículo."),
    BUSCAR_VEHICULO(22,"Buscar un vehículo."),
    BORRAR_VEHICULO(23,"Borrar un vehículo."),
    LISTAR_VEHICULOS(24,"Listar todos los vehículos."),
    INSERTAR_REVISION(31, "Insertar trabajo de revisión."),
    INSERTAR_MECANICO(32,"Insertar un trabajo DE MECÁNICO."),
    BUSCAR_TRABAJO(33,"Buscar un trabajo."),
    BORRAR_TRABAJO(34,"Borrar un trabajo."),
    LISTAR_TRABAJOS(35,"Listar todos los trabajos."),
    LISTAR_TRABAJOS_CLIENTE(36,"Listar todas los trabajos de un cliente."),
    LISTAR_TRABAJOS_VEHICULO(37,"Listar todas los trabajos de un vehículo."),
    ANADIR_HORAS_TRABAJO(38,"Añadir horas a un trabajo."),
    ANADIR_PRECIO_MATERIAL_TRABAJO(39,"Añadir precio del material a un trabajo."),
    CERRAR_TRABAJO(40,"Cerrar un trabajo."),
    MOSTRAR_ESTADISTICAS_MENSUALES(41, "Mostrar estadísticas mensuales."),
    SALIR(0,"Salir del programa.");

    private final int codigo;
    private final String texto;
    private static final Map<Integer, Evento> eventos = new HashMap<>();

    static {
        for(Evento evento : values()){
            eventos.put(evento.codigo, evento);
        }
    }

    private Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {return codigo; }

    public static boolean esValida(int codigo){
        return eventos.containsKey(codigo);
    }

    public static Evento get(int codigo){
        if(!esValida(codigo)){
            throw new IllegalArgumentException("El código no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return texto;
    }
}
