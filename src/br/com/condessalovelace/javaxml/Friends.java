package br.com.condessalovelace.javaxml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // Representa um documento XML
@XmlAccessorType(XmlAccessType.FIELD) // Deve acessar os atributos pelos fields, não pelos métodos get e set
public class Friends {
	@XmlElementWrapper(name = "pessoas") // Nome da tag que encapsula a lista
	@XmlElement(name = "pessoa") // Nome da tag que é elemento da lista
	private List<Pessoa> pessoas;

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return pessoas.toString();
	}
}
