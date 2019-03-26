package uimaps;

import org.openqa.selenium.By;

/**
 * Classe ReadmineMap, classe responsável por conter o mapeamento de todos os campos disponíveis na página.
 * 
 * @author rodri
 * 
 */
public class ReadmineMap {
	
	public By linkRegistrarUsuario = By.xpath("//*[@id='content']/div[1]/p[2]/a");
	public By buttonRegistrarUsuario = By.xpath("//*[@id=\"new_user\"]/input[3]");
	public By tituloH2 = By.xpath("//*[@id=\"content\"]/h2");
	public By buttonLogin = By.xpath("//*[@id=\"login-form\"]/form/table/tbody/tr[4]/td[2]/input");
	public By linkMenuProjetos = By.xpath("//*[@id=\"top-menu\"]/ul/li[3]/a");
	public By linkCriarNovoProjeto = By.xpath("//*[@id=\"content\"]/div[1]/a[1]");
	public By checkboxFeatureNovoProjeto = By.xpath("//*[@id=\"project_trackers\"]/label[2]/input");
	public By checkboxSupportNovoProjeto = By.xpath("//*[@id=\"project_trackers\"]/label[3]/input");
	public By buttonCriaProjeto = By.xpath("//*[@id=\"new_project\"]/input[3]");
	public By buttonCriarTarefa = By.xpath("//*[@id=\"issue-form\"]/input[3]");
	public By menuListaTarefas = By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/a");
	public By iconOrdernarTarefas = By.xpath("//*[@id=\"content\"]/form[2]/div/table/thead/tr/th[2]/a");
	public By proximaPagina = By.xpath("//*[@id=\"content\"]/p[1]/a[2]");
	
	
	public By inputUsarName = By.id("user_login");
	public By inputSenha = By.id("user_password");
	public By inputSenhaConfirmacao = By.id("user_password_confirmation");
	public By inputFirstName = By.id("user_firstname");
	public By inputLastName = By.id("user_lastname");
	public By inputEmail = By.id("user_mail");
	public By inputUserLogin = By.id("username");
	public By inputSenhaLogin = By.id("password");
	public By inputNameProjeto = By.id("project_name");
	public By inputDescricaoProjeto = By.id("project_description");
	public By inputTituloTarefa = By.id("issue_subject");
	public By textAreaDescricaoTarefa = By.id("issue_description");
	public By selectSituacaoTarefa = By.id("issue_status_id");
	public By selectPrioridadeTarefa = By.id("issue_priority_id");
	public By menuNovaTarefa = By.className("new-issue");
	public By boxErroRegister = By.id("errorExplanation");
	
	
}
