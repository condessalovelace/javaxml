package br.com.condessalovelace.javaxml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Implementação de conversor de XML para HTML usando XSLT.
 * 
 * @author condessalovelace
 *
 */
public class ConversorXMLParaHTML {
	public static void main(String[] args) throws Exception {
		InputStream xsl = new FileInputStream("src/XMLparaHTML.xsl");
		StreamSource xslSource = new StreamSource(xsl);
		InputStream dados = new FileInputStream("src/pessoas.xml");
		StreamSource xmlSource = new StreamSource(dados);
		Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);
		StreamResult saida = new StreamResult("src/pessoas.html");
		transformer.transform(xmlSource, saida);
	}
}
