package jesus.lab.arboles;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jesus Vargas
 */
public class Categoria {

    public String nombre;
    public int nivel;
    public Categoria padre;
    public List<Categoria> subcategorias;
    public ArbolAVL arbolProductos;

    /**
     * Metodo Constructor
     */
    public Categoria(String nombre, int nivel, Categoria padre, List<Categoria> subcategorias, ArbolAVL arbolProductos) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.padre = padre;
        this.subcategorias = new ArrayList<>();
        this.arbolProductos = new ArbolAVL();
    }

    /**
     * METODOS
     */
    public boolean agregarSubcategoria(Categoria subcategoria) {
        if (nivel <= 4) {
            this.subcategorias.add(subcategoria);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarSubcategoria(String nombre) {
        for (Categoria cat : subcategorias) {
            if (cat.nombre.equals(nombre)) {
                this.subcategorias.remove(cat);
                return true;
            }
        }
        return false;
    }

    public Categoria buscarSubcategoria(String nombre) {
        for (Categoria cat : subcategorias) {
            if (cat.nombre.equals(nombre)) {
                return cat;
            }
        }
        return null;
    }

    public List<Categoria> obtenerTodasSubcategorias() {
        List<Categoria> res = new ArrayList<>();
        obtenerRecursivo(this, res);
        return res;
    }

    public void obtenerRecursivo(Categoria cat, List<Categoria> acum) {
        if (cat.subcategorias == null) {
            return;
        }

        for (Categoria subcat : cat.subcategorias) {
            acum.add(subcat);
            obtenerRecursivo(subcat, acum);
        }
    }

}
