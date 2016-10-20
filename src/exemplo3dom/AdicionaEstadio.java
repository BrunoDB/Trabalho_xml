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
public class AdicionaEstadio {

    static public void main(String[] argv) {
        try {
            DocumentBuilderFactory b = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = b.newDocumentBuilder();
            Document myDoc = builder.parse("estadios.xml");
            adiciona(myDoc);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void adiciona(Document doc) throws IOException, TransformerException {
        Element estadios, estadio, localidade, pais, equipe = null, time, funcoes, nome_funcao, funcao;
        Scanner leitura = new Scanner(System.in);

        //obtem referencia do elemento estadios
        estadios = (Element) doc.getElementsByTagName("estadios").item(0);
        // define novo elemento localidade
        estadio = doc.createElement("estadio");

        //define atributo e adiciona ao elemento
        System.out.print("Informe a localidade (pais) do time: ");
        estadio.setAttribute("estadio", leitura.nextLine());
        
        // define subelementos de equipe
        System.out.print("Informe o nome do time: ");
        equipe.appendChild(doc.createTextNode(leitura.nextLine()));
        localidade.appendChild(pais);
        equipe = doc.createElement("equipe");
        System.out.print("Informe o equipe: ");
        equipe.appendChild(doc.createTextNode(leitura.nextLine()));
        localidade.appendChild(equipe);

        //define elemento time e subelementos
        time = doc.createElement("time");
        funcoes = doc.createElement("funcoes");
        System.out.print("Informe o funcoes: ");
        funcoes.appendChild(doc.createTextNode(leitura.nextLine()));
        nome_funcao = doc.createElement("nome_funcao");
        System.out.print("Informe a nome_funcao: ");
        nome_funcao.appendChild(doc.createTextNode(leitura.nextLine()));
        funcao = doc.createElement("funcao");
        System.out.print("Informe o funcao: ");
        funcao.appendChild(doc.createTextNode(leitura.nextLine()));
        time.appendChild(funcoes);
        time.appendChild(nome_funcao);
        time.appendChild(funcao);
        localidade.appendChild(time);

        //adiciona no localidade
        estadios.appendChild(localidade);
        
        estadios.appendChild(doc.createComment("Arquivo gerado por exemplo3DOM"));

        //serializa documento para arquivo
        XMLSerializer serializer = new XMLSerializer(
                new FileOutputStream("estadios.xml"), new OutputFormat(doc, "iso-8859-1", true));
        serializer.serialize(doc);
    }
}
