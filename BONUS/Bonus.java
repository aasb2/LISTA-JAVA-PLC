
interface Expression{
	String avaliar(String expr);
	String imprimirArvore(String expr);
}

class ExprAritmetica implements Expression{
	String expr;
	
	ExprAritmetica(String expr){
		this.expr = expr;
	}
	
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	private int findOp(String expr, char op) {
		for(int i = expr.length() -1; i>= 0; i--){
			if(expr.charAt(i) == op) {
				return i;
			}
		}
		return -1;
	}

	private int positionToSplit(String expr) {
		int posOp = findOp(expr,'+');
		if(posOp == -1) {
			posOp = findOp(expr,'-');
			if(posOp == -1) {
				posOp = findOp(expr, '*');
				if(posOp == -1) {
					posOp = findOp(expr,'/');
				}
			}
		}
		return posOp;
	}
	private String evaluate(String expr1, char op, String expr2) {
		int posOp1 = positionToSplit(expr1), posOp2 = positionToSplit(expr2);
		if(posOp1 == -1 && posOp2 == -1) {
			double n1 = Double.parseDouble(expr1), n2 = Double.parseDouble(expr2);
			if(op == '+') {
				return Double.toString(n1 + n2);
			}
			else if(op == '-') {
				return Double.toString(n1 - n2);
			}
			else if(op == '*'){
				return Double.toString(n1 * n2);
			}
			else if(op == '/'){
				return Double.toString(n1/n2);
			}
		}
		String newExpr1 = expr1,newExpr2 = expr2;
		
		if(posOp1 != -1){ 
			newExpr1 = evaluate(expr1.substring(0,posOp1),expr1.charAt(posOp1),expr1.substring(posOp1+1,expr1.length()));
        }
		if(posOp2 != -1) {
            newExpr2 = evaluate(expr2.substring(0,posOp2),expr2.charAt(posOp2),expr2.substring(posOp2+1,expr2.length()));

		}
		return evaluate(newExpr1,op,newExpr2) ;
	}		
	@Override
	public String avaliar(String expr) {
		int posOp = positionToSplit(expr);
		if(posOp == -1) {
			return expr;
		}
		char op = expr.charAt(posOp);
		String expr1 = expr.substring(0,posOp);
		String expr2 = expr.substring(posOp+1,expr.length());
		String tree = evaluate(expr1,op,expr2);
		return tree;
	}
	
	
	
	private String printTree(String expr1,char op, String expr2) {
		int posOp1 = positionToSplit(expr1), posOp2 = positionToSplit(expr2);
		if(posOp1 == -1 && posOp2 == -1) {
			if(op == '+')
				return "(" + expr1 + ")" + "P" + "(" + expr2 +")";//PLUS
			else if(op == '-') 
					return "(" + expr1 + ")" + "M" + "(" + expr2 +")" ;	//MINUS
			else if(op == '/') 
				return "(" + expr1 + ")" + "D" + "(" + expr2 +")" ;	//DIVIDE
			else if(op == '*') 
				return "(" + expr1 + ")" + "T" + "(" + expr2 +")"; //TIMES
			
		}
		String newExpr1 = expr1,newExpr2 = expr2;
		if(posOp1 != -1){ 
			newExpr1 = printTree(expr1.substring(0,posOp1),expr1.charAt(posOp1),expr1.substring(posOp1+1,expr1.length()));
        }
		if(posOp2 != -1) {
            newExpr2 = printTree(expr2.substring(0,posOp2),expr2.charAt(posOp2),expr2.substring(posOp2+1,expr2.length()));
		}
		return printTree(newExpr1,op,newExpr2);
		
	}
	@Override
	public String imprimirArvore(String expr) {
		int posOp = positionToSplit(expr);
		char op = 'F';
		if(posOp!=-1)
			op = expr.charAt(posOp);
		else if(posOp == -1) {
			return "(" + expr + ")";
		}
		String expr1 = expr.substring(0,posOp);
		String expr2 = expr.substring(posOp+1,expr.length());
		if(positionToSplit(expr1) == -1 && positionToSplit(expr2) == -1)
			return "(" + "("+expr1+")" + op + "("+expr2+")" + ")";
		String tree = printTree(expr1,op,expr2);
		tree = tree.replaceAll("P", "+");
		tree = tree.replaceAll("M", "-");
		tree = tree.replaceAll("D", "/");
		tree = tree.replaceAll("T", "*");

		return "(" + tree +")";
				
				
				
	}
	public String toString() {
		return this.expr;
	}
}

class ExprLogica implements Expression{
	
	String expr;
	
	ExprLogica(String expr){
		this.expr = expr;
	}
	public String getExpr() {
		return this.expr;
	}
	public void setExpr(String expr){
		this.expr = expr;
	}
	private int findOp(String expr) {
		int pos = -1;
		for(int i = 0; i<expr.length(); i++) {
			if(expr.charAt(i) == '=' || expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '!' ) {
				return i;
			}				
		}
		return pos;
	}
	
	private String returnOp(String expr) {
		int pos = findOp(expr);
		char op = expr.charAt(pos);
		char op1 = expr.charAt(pos+1);
		if(op == '=' && op1 == '=') {
			return "==";
		}
		else if(op == '!'&& op1 == '=') {
			return "!=";
		}
		else if(op == '>') {
			if(op1 == '=') {
				return ">=";
			}
			return ">";
		}
		else if(op == '<') {
			if(op1 == '=') {
				return ">=";
			}
			return "<";
		}
		return "";
	}
	@Override
	public String avaliar(String expr) {
		int posOp = findOp(expr);
		String op = returnOp(expr);
		int opLen = op.length();
		ExprAritmetica expr1 = new ExprAritmetica(expr.substring(0,posOp));
		ExprAritmetica expr2 = new ExprAritmetica(expr.substring(posOp + opLen, expr.length()));
		Double result1 = Double.parseDouble(expr1.avaliar(expr1.getExpr()).toString().toString());
		Double result2 = Double.parseDouble(expr2.avaliar(expr2.getExpr()).toString());
		boolean finalResult = true;
		if(op == "==") {
			finalResult = Double.compare(result1, result2) == 0? true : false;
		}
		else if(op == "!=")
			finalResult = Double.compare(result1, result2) == 0 ? false : true;
		else if(op == ">=")
			finalResult = result1 >= result2;
		else if(op == "<=")
			finalResult = result1 <= result2;
		else if(op == "<")
			finalResult = result1 < result2;
		else if(op == ">")
			finalResult = result1 > result2;
		return finalResult? "True":"False";
	}

	@Override
	public String imprimirArvore(String expr) {
		int posOp = findOp(expr);
		String op = returnOp(expr);
		int opLen = op.length();
		ExprAritmetica expr1 = new ExprAritmetica(expr.substring(0,posOp));
		ExprAritmetica expr2 = new ExprAritmetica(expr.substring(posOp + opLen, expr.length()));
		String result1 = expr1.imprimirArvore(expr1.getExpr());
		String result2 = expr2.imprimirArvore(expr2.getExpr());
		return "(" +result1+op+result2 +")";
	}
	
	public String toString() {
		return this.expr;
	}
	

}




class ExprOpTernario implements Expression{

	private String expr;
	ExprOpTernario(String expr){
		this.expr = expr;
	}
	
	private int find(String str,char character,boolean reverse){
		if(!reverse) {
			for(int i =0; i<str.length(); i++) {
				if(str.charAt(i) == character)
					return i;
			}
		}
		else {
			for(int i =str.length()-1; i>=0; i--) {
				if(str.charAt(i) == character)
					return i;
			}
		}
		return -1;
	}
	@Override
	public String avaliar(String expr) {
		int posIf = find(expr,'?',false);
		int posElse = find(expr,':',true);
		
		String exprLogica_str = expr.substring(0,posIf);
		String str_expr1 = expr.substring(posIf+1,posElse);
		String str_expr2 = expr.substring(posElse+1,expr.length());
		ExprLogica exprLogica = new ExprLogica(exprLogica_str);
		boolean boolExprLogica = exprLogica.avaliar(exprLogica_str) == "True";
		String result = boolExprLogica? str_expr1 : str_expr2;
		//Resolve o caso que a expressão após ? é ternária, mas não após o :
		if(find(str_expr1,'?',false) == -1 && find(str_expr1,':',true) == -1) {
			Expression expr1 = descobreTipoDeExpr(str_expr1) == 'A' ? new ExprAritmetica(result): new ExprLogica(result);
			return expr1.avaliar(result);
		}
		
		
		return avaliar(result);
	}
	private String printTree(String expr) {
		int posIf = find(expr,'?',false);
		int posElse = find(expr,':',true);
		
		String exprLogica_str = expr.substring(0,posIf);
		String str_expr1 = expr.substring(posIf+1,posElse);
		String str_expr2 = expr.substring(posElse+1,expr.length());
		ExprLogica exprLogica = new ExprLogica(exprLogica_str);
		Expression expr2 = descobreTipoDeExpr(str_expr2) == 'A' ? new ExprAritmetica(str_expr2): new ExprLogica(str_expr2);
		String exprLogicaTree = exprLogica.imprimirArvore(exprLogica_str);
		exprLogicaTree = exprLogicaTree.substring(1,exprLogicaTree.length()-1);
	
		//Resolve o caso que a expressão após ? é ternária, mas não após o :
		if(find(str_expr1,'?',false) == -1 && find(str_expr1,':',true) == -1) {
			Expression expr1 = descobreTipoDeExpr(str_expr1) == 'A' ? new ExprAritmetica(str_expr1): new ExprLogica(str_expr1);
			
			return exprLogicaTree+  "I" + expr1.imprimirArvore(str_expr1) + "E" + expr2.imprimirArvore(str_expr2) ;
			
		}
		
		return "("+ exprLogica.imprimirArvore(exprLogica_str) + "I" + printTree(str_expr1) + "E" +  expr2.imprimirArvore(str_expr2)+")";
	}

	@Override
	public String imprimirArvore(String expr) {
		
		String result = printTree(expr);
		result = result.replaceAll("I", "?");
		result = result.replaceAll("E", ":");
		return result;
	}

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}
	public String toString() {
		return this.expr;
	}
	
	private char descobreTipoDeExpr(String expr) {
		for(int i = 0; i < expr.length(); i++) {
			if(expr.charAt(i) == '=' || expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '!' ) {
				return 'L';
			}	
		}
		return 'A';
	}
}




public class Bonus {
		
	public static void main(String[] args){
		String expr ="2*3+4/6!=3/4?96+45*3:26*7*6/4+3";
		descobreTipoDeExpr(expr);
		ExprOpTernario exprTernario= new ExprOpTernario(expr);
		System.out.println(exprTernario.avaliar(exprTernario.getExpr()));
		System.out.println(exprTernario.imprimirArvore(exprTernario.getExpr()));
	}
	public static char descobreTipoDeExpr(String expr) {
		for(int i = 0; i < expr.length(); i++) {
			if(expr.charAt(i) == '?' || expr.charAt(i) == ':' ) {
				return 'T';
			}	
		}
		for(int i = 0; i < expr.length(); i++) {
			if(expr.charAt(i) == '=' || expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '!' ) {
				return 'L';
			}	
		}
		return 'A';
	}

}