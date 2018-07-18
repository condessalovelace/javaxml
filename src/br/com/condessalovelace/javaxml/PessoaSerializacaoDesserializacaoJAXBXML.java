package br.com.condessalovelace.javaxml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Técnica de serialização/desserializacao de XML através de Biding usando a
 * biblioteca JAXB.
 * 
 * @author condessalovelace
 *
 */
public class PessoaSerializacaoDesserializacaoJAXBXML {
	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Friends.class);
		desserializacao(context);
		serializacao(context);
	}

	private static void serializacao(JAXBContext context) throws Exception {
		Friends friends = new Friends();
		friends.setPessoas(new ArrayList<>());
		friends.getPessoas().add(new Pessoa("Phoebe Buffay", 28));
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(friends, new File("src/friends_novo.xml"));
	}

	private static void desserializacao(JAXBContext context) throws Exception {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Friends pessoas = (Friends) unmarshaller.unmarshal(new FileInputStream("src/friends.xml"));
		System.out.println(pessoas);
	}
}
