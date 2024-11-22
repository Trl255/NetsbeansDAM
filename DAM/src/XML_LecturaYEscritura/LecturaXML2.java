package XML_LecturaYEscritura;


import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class LecturaXML2 {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento ra�z del documento
            Element eRaiz = doc.createElement("cine");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendr� los elementos
            Element cine = doc.createElement("peliculas");
            eRaiz.appendChild(cine);

            String[] peliculas = new String[]{"pelicula1", "pelicula2", "pelicula3", "pelicula4"};
            String[] nombres = new String[]{"Super 8", "Los Pitufos", "El rey Leon", "La Cenicienta"};
            String[] directores = new String[]{"Director1", "Director3", "Director4", " "};
            String[] valoracionPuntos = new String[]{"4", "3", "4", ""};
            String[] publicos = new String[]{"Adultos", "Todos los públicos", "Infantil", "Infantil"};

            for (int i = 0; i < peliculas.length; i++) {

                // definimos el nodo que contendr� los elementos
                Element ePelicula = doc.createElement("pelicula");
                cine.appendChild(ePelicula);

                // atributo para el nodo pelicula
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(nombres[i]));
                ePelicula.appendChild(nombre);

                // definimos cada uno de los elementos y le asignamos un valor
                Element eDirector = doc.createElement("director");

                eDirector.appendChild(doc.createTextNode(directores[i]));
                ePelicula.appendChild(eDirector);

                Element ePuntos = doc.createElement("puntos");
                ePuntos.appendChild(doc.createTextNode(valoracionPuntos[i]));
                ePelicula.appendChild(ePuntos);

                Element ePublicos = doc.createElement("publico");
                ePublicos.appendChild(doc.createTextNode(publicos[i]));
                ePelicula.appendChild(ePublicos);

            }

            // clases necesarias finalizar la creaci�n del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("cines.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mostrarInformacion();

    }

    public static void mostrarInformacion() {

        boolean valoracionCincoPuntos = false;
        String puntosV = "5";

        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File("cines.xml"));

            // Cojo todas las etiquetas pelicula del documento
            NodeList listaPeliculas = documento.getElementsByTagName("pelicula");
            System.out.println("Cantidad de peliculas: " + listaPeliculas.getLength());

            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodo = listaPeliculas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;

                    // Verificar y acceder a los elementos hijos
                    Node nombreNode = e.getElementsByTagName("nombre").item(0);
                    Node directorNode = e.getElementsByTagName("director").item(0);
                    Node puntosNode = e.getElementsByTagName("puntos").item(0);
                    Node publicoNode = e.getElementsByTagName("publico").item(0);

                    String nombre = nombreNode != null ? nombreNode.getTextContent() : "N/A";
                    String director = directorNode != null ? directorNode.getTextContent() : "N/A";
                    String puntos = puntosNode != null ? puntosNode.getTextContent() : "N/A";
                    String publico = publicoNode != null ? publicoNode.getTextContent() : "N/A";

                    if (nombre != null) {
                        long contPalabraA = nombre.toUpperCase().chars().filter(ch -> ch == 'A').count();
                        long contPalabraE = nombre.toUpperCase().chars().filter(ch -> ch == 'E').count();
                        long contPalabraS = nombre.toUpperCase().chars().filter(ch -> ch == 'S').count();

                        if (contPalabraA > 2) {
                            System.out.println("La pelicula: " + nombre + " tiene " + contPalabraA + " A\n---------");
                        }
                        if (contPalabraE > 2) {

                            System.out.println("La pelicula: " + nombre + " tiene " + contPalabraE + " E\n---------");
                        }

                        if (contPalabraS > 2) {

                            System.out.println("La pelicula: " + nombre + " tiene " + contPalabraS + " S\n---------");

                        }
                    }

                    if (puntos != null) {

                        if (puntos.equals(puntosV)) {
                            System.out.println("Pelicula con " + puntosV + " puntos:");
                            System.out.println("Película: " + nombre.toUpperCase());
                            System.out.println("---------");

                            valoracionCincoPuntos = true;
                        }
                    }
                    if (puntos != null && (publico.equalsIgnoreCase("Todos los públicos") || publico.equalsIgnoreCase("Infantil"))) {
                        System.out.println("Película para todo los publicos o infantil:\n\"" + publico.toUpperCase() + "\" nombre: \"" + nombre.toUpperCase() + "\"");
                        System.out.println("---------");
                    }
                    if (publico.equalsIgnoreCase("Adultos")) {
                        System.out.println("Nombre de directores que han dirigido peliculas para adultos " + director.toUpperCase());

                    }
                    if (puntos == null || puntos == "") {
                        System.out.println("Nombre de pelicula sin puntos " + nombre.toUpperCase());

                    }
                }

            }
            if (!valoracionCincoPuntos) {
                System.out.println("No hay ninguna pelicula con valoración de: " + puntosV + " puntos");
            }

            //System.out.println("Estoy en el segundo: " + listaPeliculas.getLength() + " \nMarca Audi: " + contadorAudi + "\nPrecio mayor de 22.000€: " + puntosMayor + "\nExiste pelicula 3333CCC:" + (existeMatricula ? " si" : " no"));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
/* Método que muestre la cantidad de películas que contiene el fichero
b.      Nombre de las películas cuya valoración es igual a 5.
c.       Nombre de las películas infantiles o para todos los públicos.
d.      Nombre de las películas cuyo nombre contiene por lo menos un par de “aes”.
e.       Nombre de los directores que han dirigido películas para Adultos.
f.        Cantidad de películas cuya valoración en puntos no figura.
 */
