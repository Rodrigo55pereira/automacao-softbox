package pages.dados;

public class Usuario {
	
	private String nameUsuario;
	private String senha;
	private String nome;
	private String sobreNome;
	private String email;
	
	public Usuario(String nameUsuario, String senha, String nome, String sobreNome, String email) {
		this.nameUsuario = nameUsuario;
		this.senha = senha;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.email = email;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public String getEmail() {
		return email;
	}
	
}
