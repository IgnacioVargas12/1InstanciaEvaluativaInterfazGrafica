package pkg1ieinterfazgrafica;

import java.util.ArrayList;

class Estudiante extends PersonaAcademica implements Consultable {

    private String carrera;
    private int anioIngreso;
    private ArrayList<InscripcionMateria> materias;
    

    public Estudiante(String nombre, String legajo, String carrera, int anioIngreso) {
        super(nombre, legajo);
        setCarrera(carrera);
        setAnioIngreso(anioIngreso);
        this.materias = new ArrayList<>();

    }

    @Override
    public void mostrarResumen() {
        System.out.println("\n=== Resumen de Estudiante ===");
        System.out.println("Nombre:   " + GetNombre());
        System.out.println("Legajo:   " + getLegajo());
        System.out.println("Carrera:  " + carrera);
        System.out.println("Ingreso:  " + anioIngreso);
        System.out.println("Materias: " + materias.size());
    }
    
    public void inscribirse(Materia nuevaMateria) 
    {
        if (nuevaMateria == null) return;

        for (InscripcionMateria ins : materias) 
            {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(nuevaMateria.getCodigo())) 
            {
                System.out.println("Error: Ya estas inscripto en " + nuevaMateria.getNombre());
                return; 
            }
        }
        InscripcionMateria nuevaInscripcion = new InscripcionMateria(nuevaMateria);
        this.materias.add(nuevaInscripcion);

        System.out.println("Inscripcion exitosa a: " + nuevaMateria.getNombre());
    }
    
    

   public boolean darDeBaja(String codigoMateria) {
        for (int i = 0; i < materias.size(); i++) {
            InscripcionMateria ins = materias.get(i);

            if (ins.getMateria().getCodigo().equalsIgnoreCase(codigoMateria)) {
                materias.remove(i);
                System.out.println("Sistema: Se ha dado de baja la materia " + codigoMateria);
                return true; 
            }
        }
        System.out.println("Error: El alumno no esta inscripto en la materia " + codigoMateria);
        return false;
    }

    public InscripcionMateria getInscripcion(String codigoMateria) {
        if (codigoMateria == null || codigoMateria.isBlank()) {
            return null;
        }
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(codigoMateria)) {
                return ins; 
            }
        }
        return null;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        if (carrera.isEmpty()) {
            System.out.println("Error: la carrera no puede estar vacia.");
        } else {
            this.carrera = carrera;
        }
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        if (anioIngreso < 1900 || anioIngreso > 2100) {
            System.out.println("Error: el año de ingreso no es valido.");
        } else {
            this.anioIngreso = anioIngreso;
        }
    }
    
    public double getPromedioGeneral()
    {
        if (materias == null|| materias.isEmpty ())
        {
            return 0;
        }
        double sumaDePromedios = 0;
        int materiasConNotas = 0;
        for (InscripcionMateria ins: materias) 
        {
            double promedioMateria = ins.getPromedio();
            
            if (promedioMateria > 0)
            {
                sumaDePromedios += promedioMateria;
                materiasConNotas++;
                
                
            }
        }
        if (materiasConNotas == 0) {return 0;}          
            return sumaDePromedios / materiasConNotas;
       
    }
    
    public ArrayList<InscripcionMateria> getMateriasCriticas() {
        ArrayList<InscripcionMateria> criticas = new ArrayList<>();
        for (InscripcionMateria ins : materias) {
            double porcentaje = ins.getPorcentajeAsistencia();
            if (porcentaje >= 75 && porcentaje <= 85) {
                criticas.add(ins);
            }
        }
        return criticas;
    }
    
    public ArrayList<InscripcionMateria> getRankingMaterias() {
        ArrayList<InscripcionMateria> listaOrdenada = new ArrayList<>(this.materias);
        listaOrdenada.sort((m1, m2) -> Double.compare(m2.getPuntajeRanking(), m1.getPuntajeRanking()));
        return listaOrdenada;
    }
    
    public ArrayList<InscripcionMateria> getMaterias(){
        return this.materias;
    }
    
    public InscripcionMateria buscarPorCodigo(String codigo) {
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(codigo)) {
                return ins; 
            }
        }
        return null; 
    }

    public InscripcionMateria buscarPorNombre(String nombre) {
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getNombre().equalsIgnoreCase(nombre)) {
                return ins; 
            }
        }
        return null;
    }
    
    public ArrayList<InscripcionMateria> getMateriasEnRiesgo() {
        ArrayList<InscripcionMateria> enRiesgo = new ArrayList<>();

        for (InscripcionMateria ins : materias) {
            double asistencia = ins.getPorcentajeAsistencia();
            if (asistencia >= 75 && asistencia <= 85) {
                enRiesgo.add(ins);
            }
        }
        enRiesgo.sort((m1, m2) -> Double.compare(m1.getPorcentajeAsistencia(), m2.getPorcentajeAsistencia()));
        return enRiesgo;
    }
    
}
