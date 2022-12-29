package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
			ZERAR, NUMERO, DIV, MULT, SUB, SOM, IGU, VIRG, SINAL;
	};
	
	//fazendo com que só seja instanciado apenas uma classe Memoria
	//impedindo outras instancias
	private static final Memoria instancia = new Memoria();
	private final List<MemoriaObservador> observadores = new ArrayList<>();
	
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	//construtor privado
	private Memoria() {	}
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObservador(MemoriaObservador observador) {
		this.observadores.add(observador);
	}

	public String getTextoAtual() {
		return this.textoAtual.isEmpty() ? "0" : this.textoAtual;
	}
	
	public void processarComando(String texto) {
		
		TipoComando tipoComando = detectarComando(texto);
		
		if(tipoComando == null) {
			return;
		}
		else if(tipoComando == TipoComando.ZERAR) {
			this.textoAtual = "";
			this.textoBuffer = "";
			this.substituir = false;
			this.ultimaOperacao = null;
		}				
		else if(tipoComando == TipoComando.SINAL && this.textoAtual.contains("-")) {
			this.textoAtual = this.textoAtual.substring(1);
		}		
		else if(tipoComando == TipoComando.SINAL && !this.textoAtual.contains("-")) {
			this.textoAtual = "-" + this.textoAtual;
		}		
		else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRG) {			
			this.textoAtual = this.substituir ? texto : this.textoAtual + texto;
			this.substituir = false;
		}
		else {
			this.substituir = true;			
			this.textoAtual = obterResultadoOperacao();
			this.textoBuffer = this.textoAtual;
			this.ultimaOperacao = tipoComando;		
		}		
		this.observadores.forEach(obs -> obs.valorAlterado(getTextoAtual()));				
	}

	private String obterResultadoOperacao() {
		
		if(this.ultimaOperacao == null 
				|| this.ultimaOperacao == TipoComando.IGU) {
			return this.textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(this.textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(this.textoAtual.replace(",", "."));		
		double resultado = 0;
		
		if(this.ultimaOperacao == TipoComando.SOM) {
			resultado = numeroBuffer + numeroAtual;
		}
		else if(this.ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}
		else if(this.ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		}
		else if(this.ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}
				
		String saida = Double.toString(resultado).replace(".", ",");
		boolean inteiro = saida.endsWith(",0");		
		return inteiro ? saida.replace(",0", "") : saida;
	}

	private TipoComando detectarComando(String texto) {
		
		if(this.textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		}
		catch(NumberFormatException e) {
			
			//quando não fo rnumero
			
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			}
			else if("/".equals(texto)) {
				return TipoComando.DIV;
			}
			else if("*".equals(texto)) {
				return TipoComando.MULT;
			}
			else if("+".equals(texto)) {
				return TipoComando.SOM;
			}
			else if("-".equals(texto)) {
				return TipoComando.SUB;
			}
			else if("±".equals(texto)) {
				return TipoComando.SINAL;
			}
			else if(",".equals(texto) && !this.textoAtual.contains(",")) {
				return TipoComando.VIRG;
			}
			else if("=".equals(texto)) {
				return TipoComando.IGU;
			}			
		}		
		return null;
	}
	
}
