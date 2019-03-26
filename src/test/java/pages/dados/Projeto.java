package pages.dados;

import java.util.Random;


/**
 * Classe de Projeto
 * 
 * @author rodri
 * 
 */
public class Projeto {
	
	private String nome;
	private String descricao;
	
	public Projeto(String nome, String descricao) {
		Random gerador = new Random();
		
		this.nome = nome + gerador.nextInt(1000);
		this.descricao = descricao;

	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
