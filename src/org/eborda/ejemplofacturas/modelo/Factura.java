package org.eborda.ejemplofacturas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {

    private int folio;
    private String descripcion;
    private Date fecha;
    private Cliente cliente;
    private DetalleFactura[] detalle; //Es un arreglo
    private int indiceDetalle;
    public static final int MAX_DETALLES = 12;
    private static int ultimoFolio;

    public Factura(String descripcion, Cliente cliente) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.detalle = new DetalleFactura[MAX_DETALLES];
        this.folio = ++ultimoFolio;
        this.fecha = new Date();
    }

    public int getFolio() {
        return folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DetalleFactura[] getDetalle() {
        return detalle;
    }

    public void addDetalleFactura(DetalleFactura detalle) {

        if (indiceDetalle < MAX_DETALLES) {
            this.detalle[indiceDetalle++] = detalle;
        }

    }

    public float calcularTotal() {
        float total = 0.0f;
        for (DetalleFactura detalle : this.detalle) {
            if (detalle == null) {
                continue;
            }
            total += detalle.calcularImporte();
        }
        return total;
    }

    public String generarDetalle() {
        StringBuilder sb = new StringBuilder("Factura N°: ");
        sb.append(folio)
                .append("\nCliente: ")
                .append(this.cliente.getNombre())
                .append("\t NIF: ")
                .append(this.cliente.getNif())
                .append("\nDescripción: ")
                .append(this.descripcion)
                .append("\n")
                .append("\n#\tNombre\t$\tCant.\tTotal\n");

        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM, yyyy");
        sb.append("Fecha Emisión: ")
                .append(df.format(this.fecha))
                .append("\n");

        for (DetalleFactura detalle : this.detalle){
            if (detalle == null){
                continue;
            }
            sb.append(detalle.getProducto().getCodigo())
                    .append("\t")
                    .append(detalle.getProducto().getNombre())
                    .append("\t")
                    .append(detalle.getProducto().getPrecio())
                    .append("\t")
                    .append(detalle.getCantidad())
                    .append("\t")
                    .append(detalle.calcularImporte())
                    .append("\n");
        }
        sb.append("\nGran Total: ")
                .append(calcularTotal());

        return sb.toString();
    }
}
