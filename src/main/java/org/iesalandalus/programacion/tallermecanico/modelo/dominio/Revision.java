package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate fechaInicio;
    LocalDate fechaFin;
    int horas = 0;
    float precioMaterial;

    Cliente cliente;
    Vehiculo vehiculo;


    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }

    public Revision(Revision revision) {
    Objects.requireNonNull(revision,"La revisión no puede ser nula.");
    this.cliente = new Cliente(revision.cliente);
    this.vehiculo = revision.vehiculo;
    this.fechaInicio = revision.fechaInicio;
    fechaFin = revision.fechaFin;
    horas = revision.horas;
    precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio){
        Objects.requireNonNull(fechaInicio,"La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }
    public void anadirHoras(int cantidad) throws TallerMecanicoExcepcion {
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        } else if (cantidad <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        horas = horas + cantidad;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float cantidad) throws TallerMecanicoExcepcion{
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }else if (cantidad <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        precioMaterial = precioMaterial + cantidad;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        return (getHoras() * PRECIO_HORA) + (getPrecioMaterial() * PRECIO_MATERIAL) + (getDias() * PRECIO_DIA);
    }

    private float getDias() {
        float mostrar;
        if (estaCerrada()) {
            mostrar = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        } else {
            mostrar = 0;
        }
        return mostrar;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        // Formatear los números con coma como separador decimal
        Locale locale = new Locale("es", "ES");
        String precioMaterialFormateado = String.format(locale, "%.2f", precioMaterial);

        String mostrar;
        if (fechaFin == null) {
            mostrar = String.format("%s - %s: (%s - ), %s horas, %s € en material",cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas,precioMaterialFormateado);
        } else {
            String precioTotalFormateado = String.format(locale, "%.2f", getPrecio());
            mostrar = String.format("%s - %s: (%s - %s), %s horas, %s € en material, %s € total",cliente, vehiculo, fechaInicio.format(FORMATO_FECHA),fechaFin.format(FORMATO_FECHA), horas,precioMaterialFormateado,precioTotalFormateado);
        }

        return mostrar;
    }
}