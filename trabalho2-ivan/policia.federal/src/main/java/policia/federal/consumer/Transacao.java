package policia.federal.consumer;


public class Transacao {

	private String codigo;

	private String cedente;

	private String pagador;

	private Double valor;

	private String vencimento;

	public Transacao(String codigo, String cedente, String pagador, Double valor, String vencimento) {
		this.codigo = codigo;
		this.cedente = cedente;
		this.pagador = pagador;
		this.valor = valor;
		this.vencimento = vencimento;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCedente() {
		return this.cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getPagador() {
		return this.pagador;
	}

	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getVencimento() {
		return this.vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String toString() {
		return "Transacao [codigo=" + this.codigo + ", cedente=" + this.cedente + ", pagador=" + this.pagador
				+ ", valor=" + this.valor + ", vencimento=" + this.vencimento + "]";
	}
}
