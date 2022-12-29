package Visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import Modelo.Memoria;

//Herdando as informações de JPanel
@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{

	//Definindo as cores dos botoes
	private final Color CINZA_ESCURO = new Color(68, 68, 68);
	private final Color CINZA_CLARO = new Color(99, 99, 99);
	private final Color LARANJA = new Color(242, 163, 60);
	
	public Teclado() {
		//Setando a cor de fundo
		//setBackground(Color.RED);
		
		//criando uma gridBaglayout para organizar o taclado
		GridBagLayout layout = new GridBagLayout();		
		GridBagConstraints cons = new GridBagConstraints();
		
		//definindo o grid, 
		setLayout(layout);	

		//expandindo o GridBagLayout atravez do cons, horizontalmente
		cons.weightx = 1;
		//expandindo o GridBagLayout atravez do cons, verticalmente
		cons.weighty = 1;
		
		//expande os botões tanto na vertical, quanto na  horizontal
		cons.fill = GridBagConstraints.BOTH;
		//definindo a constraints antes de usa-las
		
		//largura do grid
		cons.gridwidth = 2;
		adicionarBotao("AC", CINZA_ESCURO,  cons, 0, 0);
		
		cons.gridwidth = 1;
		adicionarBotao("±", CINZA_ESCURO,  cons, 0, 2);
		adicionarBotao("/", LARANJA,  cons, 0, 3);
		//----------
			
		adicionarBotao("7", CINZA_CLARO,  cons, 1, 0);		
		adicionarBotao("8", CINZA_CLARO,  cons, 1, 1);		
		adicionarBotao("9", CINZA_CLARO,  cons, 1, 2);		
		adicionarBotao("*", LARANJA,  cons, 1, 3);
		//----------		
		
		adicionarBotao("4", CINZA_CLARO,  cons, 2, 0);
		adicionarBotao("5", CINZA_CLARO,  cons, 2, 1);
		adicionarBotao("6", CINZA_CLARO,  cons, 2, 2);
		adicionarBotao("-", LARANJA,  cons, 2, 3);
		//----------
		
		adicionarBotao("1", CINZA_CLARO,  cons, 3, 0);
		adicionarBotao("2", CINZA_CLARO,  cons, 3, 1);
		adicionarBotao("3", CINZA_CLARO,  cons, 3, 2);
		adicionarBotao("+", LARANJA,  cons, 3, 3);
		//----------
		cons.gridwidth = 2;
		adicionarBotao("0", CINZA_CLARO,  cons, 4, 0);
		
		cons.gridwidth = 1;
		adicionarBotao(",", CINZA_CLARO,  cons, 4, 2);
		adicionarBotao("=", LARANJA,  cons, 4, 3);
		//----------				
	}
	
	private void adicionarBotao(String texto, Color cor, GridBagConstraints cons, int posiY, int posiX) {
		cons.gridy = posiY;
		cons.gridx = posiX;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, cons);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() instanceof JButton) {
			JButton bt = (JButton) e.getSource();
			Memoria.getInstancia().processarComando(bt.getText());
			//this.t
		}	
	}
	
}
