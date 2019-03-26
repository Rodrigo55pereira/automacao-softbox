package pages.dados;

public class Tarefa {

	private String titulo;
	private String tipo;
	private String descricao;
	private String situacao;
	private String prioridade;

	public Tarefa(String titulo, String tipo, String descricao, String situacao, String prioridade) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.descricao = descricao;
		this.situacao = situacao;
		this.prioridade = prioridade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public boolean equals(Tarefa obj) {
		if ((this.descricao.equals(obj.getDescricao())) && (this.prioridade.equals(obj.getPrioridade()))
				&& (this.situacao.equals(obj.getSituacao())) && (this.tipo.equals(obj.getTipo()))
				&& (this.titulo.equals(obj.getTitulo()))) {

			return true;

		} else {
			return false;
		}
	}
}
