abstract class Pessoa{
	protected String nome;
	protected String cpf;
	
	Pessoa(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
	}
	String getNome() {
		return this.nome;
	}
	String getCpf() {
		return this.cpf;
	}
	abstract void setNome(String nome);
	abstract void setCpf(String cpf);
}
class Professor extends Pessoa{
	
	private String formacao;
	private String area;
	
	Professor(String nome, String cpf, String formacao, String area) {
		super(nome, cpf);
		this.formacao = formacao;
		this.area = area;
	}
	public String getFormacao() {
		return this.formacao;
	}
	public String getArea() {
		return this.area;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	void setNome(String nome) {
		this.nome = nome;
		
	}
	@Override
	void setCpf(String cpf) {
		this.cpf = cpf;		
	}

}

class Aluno extends Pessoa{
	
	
	private String matricula;
	private String curso;
	Aluno(String nome, String cpf, String matricula,String curso) {
		super(nome, cpf);
		this.matricula = matricula;
		this.curso = curso;
	}
	public String getMatricula() {
		return this.matricula;
	}
	public String grtCurso() {
		return this.curso;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	@Override
	void setNome(String nome) {
		this.nome = nome;
		
	}
	@Override
	void setCpf(String cpf) {
		this.cpf = cpf;		
	}
	
}

interface Colecao{
	 void inserir(Pessoa p);
	 int tamanhoColecaoNaoNull();
}


	

class ColecaoVetor implements Colecao {
	Pessoa[] dados = new Pessoa[5];
	private int index = 0;
	@Override
	public void inserir(Pessoa p) {
		if(index < 5) {
			dados[index++]  = p;	
			
		}
		
	}

	@Override
	public int tamanhoColecaoNaoNull() {
		if(index > 0) {
			System.out.println(dados[index -1].getNome());
		}
		return dados.length - index;
	}


}
public class Q5 {
	
	public static void main(String[] args){
      System.out.println("test");
      ColecaoVetor pessoas = new ColecaoVetor();
      pessoas.inserir(new Professor("Alexis Leskinen","12456789000","Neurocientista","AI development"));
      System.out.println(pessoas.tamanhoColecaoNaoNull());
      pessoas.inserir(new Aluno("Carl Friedrich Gauss","124456789001","124456789001","Matemática"));
      System.out.println(pessoas.tamanhoColecaoNaoNull());
      pessoas.inserir(new Professor("Newton","123123123123","Cientista","Fisica"));
      System.out.println(pessoas.tamanhoColecaoNaoNull());
	}
}
