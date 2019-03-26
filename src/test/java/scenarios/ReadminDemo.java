package scenarios;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.ReadminePage;
import pages.dados.Projeto;
import pages.dados.Usuario;

/**
 * Classe ReadminDemo, classe de cenários responsável executar os testes   
 * 
 * @author rodri
 * 
 */
public class ReadminDemo {
	
	WebDriver driver;
	ReadminePage readminePage;
	
	@BeforeClass
	public static void beforeClass() {
		String driverPath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
	}
	
	@Before
	public void before() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		readminePage = new ReadminePage(driver);
	}
	
	@Test
	public void runTest() throws InterruptedException{
		// Acessa o site.
		readminePage.navegar("http://demo.redmine.org/");
		
		// Navega até a tela de cadastro de usuário.
		readminePage.telaRegistro();
		
		// Cria o objeto Usuário para cadastrar na aplicação.
		Usuario usuarioRegister = new Usuario("RodrigoPereiraTeste","teste123", "Rodrigo Pereira", "Moraes", "rodrigopereiraTeste@gmail.com");
		
		// Registra e valida se o usuário já existe. Ao registrar é acessado a tela de projetos.
		// Caso o usuario exista, é realizado o login na aplicação e acessado a tela de projetos.
		if(readminePage.registrarUsuario(usuarioRegister)) {
			readminePage.telaProjeto();
		}
		else {
			// Realiza o login na aplicação caso o usuário já exista.
			readminePage.fazerLogin(usuarioRegister.getNameUsuario(),usuarioRegister.getSenha());
			readminePage.telaProjeto();
		}
		
		// Criando o objeto projeto, inserindo apenas informações obrigatórias para a criação do projeto
		Projeto projeto = new Projeto("teste", "teste criacao de um projeto"); 
		
		// Cria o projeto e redireciona para a tela de tarefas.
		readminePage.criarProjetoRedirecionaParaTarefas(projeto);
		
		// Criando tarefas a partir de um arquivo json
		readminePage.criaTarefas();
		
		// validando se tarefa
		readminePage.validaTarefa();
		
	  }
	
	@After
	public void afterClass() {
		driver.close();
	}

}
