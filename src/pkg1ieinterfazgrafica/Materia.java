/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1ieinterfazgrafica;
import java.util.HashSet;

/**
 *
 * @author conra
 */
public class Materia implements Consultable{

    private String nombre;
    private String codigo;
    private int cuatrimestre;
    private int anio;
    
    private static HashSet<String> codigosRegistrados = new HashSet<>();

    public Materia(String nombre, String codigo, int cuatrimestre, int anio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cuatrimestre = cuatrimestre;
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
        } else {
            this.nombre = nombre;
        }
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        if (codigosRegistrados.contains(codigo)) {
            throw new IllegalArgumentException(
                "Ya existe una materia con ese código"
            );
        }
        codigosRegistrados.add(codigo);
        this.codigo = codigo;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }
    public void setCuatrimestre(int cuatrimestre) {
        if(cuatrimestre == 1 || cuatrimestre == 2) {
            this.cuatrimestre = cuatrimestre;
        } else {
            System.out.println("El cuatrimestre debe ser 1 o 2");
        }
    }

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
    if (anio != 0) {
        this.anio = anio;
    } else {
        System.out.println("El año no puede estar vacío");
    }
}

@Override
public void mostrarResumen() {
    System.out.println("===== MATERIA =====");
    System.out.println("Nombre: " + nombre);
    System.out.println("Código: " + codigo);
    System.out.println("Cuatrimestre: " + cuatrimestre);
    System.out.println("Año: " + anio);
}
}