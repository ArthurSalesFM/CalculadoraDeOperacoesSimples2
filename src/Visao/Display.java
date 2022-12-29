package Visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Modelo.Memoria;
import Modelo.MemoriaObservador;

//Herdando as informações do JPanel
@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{

	//criando uma label para exibir os numerios de entrada e saida dos resultados
	private final JLabel exibeEntradaSaidaDeDados;
	
	public Display() {
		
		Memoria.getInstancia().adicionarObservador(this);
		
		//Setando a cor de fundo
		setBackground(new Color(46, 49, 50));
		
		
		//setando 1234,56 na saida do label
		this.exibeEntradaSaidaDeDados = new JLabel(Memoria.getInstancia().getTextoAtual());
		
		//definindo a cor do texto
		this.exibeEntradaSaidaDeDados.setForeground(Color.WHITE);
		
		//definindo a fonte do texto
		this.exibeEntradaSaidaDeDados.setFont(new Font("courier",Font.PLAIN,30));
		
		//Criando layout para organizar o texto no display
		//alinhando a direita, espaço lateral, espaço horizontal
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));		
		
		//adicionando o label ao JPanel
		add(this.exibeEntradaSaidaDeDados);
		
	}

	@Override
	public void valorAlterado(String texto) {
		this.exibeEntradaSaidaDeDados.setText(texto);
	}
	
}
