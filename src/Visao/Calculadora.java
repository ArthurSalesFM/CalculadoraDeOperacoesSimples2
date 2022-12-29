package Visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

//Herda de JFrame para criação da janela
@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	//Construtor da classe
	public Calculadora() {
		
		this.setTitle("Calc Art");
		organizarLayout();
	
		//Definindo o tamanho da tela, definindo (Largura, Altura)
		setSize(232, 322);
		
		//setUndecorated(true);
		
		//Encerra o processo caso precione o botao fechar
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Centralizando a janela no meio da tela
		setLocationRelativeTo(null);
		
		//Deixando a tela visível
		setVisible(true);
		
		setResizable(false);
	}	
	
	private void organizarLayout() {
		//cirando uma nova BorderLayout para organizar os JPanel´s
		setLayout(new BorderLayout());
		
		Display display = new Display();
		//definindo o tamanho do display(largura, altura)
		display.setPreferredSize(new Dimension(233, 60));
		
		//Adicionando display na tela, porém no norte(para de cima)
		add(display, BorderLayout.NORTH);
		
		Teclado teclado = new Teclado();
		//Adicionando display na tela, porém no centro(para central)
		add(teclado, BorderLayout.CENTER);		
	}
	
}
