
package pkg1ieinterfazgrafica;


public interface Evaluable {
    String getCondicion();
    double getPromedio();
    boolean estaAprobada();
    
    default void mostrarEstadoAcademico() {
        System.out.println("Condición: " + getCondicion());
        System.out.println("Promedio: " + getPromedio());
    }
}
