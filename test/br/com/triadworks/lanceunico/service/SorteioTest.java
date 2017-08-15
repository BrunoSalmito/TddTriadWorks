package br.com.triadworks.lanceunico.service;

import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.builder.CriadorDePromocao;
import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.service.Sorteio;


public class SorteioTest {

	private Sorteio sorteio;
	
	Cliente rafael;
	Cliente rommel;
	Cliente handerson;
	
	@Before
	public void setUp(){
		this.sorteio= new Sorteio(); 
		
		rafael = new Cliente("Rafael");
		rommel = new Cliente("Rommel");
		handerson = new Cliente("Handerson");
	}
	
	@Test
	public void deveSortearEmOrdemCrescente(){
		//Montar cenario
		
		Promocao promocao = new CriadorDePromocao()
					.para("xbox")
					.comLance(rafael, 250d) 
					.comLance(rommel, 300d) 
					.comLance(handerson, 400d) 
					.cria();
		
		
		//ação
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearEmOrdemDecrescente(){
		//Montar cenario
			
	
		Promocao promocao = new CriadorDePromocao()
				.para("xbox")
				.comLance(rafael, 400) 
				.comLance(rommel, 300d) 
				.comLance(handerson, 250d) 
				.cria();
		
		//ação
		
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearEmOrdemAleatoria(){
		//Montar cenario
			
		Promocao promocao = new CriadorDePromocao()
				.para("xbox")
				.comLance(rafael, 400) 
				.comLance(rommel, 250d) 
				.comLance(handerson, 300d) 
				.cria();
		
		//ação
		
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearComApenasUmLance(){
		//Montar cenario
			
		
		
		Promocao promocao = new CriadorDePromocao()
				.para("xbox")
				.comLance(rafael, 250) 
			
				.cria();
		
		
		//ação
		
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=250;
		double menorEsperado=250;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearOsTresMenoresLances(){
		//Montar cenario
			
		Promocao promocao = new CriadorDePromocao()
				.para("xbox")
				.comLance(rafael, 300d) 
				.comLance(rafael, 100d) 
				.comLance(rafael, 20d) 
				.comLance(rafael, 440d) 
				.comLance(rafael, 1.25d) 
				.cria();
		
		
	
		//ação
		
		sorteio.sorteia(promocao);
		
		//validação
		List<Lance> menores = sorteio.getTresMenores();
		assertEquals(3, menores.size());
		assertEquals(1.25, menores.get(0).getValor(),0.001);
		assertEquals(20, menores.get(1).getValor(),0.001);
		assertEquals(100, menores.get(2).getValor(),0.001);
		
		
	}
	@Test 
	public void deveSortearListaVazia(){
		//Montar cenario
		Promocao promocao = new CriadorDePromocao()
				.para("xbox")
				.cria();
		
		//ação
		
		sorteio.sorteia(promocao);
				
		assertEquals(0, promocao.getLances().size());
	}
}
