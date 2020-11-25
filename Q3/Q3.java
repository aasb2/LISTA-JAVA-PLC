enum Genero{
	MASCULINO, FEMININO;
	
}
class Pessoa{
	private String nome;
	private int idade;
	private String grauDeEscolaridade;
	private String orientacaoSexual;
	private char sexoBiologico;
	Genero genero;
	Pessoa(){
		
	}
	Pessoa(String nome, int idade, String grauDeEscolaridade, String orientacaoSexual, char sexoBiologico, Genero genero){
		this.nome = nome;
		this.idade = idade;
		this.grauDeEscolaridade = grauDeEscolaridade;
		this.orientacaoSexual = orientacaoSexual;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getGrauDeEscolaridade() {
		return grauDeEscolaridade;
	}
	public void setGrauDeEscolaridade(String grauDeEscolaridade) {
		this.grauDeEscolaridade = grauDeEscolaridade;
	}
	public String getOrientacaoSexual() {
		return orientacaoSexual;
	}
	public char getSexo() {
		return sexoBiologico;
	}
	
	public void dormir() {
		System.out.println("dormindo...");
	}
	public void cagar() {
		System.out.println("cagando...");
	}
	public void comer() {
		System.out.println("comendo...");
	}
	public void estudar() {
		System.out.println("estudando...");
	}
	public void viver() {
		System.out.println("vivendo...");
	}
	public void sobreviver() {
		System.out.println("sobrevivendo...");
	}
	public void trabalhar() {
		System.out.println("trabalhando...");
	}
}


public class Q3 {
	public static void main(String[] args){
		Pessoa p1 = new Pessoa();
		p1 = new Pessoa("Lintaho",18,"Superior Incompleto","Hetero",'M',Genero.MASCULINO);
		p1.estudar();
		p1.comer();
		p1.dormir();
	}
}
