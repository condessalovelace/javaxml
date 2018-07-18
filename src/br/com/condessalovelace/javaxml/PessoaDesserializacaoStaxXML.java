package br.com.condessalovelace.javaxml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

/**
 * Técnica de desserialização de arquivos XML com abordagem orientada à eventos
 * através da biblioteca Stax.
 * 
 * @author condessalovelace
 *
 */
public class PessoaDesserializacaoStaxXML {
	public static void main(String[] args) throws Exception {
		InputStream inputStream = new FileInputStream("src/pessoas.xml");
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventos = factory.createXMLEventReader(inputStream);
		Pessoa pessoa = new Pessoa();
		List<Pessoa> pessoas = new ArrayList<>();

		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("pessoa")) {
				pessoa = new Pessoa();
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();
				String conteudo = evento.asCharacters().getData();
				pessoa.setNome(conteudo);
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("idade")) {
				evento = eventos.nextEvent();
				String conteudo = evento.asCharacters().getData();
				pessoa.setIdade(Integer.parseInt(conteudo));
			} else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("pessoa")) {
				pessoas.add(pessoa);
			}
		}
		System.out.println(pessoas);
	}
}
