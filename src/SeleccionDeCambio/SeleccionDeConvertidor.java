package SeleccionDeCambio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SeleccionDeConvertidor extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mensajebienvenida;
	private JLabel pregunta;
	private JButton btndivisas;
	private JButton btntemperatura;
	
	public SeleccionDeConvertidor() {
		
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bienvenido");
		getContentPane().setBackground(new Color(5,25,51));
		
		//Mensaje de bienvenida
		
		mensajebienvenida= new JLabel("Bienvenido");
		mensajebienvenida.setBounds(190, 40, 400,30 );
		mensajebienvenida.setFont(new Font("Roboto", 1, 23));
		mensajebienvenida.setForeground(new Color(107,208,254));
		add(mensajebienvenida);
		
		//Mensaje de Pregunta
		
		pregunta= new JLabel("¿Qué deseas consultar?");
		pregunta.setBounds(110, 80, 400,60 );
		pregunta.setFont(new Font("Roboto", 1, 23));
		pregunta.setForeground(new Color(107,208,254));
		add(pregunta);
		 
		
		
		//Boton para divisas
		
		btndivisas = new JButton("Convertir Divisas");
		btndivisas.setBounds(100, 200, 300, 30);
		btndivisas.setBackground(new Color(0, 199, 111));
		btndivisas.setFont(new Font("Roboto", 1, 16));
		btndivisas.setForeground(new Color(255,255,255));
		btndivisas.addActionListener(this);
		add(btndivisas);
		
	}
	

	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()==btndivisas) {
			ConvertidorDivisas selectorDivisa1 = new ConvertidorDivisas();
			selectorDivisa1.setBounds(0, 0, 750, 530);
			selectorDivisa1.setVisible(true);
			selectorDivisa1.setResizable(false);
			selectorDivisa1.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		
		
		
	}
	
	public static void main(String[] args) {
		SeleccionDeConvertidor ventanaBienvenida = new SeleccionDeConvertidor();
		ventanaBienvenida.setBounds(0, 0, 500, 400);
		ventanaBienvenida.setVisible(true);
		ventanaBienvenida.setResizable(false);
		ventanaBienvenida.setLocationRelativeTo(null);			
	}

}
