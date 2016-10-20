package exemplo3dom;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * @author BDB-DJ
 */
public class AdicionaCliente {

    static public void main(String[] argv) {
        try {
            DocumentBuilderFactory b = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = b.newDocumentBuilder();
            Document myDoc = builder.parse("clientes.xml");
            adiciona(myDoc);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void adiciona(Document doc) throws IOException, TransformerException {
        Element clientes, cliente, razao_social, cgc, endereco, logradouro, cidade, estado;
        Scanner leitura = new Scanner(System.in);

        //obtem referencia do elemento clientes
        clientes = (Element) doc.getElementsByTagName("clientes").item(0);
        // define novo elemento cliente
        cliente = doc.createElement("cliente");

        //define atributo e adiciona ao elemento
        System.out.print("Informe o numero do cliente: ");
        cliente.setAttribute("numero", leitura.nextLine());
        
        // define subelementos de cliente
        razao_social = doc.createElement("razao_social");
        System.out.print("Informe a razao social: ");
        razao_social.appendChild(doc.createTextNode(leitura.nextLine()));
        cliente.appendChild(razao_social);
        cgc = doc.createElement("cgc");
        System.out.print("Informe o cgc: ");
        cgc.appendChild(doc.createTextNode(leitura.nextLine()));
        cliente.appendChild(cgc);

        //define elemento endereco e subelementos
        endereco = doc.createElement("endereco");
        logradouro = doc.createElement("logradouro");
        System.out.print("Informe o logradouro: ");
        logradouro.appendChild(doc.createTextNode(leitura.nextLine()));
        cidade = doc.createElement("cidade");
        System.out.print("Informe a cidade: ");
        cidade.appendChild(doc.createTextNode(leitura.nextLine()));
        estado = doc.createElement("estado");
        System.out.print("Informe o estado: ");
        estado.appendChild(doc.createTextNode(leitura.nextLine()));
        endereco.appendChild(logradouro);
        endereco.appendChild(cidade);
        endereco.appendChild(estado);
        cliente.appendChild(endereco);

        //adiciona no cliente
        clientes.appendChild(cliente);
        
        clientes.appendChild(doc.createComment("Arquivo gerado por exemplo3DOM"));

        //serializa documento para arquivo
        XMLSerializer serializer = new XMLSerializer(
                new FileOutputStream("clientes.xml"), new OutputFormat(doc, "iso-8859-1", true));
        serializer.serialize(doc);
    }
}
