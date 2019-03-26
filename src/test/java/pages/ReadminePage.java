package pages;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;

import pages.dados.Projeto;
import pages.dados.Tarefa;
import pages.dados.TarefaJson;
import pages.dados.Usuario;
import uimaps.ReadmineMap;

/**
 * Classe ReadminePage, classe de páginas responsável por conter todas as ações
 * que serão realizados na pagina web.
 * 
 * @author rodri
 * 
 */
public class ReadminePage {
	WebDriver driver;
	ReadmineMap readminMap = new ReadmineMap();

	public ReadminePage(WebDriver driver) {
		this.driver = driver;
	}

	public void navegar(String url) {
		driver.navigate().to(url);
	}

	public void telaRegistro() {
		driver.findElement(readminMap.linkRegistrarUsuario).click();
	}

	public void telaProjeto() {
		driver.findElement(readminMap.linkMenuProjetos).click();
	}

	public boolean registrarUsuario(Usuario usuario) {
		// Inseri UserName
		driver.findElement(readminMap.inputUsarName).sendKeys(usuario.getNameUsuario());
		// Inseri Senha
		driver.findElement(readminMap.inputSenha).sendKeys(usuario.getSenha());
		// Inseri senha de confirmacao
		driver.findElement(readminMap.inputSenhaConfirmacao).sendKeys(usuario.getSenha());
		// Inseri Nome
		driver.findElement(readminMap.inputFirstName).sendKeys(usuario.getNome());
		// Inseri Sobrenome
		driver.findElement(readminMap.inputLastName).sendKeys(usuario.getSobreNome());
		// Inseri Email.
		driver.findElement(readminMap.inputEmail).sendKeys(usuario.getEmail());
		// Registra usuário
		driver.findElement(readminMap.buttonRegistrarUsuario).click();
		// Valida se o usuário não foi redirecionado para a tela do usuário após o
		// registro
		if (driver.findElement(readminMap.tituloH2).getText().equals("Cadastre-se")) {
			System.err.println("Usuário ou Email já estão sendo utilizados, redirecionado para a tela de login!");
			return false;
		}

		System.out.println("Usuario cadastrado com sucesso");
		return true;

	}

	public void fazerLogin(String usuario, String senha) {
		// Acessa a tela de login da aplicação
		this.navegar("http://demo.redmine.org/login");

		// Inseri o userName
		driver.findElement(readminMap.inputUserLogin).sendKeys(usuario);
		// Inseri a senha
		driver.findElement(readminMap.inputSenhaLogin).sendKeys(senha);
		// Realiza o login
		driver.findElement(readminMap.buttonLogin).click();
	}

	public void criarProjetoRedirecionaParaTarefas(Projeto projeto) throws InterruptedException {
		driver.findElement(readminMap.linkCriarNovoProjeto).click();

		// Inseri NomeProjeto
		driver.findElement(readminMap.inputNameProjeto).sendKeys(projeto.getNome());
		// Inseri NomeProjeto
		driver.findElement(readminMap.inputDescricaoProjeto).sendKeys(projeto.getDescricao());

		// Desmarca as opções Feature e Support
		driver.findElement(readminMap.checkboxFeatureNovoProjeto).click();
		driver.findElement(readminMap.checkboxSupportNovoProjeto).click();

		// Salva o projeto
		driver.findElement(readminMap.buttonCriaProjeto).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("flash_notice")));
		System.out.println(driver.findElement(By.id("flash_notice")).getText());
		Thread.sleep(1000);

		driver.findElement(readminMap.linkMenuProjetos).click();

		driver.findElement(By.linkText(projeto.getNome())).click();

		telaCriarTarefa();

	}

	public void criaTarefas() {

		Gson gson = new Gson();

		try {

			// Lê o arquivo json de tarefas
			String jsonFile = readFileJson("jsonTarefas.json");

			TarefaJson tarefas = gson.fromJson(jsonFile, TarefaJson.class);

			List<Tarefa> tarefasList = tarefas.getTarefa();

			// Percorre linha por linha e adiciona
			for (Tarefa elem : tarefasList) {

				driver.findElement(readminMap.inputTituloTarefa).sendKeys(elem.getTitulo());
				driver.findElement(readminMap.textAreaDescricaoTarefa).sendKeys(elem.getDescricao());

				Select selectSituacao = new Select(driver.findElement(readminMap.selectSituacaoTarefa));
				selectSituacao.selectByVisibleText(elem.getSituacao());

				Select selectPrioridade = new Select(driver.findElement(readminMap.selectPrioridadeTarefa));
				selectPrioridade.selectByVisibleText(elem.getPrioridade());

				driver.findElement(readminMap.buttonCriarTarefa).click();

				// Thread.sleep(1000);

				telaCriarTarefa();

			}

			driver.findElement(readminMap.menuListaTarefas).click();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validaTarefa() {
		driver.findElement(readminMap.iconOrdernarTarefas).click();
		driver.findElement(readminMap.proximaPagina).click();

		// Recupera o tipo da tarefa antes de entrar na mesma
		String tipo = driver.findElement(By.xpath("//*[contains(@class,'list')]/tbody/tr[4]/td[3]")).getText();

		driver.findElement(By.xpath("//*[contains(@class,'list')]/tbody/tr[4]/td[2]")).click();

		// Recupera e cria a tarefa.

		String status = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[1]")).getText();
		String descricao = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div[2]/p")).getText();
		String prioridade = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[1]"))
				.getText();
		String titulo = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/h3")).getText();

		Tarefa tarefa = new Tarefa(titulo, tipo, descricao, status, prioridade);

		try {

			Gson gson = new Gson();

			String jsonFile = readFileJson("jsonTarefasValidacao.json");

			TarefaJson tarefasValidacao = gson.fromJson(jsonFile, TarefaJson.class);

			List<Tarefa> tarefasList = tarefasValidacao.getTarefa();

			// pega a vigésima nona tarefa do array de validaçao conforme solicitado no
			// exercicio.
			Tarefa tvalidacao = tarefasList.get(28);

			if (tarefa.equals(tvalidacao)) {

				System.out.println("As tarefas são do mesmo tipo");
			} else {
				System.out.println("As tarefas não são do mesmo tipo");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void telaCriarTarefa() {
		driver.findElement(readminMap.menuNovaTarefa).click();
	}

	private String readFileJson(String fileDir) {

		try {

			String content = null;
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileDir).getFile());
			FileReader reader = null;

			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();

			return content.trim();

		} catch (Exception e) {
			System.out.println("Erro ao ler Arquivo Json");
		}

		return null;
	}

}
