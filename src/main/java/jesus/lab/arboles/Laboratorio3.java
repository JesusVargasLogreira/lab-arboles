package jesus.lab.arboles;

import java.util.ArrayList;

/**
 *
 * @author Jesus Vargas
 */
public class Laboratorio3 {

    public static void main(String[] args) {
        System.out.println("PRUEBA RÁPIDA SISTEMA CATEGORÍAS\n");

        SistemaCategorias sistema = new SistemaCategorias();

        // Probar todos los métodos en secuencia
        sistema.agregarCategoria("Electrónicos");
        sistema.agregarCategoria("Electrónicos/Computadoras");

        sistema.buscarCategoria("Computadoras");

        sistema.agregarProducto("Computadoras", new Producto("Laptop", "Desc", 999.99));

        ArrayList<Object> productos = sistema.obtenerProductosPorCategoria("Computadoras");

        sistema.eliminarCategoria("Computadoras");

        System.out.println("✅ Todos los métodos ejecutados correctamente");
        System.out.println("Productos obrenidos: " + productos.size());
    }
}
