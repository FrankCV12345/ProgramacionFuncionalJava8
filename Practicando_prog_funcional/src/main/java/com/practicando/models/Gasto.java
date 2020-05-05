package com.practicando.models;

public class Gasto {
    private Double cantidad;
    private Double precio;
    private  String producto;
    public Gasto(){
    }
    public Gasto(Double cantidad, Double precio, String producto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Gasto){
            Gasto gasto = (Gasto) obj;
            if(gasto.producto.equals(this.producto) && gasto.precio.equals(this.precio) && gasto.cantidad.equals(this.cantidad)){
                return  true;
            }
            else{
                return  false;
            }
        }else {
            return false;
        }
    }
}

