package br.com.triadworks.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.service.Sorteio;


public class SorteioTest {

	@Test
	public void deveSortearEmOrdemCrescente(){
		//Montar cenario
			
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(rafael, 250d));
		promocao.registra(new Lance(rommel, 300d));
		promocao.registra(new Lance(handerson, 400d));
		
		
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearEmOrdemDecrescente(){
		//Montar cenario
			
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		
		
		promocao.registra(new Lance(rafael, 250d));
		promocao.registra(new Lance(rommel, 300d));
		promocao.registra(new Lance(handerson, 400d));
		
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearEmOrdemAleatoria(){
		//Montar cenario
			
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(handerson, 400d));
		promocao.registra(new Lance(rommel, 250d));
		promocao.registra(new Lance(rafael, 300d));
		
		
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=400;
		double menorEsperado=250;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearComApenasUmLance(){
		//Montar cenario
			
		Cliente rafael = new Cliente("Rafael");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(rafael, 250d));
		
		
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		//validação
		double maiorEsperado=250;
		double menorEsperado=250;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(),0.001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(),0.001);
	}
	
	@Test
	public void deveSortearOsTresMenoresLances(){
		//Montar cenario
			
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(handerson, 300d));
		promocao.registra(new Lance(rommel, 100d));
		promocao.registra(new Lance(rafael, 20d));
		promocao.registra(new Lance(handerson, 440d));
		promocao.registra(new Lance(handerson, 1.25d));
		
		
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		//validação
		List<Lance> menores = sorteio.getTresMenores();
		Assert.assertEquals(3, menores.size());
		Assert.assertEquals(1.25, menores.get(0).getValor(),0.001);
		Assert.assertEquals(20, menores.get(1).getValor(),0.001);
		Assert.assertEquals(100, menores.get(2).getValor(),0.001);
		
		
	}
	@Test 
	public void deveSortearListaVazia(){
		//Montar cenario
		Promocao promocao = new Promocao("Xbox");
		//ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
				
		Assert.assertEquals(0, promocao.getLances().size());
	}
}
