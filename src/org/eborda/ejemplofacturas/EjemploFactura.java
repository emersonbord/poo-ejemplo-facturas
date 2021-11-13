package org.eborda.ejemplofacturas;

import org.eborda.ejemplofacturas.modelo.Cliente;
import org.eborda.ejemplofacturas.modelo.DetalleFactura;
import org.eborda.ejemplofacturas.modelo.Factura;
import org.eborda.ejemplofacturas.modelo.Producto;

import java.util.Scanner;

public class EjemploFactura {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNif("555-4");
        cliente.setNombre("Emerson");

        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese una descripción de factura ");
        String desc = s.nextLine();
        Factura factura = new Factura(desc, cliente);

        Producto producto;

        String nombre;
        float precio;
        int cantidad;

        System.out.println();

        for(int i = 0; i<5; i++){
            producto = new Producto();
            System.out.print("Ingrese producto n° " + producto.getCodigo() + ": ");
            nombre = s.next();

            producto.setNombre(nombre);

            System.out.print("Ingrese el precio: ");
            precio = s.nextFloat();
            producto.setPrecio(precio);

            System.out.print("Ingrese la cantidad: ");
            cantidad = s.nextInt();

            DetalleFactura detalle = new DetalleFactura(cantidad, producto);

            factura.addDetalleFactura(detalle);

            System.out.println();
        }
        System.out.println(factura.generarDetalle());

    }
}
