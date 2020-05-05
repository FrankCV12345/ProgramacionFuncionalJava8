package com.practicando.models;

import java.util.Collection;

public class CarritoCompra {

    private Collection<Integer> precios;

    public CarritoCompra(Collection<Integer> precios) {

        this.precios = precios;
    }

    public int calcularPrecioTotal() {

        int precioTotal = 0;

        for(Integer precio : precios){

            precioTotal += precio;

        }
        return precioTotal;
    }

    public int contarNumeroProductos() {

        return precios.size();
    }
}
