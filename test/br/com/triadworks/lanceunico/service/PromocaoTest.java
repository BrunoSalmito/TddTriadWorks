package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.builder.CriadorDePromocao;
import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class PromocaoTest {

	Cliente handerson;
	Cliente rafael;
	
	@Before
	public void setUp(){
		rafael = new Cliente("Rafael");
		handerson = new Cliente("handerson");
	}
	
	
	@Test 
	public void deveRegistrarUmLance(){
		
		
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comLance(rafael, 1000)
				.cria();
		
		List<Lance> lances = promocao.getLances();
		
		
		assertEquals(1, lances.size());
		assertEquals(1000,lances.get(0).getValor(),0.0001);
		
	}
	
	@Test 
	public void deveRegistrarVariosLances(){
			
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comLance(rafael, 1000)
				.comLance(handerson, 2000)
				.cria();
		List<Lance> lances = promocao.getLances();
		
		
		assertEquals(2, lances.size());
		assertEquals(1000,lances.get(0).getValor(),0.0001);
		assertEquals(2000,lances.get(1).getValor(),0.0001);
		
	}
	
	@Test 
	public void naoDeveRegistrarDoisLancesSeguidosDoMesmoCliente(){
			
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comLance(rafael, 1000)
				.comLance(rafael, 2000)
				.cria();
		List<Lance> lances = promocao.getLances();
		
		
		assertEquals(1, lances.size());
		assertEquals(1000,lances.get(0).getValor(),0.0001);
		
		
	}
	
	@Test 
	public void naoDeveRegistrarMaisDoQueCincoLancesDoMesmoCliente(){
			
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comLance(rafael, 1000)
				.comLance(handerson, 1000)
				.comLance(rafael, 1000)
				.comLance(handerson, 1000)
				.comLance(rafael, 1000)
				.comLance(handerson, 1000)
				.comLance(rafael, 1000)
				.comLance(handerson, 1000)
				.comLance(rafael, 1000)
				.comLance(handerson, 1000)
				.comLance(rafael, 4000)
				.comLance(handerson, 3000)
				.cria();
		List<Lance> lances = promocao.getLances();
		
		assertEquals(10, lances.size());
		Lance ultimo = lances.get(lances.size()-1);
		
		assertEquals(1000,ultimo.getValor(),0.0001);
		
		
	}
	@Test(expected =RuntimeException.class)
	public void naoDeveRegistrarLanceComValorNegativo(){
		
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comLance(rafael, -10)
				.cria();
		
	}
	@Test(expected =RuntimeException.class)
	public void naoDeveRegistrarLanceComValorMaiorQuePermitido(){
		
		Promocao promocao = new CriadorDePromocao()
				.para("ipad")
				.comValorMaximo(1000)
				.comLance(rafael, 10000)
				.cria();
		
	}
}
