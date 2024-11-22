
import Instituto.Curso;
import Instituto.Instituto;
import Instituto.JsonUtil;
import Instituto.Profesor;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Json_y_XmlMain2 {

    public static void main(String[] args) {
         try {

            System.out.println("\n----------------------\nCONSULTAS DE JSON\n----------------------");
            // Definir el tipo para el objeto Instituto
            Type institutoType = new TypeToken<Instituto>() {
            }.getType();

            // Leer y deserializar el JSON
            Instituto instituto = JsonUtil.leerJson("profesores.json", institutoType);

            //1 - ¿Cuál es el promedio de edad de los profesores del departamento de "Química"?
            long totalQuimica = instituto.getProfesores().stream()
                    .filter(profesor -> "Química".equalsIgnoreCase(profesor.getMateria()))
                    .count();
            System.out.println("Total de profesores de Química: " + totalQuimica);

            List<String> nombProfMatem = instituto.getProfesores().stream()
                    .filter(profesor -> "Química".equalsIgnoreCase(profesor.getMateria()))
                    .map(Profesor::getNombre)
                    .collect(Collectors.toList());
            System.out.println("Profesores de matématicas");
            nombProfMatem.forEach(System.out::println);

            //2 -¿Cuántos profesores enseñan "Idiomas"?
            long totalIdiomas = instituto.getProfesores().stream()
                    .filter(profesor -> "idiomas".equalsIgnoreCase(profesor.getMateria()))
                    .count();
            System.out.println("Total de profesores de Idiomas: " + totalIdiomas);

            List<String> nomProfIdiom = instituto.getProfesores().stream()
                    .filter(profesor -> "Idiomas".equalsIgnoreCase(profesor.getMateria()))
                    .map(Profesor::getNombre)
                    .collect(Collectors.toList());

            System.out.println("Los profesores de Química son ");
            nomProfIdiom.forEach(System.out::println);

            // 3-¿Cuál es el profesor más joven de cada departamento?
            Map<String, Optional<Profesor>> profesorMasJovenPorDepartamento = instituto.getProfesores().stream() // Ahora llamamos a getProfesores()
                    .collect(Collectors.groupingBy(
                            Profesor::getMateria,
                            Collectors.minBy(Comparator.comparingInt(Profesor::getEdad))
                    ));

            profesorMasJovenPorDepartamento.forEach((materia, profesor)
                    -> System.out.println("Profesor más joven en " + materia + ": " + profesor.orElse(null)));

            //        ¿Cuál es el curso con más estudiantes?
            Curso cursoMasEstudiantes = instituto.getProfesores().stream()
                    .flatMap(profesor -> profesor.getCursos().stream())
                    .max(Comparator.comparingInt(curso -> curso.getEstudiantes().size()))
                    .orElse(null);

            System.out.println(cursoMasEstudiantes != null ? "Cursos con más estudiantes: " + cursoMasEstudiantes.getNombre() : " no hay cursos ");

            //¿Cuántos estudiantes hay en el curso  "Inglés Avanzado"?
            long cantInglesAvanzado = instituto.getProfesores().stream()
                    .flatMap(profesor -> profesor.getCursos().stream())
                    .filter(cursos -> "Inglés Avanzado".equalsIgnoreCase(cursos.getNombre()))
                    .flatMap(curso -> curso.getEstudiantes().stream())
                    .count();

            System.out.println("Total de estudiantes en Inglés Avanzado: " + cantInglesAvanzado);

        } catch (Exception e) {

        }
         

        System.out.println("\n------------------------\nCONSULTAS DE XML\n------------------------");

        try {
            int matematicasProfesorCount = 0;
            int idiomasProfesorCount = 0;
            int edadMinima = 1000;
            String profesorMasJoven = "";
            int maxEstudiantesPorCurso = 0;
            String cursoConMayorNumeroDeEstudiantes = "";
            int estudiantesInglesAvanzadoCount = 0;

            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Crear un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtener el documento a partir del archivo XML
            Document documento = builder.parse(new File("profesores.xml"));

            // Obtener todas las etiquetas <profesor> del documento
            NodeList listaProfesores = documento.getElementsByTagName("profesor");
            NodeList listaCursos = documento.getElementsByTagName("curso"); // Obtener todos los cursos

            System.out.println("Cantidad de profesores: " + listaProfesores.getLength());

            // Contar profesores de Matemáticas e Idiomas, y encontrar el más joven
            for (int i = 0; i < listaProfesores.getLength(); i++) {
                Element profesor = (Element) listaProfesores.item(i);
                NodeList profesorNombreLista = profesor.getElementsByTagName("nombre");
                NodeList profesorEdadLista = profesor.getElementsByTagName("edad");
                NodeList profesorMateriaLista = profesor.getElementsByTagName("materia");

                // 1- ¿Cuántos profesores hay en el departamento de "Matemáticas"?
                if (profesorMateriaLista.item(0).getTextContent().equalsIgnoreCase("Matemáticas")) {
                    matematicasProfesorCount++;
                }

                // 2- ¿Cuántos profesores enseñan "Idiomas"?
                if (profesorMateriaLista.item(0).getTextContent().equalsIgnoreCase("Idiomas")) {
                    idiomasProfesorCount++;
                }

                // 3- ¿Cuál es el profesor más joven de cada departamento?
                int profesorEdad = parseInt(profesorEdadLista.item(0).getTextContent());
                if (profesorEdad < edadMinima) {
                    edadMinima = profesorEdad;
                    profesorMasJoven = profesorNombreLista.item(0).getTextContent();
                }
            }

            // 4- ¿Cuál es el curso con más estudiantes?
            for (int i = 0; i < listaCursos.getLength(); i++) {
                Element curso = (Element) listaCursos.item(i);
                NodeList listaEstudiantesCurso = curso.getElementsByTagName("estudiante");
                String nombreCurso = curso.getAttribute("nombre");

                int numeroEstudiantes = listaEstudiantesCurso.getLength();

                // Actualizar el curso con más estudiantes
                if (numeroEstudiantes > maxEstudiantesPorCurso) {
                    maxEstudiantesPorCurso = numeroEstudiantes;
                    cursoConMayorNumeroDeEstudiantes = nombreCurso;
                }

                // 5- ¿Cuántos estudiantes hay en el curso "Inglés Avanzado"?
                if (nombreCurso.equalsIgnoreCase("Inglés Avanzado")) {
                    estudiantesInglesAvanzadoCount = numeroEstudiantes;
                }
            }

            // Resultados
            System.out.println(matematicasProfesorCount > 1 ? "Hay " + matematicasProfesorCount + " profesores de Matemáticas." : "Hay " + matematicasProfesorCount + " profesor de Matemáticas.");
            System.out.println(idiomasProfesorCount > 1 ? "Hay " + idiomasProfesorCount + " profesores de Idiomas." : "Hay " + idiomasProfesorCount + " profesor de Idiomas.");
            System.out.println("Profesor con menos edad: " + profesorMasJoven + " con " + edadMinima + " años.");
            System.out.println(maxEstudiantesPorCurso > 0 ? "Curso con mayor número de estudiantes: " + cursoConMayorNumeroDeEstudiantes + " con " + maxEstudiantesPorCurso + " estudiantes." : "No hay cursos con estudiantes.");
            System.out.println(estudiantesInglesAvanzadoCount > 0 ? "El curso de Inglés Avanzado tiene " + estudiantesInglesAvanzadoCount + " estudiantes." : "No hay estudiantes en Inglés Avanzado.");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
