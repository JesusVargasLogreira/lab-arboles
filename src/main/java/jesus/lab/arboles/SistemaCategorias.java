package jesus.lab.arboles;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jesus Vargas
 */
public class SistemaCategorias {

    public Categoria raiz;
    public int maxNiveles = 4;

    /**
     * METODOS
     */
    /**
     * AGREGAR CATEGORIA
     */
    public boolean agregarCategoria(String nombreCategoria) {
        String[] partes = nombreCategoria.split("/");
        if (raiz == null) {
            raiz = new Categoria(partes[0], 1, null, new ArrayList<>(), new ArbolAVL());
        }

        Categoria actual = raiz;
        for (int i = 1; i < partes.length; i++) {
            Categoria sub = actual.buscarSubcategoria(partes[i]);
            if (sub == null) {
                sub = new Categoria(partes[i], actual.nivel + 1, actual, new ArrayList<>(), new ArbolAVL());
                actual.agregarSubcategoria(sub);
            }
            actual = sub;
        }
        return true;
    }

    public boolean agregarCategoriaRecursivo(Categoria act, String nombreCategoria) {
        if (act.nivel < maxNiveles) {
            Categoria nuevaCategoria = new Categoria(nombreCategoria, act.nivel + 1, act, new ArrayList<>(), new ArbolAVL());
            act.agregarSubcategoria(nuevaCategoria);
            return true;
        }

        for (Categoria subcat : act.subcategorias) {
            if (agregarCategoriaRecursivo(subcat, nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINAR CATEGORIA
     */
    public boolean eliminarCategoria(String nombreCategoria) {
        if (raiz == null) {
            return false;
        }

        return eliminarCategoriaRecursivo(raiz, nombreCategoria);
    }

    public boolean eliminarCategoriaRecursivo(Categoria actual, String nombreCategoria) {
        for (Categoria subcat : actual.subcategorias) {
            if (subcat.nombre.equals(nombreCategoria)) {
                actual.subcategorias.remove(subcat);
                return true;
            }
        }

        for (Categoria subcat : actual.subcategorias) {
            if (eliminarCategoriaRecursivo(subcat, nombreCategoria)) {
                return true;
            }
        }

        return false;
    }

    /**
     * BUSCAR CATEGORIA
     */
    public Categoria buscarCategoria(String nombreCategoria) {
        if (raiz.nombre.equals(nombreCategoria)) {
            return raiz;
        }

        return buscarCategoriaRecursivo(raiz, nombreCategoria);
    }

    public Categoria buscarCategoriaRecursivo(Categoria actual, String nombreCategoria) {
        for (Categoria subcat : actual.subcategorias) {
            if (subcat.nombre.equals(nombreCategoria)) {
                return subcat;
            }
            Categoria encontrada = buscarCategoriaRecursivo(subcat, nombreCategoria);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }

    /**
     * AGREGAR PRODUCTO
     */
    public boolean agregarProducto(String nombreCategoria, Producto producto) {
        if (raiz == null) {
            return false;
        }

        Categoria cat = buscarCategoria(nombreCategoria);
        if (cat == null) {
            return false;
        }

        cat.arbolProductos.insertar(producto);
        return true;
    }

    /**
     * OBTNERER PRODUCTOS POR CATEGORIA
     */
    public ArrayList<Object> obtenerProductosPorCategoria(String nombreCategoria) {
        ArrayList<Object> productos = new ArrayList<>();

        if (raiz == null) {
            return productos;
        }

        Categoria cat = buscarCategoria(nombreCategoria);
        if (cat == null) {
            return productos;
        }

        obtenerProductosInOrden(cat.arbolProductos.head, productos);
        return productos;
    }

    public void obtenerProductosInOrden(NodoAVL nodo, ArrayList<Object> lista) {
        if (nodo == null) {
            return;
        }

        obtenerProductosInOrden(nodo.izq, lista);
        lista.add(nodo.producto);
        obtenerProductosInOrden(nodo.der, lista);
    }

}
