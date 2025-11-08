package jesus.lab.arboles;

/**
 *
 * @author Jesus Vargas
 */
public class ArbolAVL {

    public NodoAVL head;

    // METODO CONSTRUCTOR
    public ArbolAVL() {
        head = null;
    }

    public int altura(NodoAVL n) {
        if (n == null) {
            return 0;
        }
        return n.altura;
    }

    /**
     * Actualiza la altura de un nodo basándose en la altura de sus hijos.
     */
    public void actualizarAltura(NodoAVL n) {
        if (n != null) {
            n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
        }
    }

    /**
     * Calcula el "Factor de Equilibrio"
     */
    public int getFactorEquilibrio(NodoAVL n) {
        if (n == null) {
            return 0;
        }
        return altura(n.izq) - altura(n.der);
    }

    /**
     * Rotacion DERECHA
     */
    public NodoAVL rotacionDerecha(NodoAVL actual) {
        NodoAVL aux = actual.izq;
        NodoAVL T2 = aux.der;

        // Realizar la rotación
        aux.der = actual;
        actual.izq = T2;

        // Actualizar alturas
        actualizarAltura(actual);
        actualizarAltura(aux);

        // Devolver la bueva raíz del subárbol
        return aux;
    }

    /**
     * Rotacion IZQUIERDA
     */
    public NodoAVL rotacionIzquierda(NodoAVL actual) {
        NodoAVL aux = actual.der;
        NodoAVL T2 = aux.izq;

        // Realizar la rotación
        aux.izq = actual;
        actual.der = T2;

        // Actualizar alturas
        actualizarAltura(actual);
        actualizarAltura(aux);

        // Devolver la bueva raíz del subárbol
        return aux;
    }

    /**
     * Insertar Recursivo
     */
    public NodoAVL insertarRecursivo(NodoAVL nodo, Producto producto) {
        if (nodo == null) {
            return (new NodoAVL(producto));
        }

        if (producto.nombre.compareTo(nodo.producto.nombre) <= 0) {
            nodo.izq = insertarRecursivo(nodo.izq, producto);
        } else if (producto.nombre.compareTo(nodo.producto.nombre) > 0) {
            nodo.der = insertarRecursivo(nodo.der, producto);
        } else {
            return nodo;
        }

        actualizarAltura(nodo);

        int factorEquilibrio = getFactorEquilibrio(nodo);

        if (factorEquilibrio > 1 && producto.nombre.compareTo(nodo.izq.producto.nombre) <= 0) {
            return rotacionDerecha(nodo);
        }

        if (factorEquilibrio < -1 && producto.nombre.compareTo(nodo.der.producto.nombre) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (factorEquilibrio > 1 && producto.nombre.compareTo(nodo.izq.producto.nombre) > 0) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }

        if (factorEquilibrio < -1 && producto.nombre.compareTo(nodo.der.producto.nombre) < 0) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    public void insertar(Producto producto) {
        head = insertarRecursivo(head, producto);
    }

    public void imprimir() {
        imprimirInOrden(head);
        System.out.println("===============");
        imprimirPreOrden(head);
        System.out.println("===============");
        imprimirPostOrden(head);
    }

    private void imprimirInOrden(NodoAVL n) {
        if (n == null) {
            return;
        }
        imprimirInOrden(n.izq);
        System.out.println(n.producto.nombre);
        System.out.println(n.producto.descripcion);
        imprimirInOrden(n.der);
    }

    public void imprimirPreOrden(NodoAVL n) {
        if (n == null) {
            return;
        }
        System.out.println(n.producto.nombre);
        System.out.println(n.producto.descripcion);
        imprimirPreOrden(n.izq);
        imprimirPreOrden(n.der);
    }

    public void imprimirPostOrden(NodoAVL n) {
        if (n == null) {
            return;
        }

        imprimirPostOrden(n.izq);
        imprimirPostOrden(n.der);
        System.out.println(n.producto.nombre);
        System.out.println(n.producto.descripcion);
    }

}
