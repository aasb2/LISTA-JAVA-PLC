class Conta{
	protected String id;
	protected String agencia;
	protected Senha senha;
	
	Conta(String id,String conta, Senha senha){
		this.senha = senha;
		this.id = id;
		this.senha = senha;
	}
	
	protected String getId() {
		return this.id;
	}
	protected String getAgencia(){
		return this.agencia;
	}
	protected Senha getSenha() {
		return this.senha;
	}
	protected String getSenhaNumerica() {
		return this.senha.getSenhaNumerica()	;
	}
	protected String getSenhaAlfanumerica(){
		return this.senha.getSenhaAlfanumerica();
	}
	protected void setSenha(Senha novaSenha) {
		this.senha = novaSenha;
		this.senha.setSenhaAlfanumerica(novaSenha.getSenhaAlfanumerica());
		this.senha.setSenhaNumerica(novaSenha.getSenhaNumerica());	
	}
	protected void setSenhaNumerica(String novaSenhaNumerica) {
		this.senha.setSenhaNumerica(novaSenhaNumerica);
	}
	protected void setSenhaAlfaNumerica(String novaSenhaAlfanumerica) {
		this.senha.setSenhaAlfanumerica(novaSenhaAlfanumerica);
	}
	protected void setId(String id) {
		this.id = id;
	}
	protected void setAgencia(String agencia) {
		this.agencia = agencia;
	}
}

class Senha{
	protected String senhaAlfanumerica;
	protected String senhaNumerica;
	
	Senha(String senhaAlfanumerica, String senhaNumerica){
		this.setSenhaAlfanumerica(senhaAlfanumerica);
		this.setSenhaNumerica(senhaNumerica);
	}
	
	protected String getSenhaAlfanumerica() {
		return this.senhaAlfanumerica;
	}
	protected void setSenhaAlfanumerica(String senhaAlfanumerica) {
		boolean isAlfanumerica = true;
		if(senhaAlfanumerica.length() <=0) {
			isAlfanumerica = false;
		}
		for(int i = 0; i < senhaAlfanumerica.length() && isAlfanumerica; i++) {
			if(!((senhaAlfanumerica.charAt(0) >= 'A' && senhaAlfanumerica.charAt(0) <= 'Z') || (senhaAlfanumerica.charAt(0) >= 'a' && senhaAlfanumerica.charAt(0) <= 'z')|| (senhaAlfanumerica.charAt(0) >= '0' && senhaAlfanumerica.charAt(0) <= '9'))) {
				isAlfanumerica = false;
			}
			
		}
		if(isAlfanumerica) {
			this.senhaAlfanumerica = senhaAlfanumerica;
		}
		
	}
	protected String getSenhaNumerica() {
		return this.senhaNumerica;
	}
	protected void setSenhaNumerica(String senhaNumerica) {
		boolean isNumerica = true;
		if(senhaNumerica.length()<=0) {
			isNumerica = false;
		}
		for(int i = 0; i < senhaAlfanumerica.length() && isNumerica; i++) {
			if(!(senhaNumerica.charAt(0) >= '0' && senhaNumerica.charAt(0) <= '9')) {
				isNumerica = false;
			}
			
		}
		if(isNumerica) {
			this.senhaNumerica = senhaNumerica;
		}
		
	}

}

public class Q4 {
	
	public static void main(String[] args){
        Senha senha = new Senha("TheSafestPasswordOnEarth12345","12345");
        Conta conta = new Conta("12345","777",senha);
        System.out.println(conta.getSenhaAlfanumerica());
        System.out.println(conta.getId());
        conta.setSenhaAlfaNumerica("QWERTY");
        System.out.println(conta.getSenhaAlfanumerica());
	}
}