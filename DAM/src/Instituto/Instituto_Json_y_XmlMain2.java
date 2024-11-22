package Instituto;


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

public class Instituto_Json_y_XmlMain2 {

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

            int contProfMatematicas = 0;
            int contProfIdiomas = 0;
            int menosEdad = 10000;
            String profesorMenosEdad = "";
            int maxEstudiantes = 0;
            String cursoConMasEstudiantes = "";

            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File("profesores.xml"));

            // Cojo todas las etiquetas profesor del documento
            NodeList listaProfesores = documento.getElementsByTagName("profesor");
            NodeList listaCurso = documento.getElementsByTagName("curso");
            System.out.println("Cantidad de profesores: " + listaProfesores.getLength());

            // Profesores de matemáticas
            for (int i = 0; i < listaProfesores.getLength(); i++) {
                Element profesor = (Element) listaProfesores.item(i); // Obtiene el elemento "profesor" actual en la iteración.
                NodeList listaNombre = profesor.getElementsByTagName("nombre");
                NodeList listaEdad = profesor.getElementsByTagName("edad");
                NodeList listaMateria = profesor.getElementsByTagName("materia");

                //1-¿Cuántos profesores hay en el departamento de "Matemáticas"?
                if (listaMateria.item(0).getTextContent().equalsIgnoreCase("Matemáticas")) {
                    contProfMatematicas++;

                }
                //¿Cuántos profesores enseñan "Idiomas"?
                if (listaMateria.item(0).getTextContent().equalsIgnoreCase("Idiomas")) {
                    contProfIdiomas++;

                }
                //3-¿Cuál es el profesor más joven de cada departamento?
                int edad = parseInt(listaEdad.item(0).getTextContent());
                if (edad < menosEdad) {

                    menosEdad = edad;

                    profesorMenosEdad = listaNombre.item(0).getTextContent();

                }

                //System.out.println(listaCurso.item(0).getTextContent());
            }

            for (int i = 0; i < listaCurso.getLength(); i++) {
                Element curso = (Element) listaCurso.item(i); // Obtiene el elemento "listaCurso" actual en la iteración.
                NodeList listaEstudiantes = curso.getElementsByTagName("estudiante");

                maxEstudiantes = listaEstudiantes.getLength();

                if (listaEstudiantes.getLength() > maxEstudiantes) {

                    cursoConMasEstudiantes = curso.getElementsByTagName("nombre").item(0).getTextContent(); // Obtiene el nombre del curso.
                }

            }

            System.out.println(contProfMatematicas > 1 ? "Hay " + contProfMatematicas + " profesores de matemáticas." : "Hay " + contProfMatematicas + " profesor de matemáticas.");
            System.out.println(contProfMatematicas > 1 ? "Hay " + contProfIdiomas + " profesores de Idiomas." : "Hay " + contProfIdiomas + " profesor de Idiomas.");
            System.out.println("Profesor con menos edad: " + profesorMenosEdad + " " + menosEdad);
            System.out.println("Curso con mayor estudiantes: " + cursoConMasEstudiantes + " " + maxEstudiantes);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
