package com.practicando.models;

import java.util.ArrayList;
import java.util.function.Function;

public class CarritoBuilder {


    ArrayList<Integer> precios = new ArrayList<Integer>();

    public CarritoBuilder(int size){
        for(int i = 0; i < size; i++){
            Double random = Math.random()*100+1;
            precios.add(random.intValue());
        }
    }

    public CarritoBuilder(int size, int value){
        for(int i = 0; i < size; i++){
            precios.add(value);
        }
    }

    public CarritoCompra build(){
        return new CarritoCompra(this.precios);
    }

    public CarritoBuilder add(Integer nuevoValor){
        precios.add(nuevoValor);
        return this;
    }

    public int calculaPrecioTotalLambda(){
        //se combierte el array a stream
        //se s toma la parete enrea de cada item
        // suma todos los items
        return this.precios.stream().mapToInt(Integer::intValue).sum();
    }

    public long calculaDescuentoConLambda(int cantidadDescuento){
        Long descuentoTotal =0L;
        Long numerodescuento = this.precios.stream().filter(precio -> precio >= cantidadDescuento).count();
        descuentoTotal = (cantidadDescuento *5/100)*numerodescuento;
        return descuentoTotal;
    }

    public CarritoBuilder addMultiple(int size, int value){
        for(int i = 0; i < size; i++){
            precios.add(value);
        }
        return this;
    }

    public boolean detectarErrorAnyMactch(){

        return this.precios.stream().anyMatch(precio -> precio.intValue() < 0);
    }
    public boolean dectectaerrorFindAny(){
        return  this.precios.stream().filter(precio -> precio.intValue() < 0).findAny().isPresent();
    }
    public boolean dectectarErrorFindFirst(){
        return this.precios.stream().filter( precio -> precio.intValue() < 0).findFirst().isPresent();
    }
}
