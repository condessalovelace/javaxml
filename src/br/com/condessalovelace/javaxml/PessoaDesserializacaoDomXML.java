package br.com.condessalovelace.javaxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Técnica de desserialização de arquivos XML utilizando a abordagem DOM da
 * biblioteca javax.xml.parsers.
 * 
 * @author condessalovelace
 *
 */
public class PessoaDesserializacaoDomXML {
	public static void main(String[] args) throws Exception {
		// Cria a fábrica de construtores de documentos
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Valida o arquivo com o XSD
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		// Cria um construtor de documentos
		DocumentBuilder builder = factory.newDocumentBuilder();
		// Cria documento a partir de arquivo
		Document document = builder.parse("src/pessoas.xml");
		// Imprime todo o documento baseado no Element.
		System.out.println("-----Documento Completo-----\n");
		imprimirDocumentoCompleto(document);
		System.out.println("\n\n-----Consulta XPath-----\n");
		consultarComXPath(document);
	}

	private static void consultarComXPath(Document document) throws Exception {
		// Segunda pessoa do arquivo
		// String expressao = "/pessoas/pessoa[2]";
		// Contem o nome Geller
		String expressao = "/pessoas/pessoa[contains(nome,'Geller')]";
		XPath xPath = XPathFactory.newInstance().newXPath();
		XPathExpression xPathExpression = xPath.compile(expressao);
		NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element elementPessoa = (Element) nodeList.item(i);
			System.out.print("Nome: " + elementPessoa.getElementsByTagName("nome").item(0).getTextContent() + ", ");
			System.out.println("Idade: " + elementPessoa.getElementsByTagName("idade").item(0).getTextContent());

		}
	}

	private static void imprimirDocumentoCompleto(Document document) {
		imprimirAtributos(document.getDocumentElement());
		imprimirTags(document);
	}

	private static void imprimirTags(Document document) {
		System.out.println("Tags:");
		NodeList nodeList = document.getElementsByTagName("pessoas");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element elementPessoas = (Element) nodeList.item(i);
			NodeList nodeListPessoas = elementPessoas.getElementsByTagName("pessoa");
			for (int j = 0; j < nodeListPessoas.getLength(); j++) {
				Element elementPessoa = (Element) nodeListPessoas.item(j);
				System.out.print("Nome: " + elementPessoa.getElementsByTagName("nome").item(0).getTextContent() + ", ");
				System.out.println("Idade: " + elementPessoa.getElementsByTagName("idade").item(0).getTextContent());
			}
		}
	}

	private static void imprimirAtributos(Element element) {
		System.out.println("Atributos:");
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			Node node = element.getAttributes().item(i);
			System.out.println(node.getNodeName() + ": " + node.getNodeValue());
		}
	}
}
