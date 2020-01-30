import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.*;

public class Main2 {

    public static void main(String [] args) {

        String rcn = "";
        String acronym = "";
        String objective = "";
        String title = "";
        String identifier = "";
        String text = "";
        String new_text = "";

        String Json_file_name = "C:\\Users\\kosta\\Desktop\\Json File\\file.json";
        JSONObject j_obj;
        String jsonString = "";
        String json_data [] = new String [18316];

        String table [] = new String [18316];
        String new_xml_name = "";

        for (int i = 0; i < 18316; i++){

            table[i] = "C:\\Users\\kosta\\Desktop\\New Parsed Files\\" +Integer.toString(i+1)+".xml";

        }

        File dir = new File("C:\\Users\\kosta\\Desktop\\\\Parsed files_original");
        File [] files = dir.listFiles();

        int counter = 0;
        int pointer = 0;

        for(File file : files) {

            if(file.isFile() && file.getName().endsWith(".xml")) {

                new_xml_name = table[counter];
                counter++;

                try {

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(file);
                    Element rootElement = doc.getDocumentElement();

                    rcn = doc.getElementsByTagName("rcn").item(0).getTextContent();
                    acronym = doc.getElementsByTagName("acronym").item(0).getTextContent();
                    objective = doc.getElementsByTagName("objective").item(0).getTextContent();
                    title = doc.getElementsByTagName("title").item(0).getTextContent();
                    identifier = doc.getElementsByTagName("identifier").item(0).getTextContent();
                    text = title + " " + objective;
                    new_text = text.replaceAll("\\n",",");
                    new_text = new_text.replaceAll("[^a-zA-Z0-9\\s]", "");

                    WriteNewXml(rcn,acronym,new_text,identifier,new_xml_name);

                    j_obj = new JSONObject();
                    j_obj.put("Rcn", rcn);
                    j_obj.put("Acronym", acronym);
                    j_obj.put("Text", new_text);
                    j_obj.put("identifier", identifier);
                    jsonString = j_obj.toString();
                    json_data[pointer] = jsonString;
                    pointer++;

                } catch (Exception e){

                    e.printStackTrace();

                }

            }

        }

        String file_name = "C:\\Users\\kosta\\Desktop\\Json File\\Reviews.json";

        try(FileWriter file = new FileWriter(file_name)) {

            File fout = new File(file_name);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (int i = 0; i < 18316; i++) {

                bw.write("{" +"\"index\"" + ":" + "{" +"\"_id\"" + ":" + "\"" + (i+1) + "\"" + "}" + "}");
                bw.newLine();
                bw.write(json_data[i]);
                bw.newLine();

            }

            bw.close();

        } catch (IOException e1) {

            e1.printStackTrace();

        }

    }

    private static void WriteNewXml(String new_rcn, String new_acronym, String new_text, String new_identifier,String new_xml_name) {

        try {

            DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
            Document doc1 = dBuilder1.newDocument();
            Element rootElement = doc1.createElement("project");
            Attr attr = doc1.createAttribute("xmlns");
            attr.setValue("http://cordis.europa.eu");
            rootElement.setAttributeNode(attr);
            doc1.appendChild(rootElement);
            Element rcn = doc1.createElement("rcn");
            rcn.setTextContent(new_rcn);
            rootElement.appendChild(rcn);
            Element acronym = doc1.createElement("acronym");
            acronym.setTextContent(new_acronym);
            rootElement.appendChild(acronym);
            Element text = doc1.createElement("text");
            text.setTextContent(new_text);
            rootElement.appendChild(text);
            Element identifier = doc1.createElement("identifier");
            identifier.setTextContent(new_identifier);
            rootElement.appendChild(identifier);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc1);
            StreamResult result = new StreamResult(new File(new_xml_name));
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
            transformer.transform(source,result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}