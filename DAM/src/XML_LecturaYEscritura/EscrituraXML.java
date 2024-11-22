package XML_LecturaYEscritura;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EscrituraXML {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento ra�z del documento
            Element eRaiz = doc.createElement("concesionario");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendr� los elementos
            Element coches = doc.createElement("coches");
            eRaiz.appendChild(coches);

            String[] vehiculos = new String[]{"coche1", "coche2", "coche3", "coche4", "coche5", "coche6", "coche7", "coche8"};
            String[] matriculas = new String[]{"1111AAA", "2222BBB", "3333CCC", "4444DDD", "AA334A3", "3444BBB", "3333CCC", "4444DDD"};
            String[] marcas = new String[]{"AUDI", "SEAT", "BMW", "TOYOTA", "AUDI", "AUDI", "BMW", "TOYOTA"};
            String[] precios = new String[]{"30000", "10000", "20000", "10000", "23444", "12344", "20000", "10000"};

            for (int i = 0; i < vehiculos.length; i++) {

                // definimos el nodo que contendr� los elementos
                Element eCoche = doc.createElement("coche");
                coches.appendChild(eCoche);

                // atributo para el nodo coche
                Element matricula = doc.createElement("matricula");
                matricula.appendChild(doc.createTextNode(matriculas[i]));
                eCoche.appendChild(matricula);

                // definimos cada uno de los elementos y le asignamos un valor
                Element eMarca = doc.createElement("marca");
                eMarca.appendChild(doc.createTextNode(marcas[i]));
                eCoche.appendChild(eMarca);

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(precios[i]));
                eCoche.appendChild(precio);

            }

            // clases necesarias finalizar la creaci�n del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("concesionario.xml"));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mostrarInformacion();

    }

    public static void mostrarInformacion() {
        int contadorAudi = 0;
        int precioMayor = 0;
        boolean existeMatricula = false;

        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File("concesionario.xml"));

            // Cojo todas las etiquetas coche del documento
            NodeList listaCoches = documento.getElementsByTagName("coche");

            // Recorro las etiquetas
            for (int i = 0; i < listaCoches.getLength(); i++) {
                // Cojo el nodo actual
                Node nodo = listaCoches.item(i);

                // Compruebo si el nodo es un elemento
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    // Lo transformo a Element
                    Element e = (Element) nodo;
                    // Obtengo sus hijos
                    NodeList hijos = e.getChildNodes();
                    // Recorro sus hijos
                    for (int j = 0; j < hijos.getLength(); j++) {
                        // Obtengo al hijo actual

                        Node hijo = hijos.item(j);

                        if (hijo.getNodeName().trim().equalsIgnoreCase("precio") && Integer.parseInt(hijo.getTextContent()) > 22000) {
                            precioMayor++;

                            //System.out.println(hijo.getTextContent());
                        }
                        if (hijo.getNodeName().trim().equalsIgnoreCase("matricula") && hijo.getTextContent().equalsIgnoreCase("3333CCC")) {

                            existeMatricula = true;
                            //System.out.println(hijo.getTextContent());
                        }
                        if (hijo.getTextContent().trim().equalsIgnoreCase("audi")) {
                            contadorAudi++;

                        }

                    }
                }

            }

            System.out.println("Total de coches: " + listaCoches.getLength() + " \nMarca Audi: " + contadorAudi + "\nPrecio mayor de 22.000€: " + precioMayor + "\nExiste matricula 3333CCC:" + (existeMatricula ? " si" : " no"));

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
