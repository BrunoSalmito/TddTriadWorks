package br.com.triadworks.lanceunico.builder;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class CriadorDePromocao {

	private Promocao promocao;
	
	public CriadorDePromocao para(String nome){
		promocao= new Promocao(nome);
		return this;
	}
	public CriadorDePromocao comLance(Cliente cliente,double valor){
		promocao.registra(new Lance(cliente, valor));
		return this;
	}
	
	public Promocao cria(){
		return promocao;
	}
}
