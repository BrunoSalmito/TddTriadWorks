

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CadastroDeClientesTest {

	
	private static WebDriver driver;
	
	@BeforeClass
	public void inicializa(){
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\bruno\\Desktop\\lanceTdd\\TddTriadWorks\\geckodriver.exe");
		driver =new FirefoxDriver();
	}
	
	@AfterClass
	public void finaliza(){
		driver.close();
	}
	
	
	@Test 
	public void deveAdicionarNovoCliente(){
	
		
	
		driver.get("http:////localhost:8080//TddTriadWorks//pages//clientes//novo.xhtml");
		
		WebElement campoDeNome = driver.findElement(By.name("nome"));
		campoDeNome.sendKeys("Bruce");
		
		WebElement email = driver.findElement(By.name("email"));
		campoDeNome.sendKeys("Bruce@hotmail.com");
		
		WebElement botao = driver.findElement(By.name("btn-salvar"));
		botao.click();
		
		assertTrue(driver.getPageSource().contains("Bruce"));
		assertTrue(driver.getPageSource().contains("Bruce@hotmail.com"));
		
		driver.close();
		
	}
	
	
	@Test 
	public void naoDeveAdicionarNovoClienteSemEmail(){
		driver.get("http:////localhost:8080//TddTriadWorks//pages//clientes//novo.xhtml");
		
		WebElement campoDeNome = driver.findElement(By.name("nome"));
		campoDeNome.sendKeys("Bruce");
		
		
		WebElement botao = driver.findElement(By.name("btn-salvar"));
		botao.click();
		
		assertTrue(driver.getPageSource().contains("Email: campo é obrigatorio"));
	
		
	}
}
