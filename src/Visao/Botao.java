package Visao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

//herdando as informações de JButton
@SuppressWarnings("serial")
public class Botao extends JButton{

	public Botao(String texto, Color cor) {
		//setando o texto no botão
		setText(texto);
		
		//setando a fonte do texto
		setFont(new Font("courier",Font.PLAIN,20));
		
		//para realizar a pintura do botão, coloca-se true
		setOpaque(true);
		
		//setando a cor
		setBackground(cor);
		
		setForeground(Color.WHITE);
		
		//definindo as linhas de bordas dos botoes
		setBorder(BorderFactory.createLineBorder(Color.BLACK));			
	}	
}
