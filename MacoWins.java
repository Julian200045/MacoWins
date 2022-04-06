class Dia{
    var fecha;
    var ventasDelDia = [];

    method añadirVenta(venta){
        ventasDelDia.add(venta);
    }

    method ganancias(){
        return ventasDelDia.sum{venta => venta.precioDeVenta()}
    }
}

class Venta{
    var tipoDePago;
    var fechaDeVenta;
    var prendas = [];

    method precioTotalDePrendas(){
        return prendas.sum{prenda => prenda.precioDePrenda()};
    }

    method precioDeVenta(){
        return tipoDePago.montoAPagar(self);
    }
}

//Decidí crear una clase adicional para manejar la ejecución del pago (y no como un método de ventas) para tener una mayor escalabilidad
//en el caso de que se agreguen más tipos de pago y para no sobrecargar la clase de Venta, pero mas importante, me resultó mas simple.

class Pago{
    method montoAPagar(venta){
        return venta.precioTotalDePrendas();
    }
}

class PagoConTarjeta extends Pago{
    var cuotas;
    var coeficienteDeRecargo;

    override method montoAPagar(venta){
        return super() + (cuotas * coeficienteDeRecargo + 0.01 * venta.precioTotalDePrendas());
    }
}

class Prenda{
    var estado = new Nueva();
    var precioPropio;
    var tipo;

    method tipo(){
        return tipo;
    }

    method precioPropio(){
        return precioPropio;
    }

    method precioDePrenda(){
        return estado.modificarPrecio(self);
    }
}


class Nueva{
    method modificarPrecio(prenda){
        return prenda.precioPropio();
    }
}

class Promocion{
    var descuento;
    method modificarPrecio(prenda){
        return prenda.precioPropio() - descuento;
    }
}

class Liquidacion{
    method modificarPrecio(prenda){
        return prenda.precioPropio() * 0.5;
    }
}