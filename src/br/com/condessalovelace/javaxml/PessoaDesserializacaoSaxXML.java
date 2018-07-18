package br.com.condessalovelace.javaxml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Técnica de desserializacao de arquivos XML utilizando uma abordagem orientada
 * à eventos através da biblioteca Sax.
 * 
 * @author condessalovelace
 *
 */
public class PessoaDesserializacaoSaxXML {
	public static void main(String[] args) throws Exception {
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		PessoasHandler logica = new PessoasHandler();
		xmlReader.setContentHandler(logica);
		InputStream inputStream = new FileInputStream("src/pessoas.xml");
		InputSource inputSource = new InputSource(inputStream);
		xmlReader.parse(inputSource);
		System.out.println(logica.getPessoas());
	}
}

class PessoasHandler extends DefaultHandler {
	private StringBuilder conteudo = new StringBuilder();
	private List<Pessoa> pessoas = new ArrayList<>();
	private Pessoa pessoa;

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("pessoa")) {
			pessoa = new Pessoa();
		}
		conteudo = new StringBuilder();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		conteudo.append(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("pessoa")) {
			pessoas.add(pessoa);
		} else if (qName.equals("nome")) {
			pessoa.setNome(conteudo.toString());
		} else if (qName.equals("idade")) {
			pessoa.setIdade(Integer.parseInt(conteudo.toString()));
		}
	}
}
