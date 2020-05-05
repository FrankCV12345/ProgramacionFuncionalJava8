
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.practicando.models.Gasto;

public class App {
    static List<Gasto> gastosAceptados= null;
    static List<Gasto> gastosDisitnc = new ArrayList<Gasto>();

    public static  void main(String[] args){

        List<Gasto> gastos  = Arrays.asList(
                new Gasto(3.3,5.8,"leche"),
                new Gasto(4.7,8.9,"arroz"),
                new Gasto(1.2,6.8,"aceite"),
                new Gasto(3.9,6.7,"azucar"),
                new Gasto(3.6,22.2,"Queso"),
                new Gasto(3.7,12.9,"pan del monte"),
                new Gasto(1.2,6.8,"aceite"),
                new Gasto(2.0,13.9,"pollo fresco"),
                new Gasto(3.6,22.2,"Queso")
                );

        Function<Double,Double> calculaimporte = precio -> precio * 1.18;
        Predicate<Gasto>  gastoEsAceptable = (gasto) -> gasto.getPrecio() <= 30;


        Function<Gasto,Gasto> addIGV = gasto -> new Gasto(gasto.getCantidad(),calculaimporte.apply(gasto.getPrecio()),gasto.getProducto());
        Function<List<Gasto>,Gasto> getMayorGasto = (gastitos) ->gastitos.stream().reduce(gastitos.get(0),(g1,g2) -> g1.getPrecio() >g2.getPrecio() ? g1 : g2 );

        gastosAceptados = gastos.stream()
                .map(addIGV)
                .filter(gastoEsAceptable)
                .collect(Collectors.toList());

        System.out.println("GASTOS ACEPTADOS : "+gastosAceptados.size());
        gastosAceptados.forEach(gasto -> System.out.println("   - Producto :"+gasto.getProducto()+" Precio :"+gasto.getPrecio()));

        Gasto gastoMayor = gastosAceptados.stream().findAny().map(g -> getMayorGasto.apply(gastosAceptados)).orElse(new Gasto(0.0,0.0,""));

        System.out.println("El mayor gasto es de  : "+ gastoMayor.getPrecio() + " por el producto :"+gastoMayor.getProducto());

        Double sumaDegastos = gastosAceptados.stream().mapToDouble( gasto -> gasto.getPrecio()).sum();
        System.out.println("El total de gasto es :"+sumaDegastos);

        Predicate<Gasto> productContainsE = gasto -> gasto.getProducto().contains("e");

        Predicate<Gasto> distinct = gasto -> gastosDisitnc.stream()
                                                .filter(g -> g.equals(gasto))
                                                .findAny()
                                                .map( g -> false)
                                                .orElseGet(() ->{
                                                    gastosDisitnc.add(gasto);
                                                    return true;
                                                });
        gastosAceptados.stream()
                .filter(productContainsE)
                .filter(distinct)
                .collect(Collectors.toList())
                .forEach( gasto -> System.out.println("Que contienen e : "+gasto.getProducto()));

                //LIMITANDO LOS STREAMS
                /*
                        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n >= 4;
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n;
                        })
                        .limit(2)
                        .collect(Collectors.toList());

                 */
        //NUMERO MAYOR
        //Integer max = Arrays.asList(1,5,5,3,9,1000,32,4332,15,8,0,3,2).stream().reduce(1,Integer::max);
        //System.out.println(max);

        //FUNCIONAL INTERFACES
       /* Function<String,String> converterString = (letra) -> letra.concat(" concatenado") ;

        System.out.println(converterString.apply("letrita"));


        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX   = (text) -> text.endsWith("x");

        Predicate<String> startsWithAAndEndsWithX = (text) -> startsWithA.test(text) && endsWithX.test(text);

        String  input  = "A hardworking person must relax";
        boolean result = startsWithAAndEndsWithX.test(input);

        Predicate<String> composed =startsWithA.and(endsWithX);
        composed.test(input);

        System.out.println(result);
        */
    }

}

