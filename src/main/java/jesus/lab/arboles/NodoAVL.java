package jesus.lab.arboles;

/**
 *
 * @author Jesus Vargas
 */
public class NodoAVL {

    public Producto producto;
    public int altura;
    public NodoAVL izq, der;

    NodoAVL(Producto producto) {
        this.producto = producto;
        this.altura = 1;
    }

}
