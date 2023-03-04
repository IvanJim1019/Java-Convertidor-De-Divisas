package SeleccionDeCambio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;


public class ConvertidorDivisas extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	/**
	 * Version 1
	 */
	private JComboBox<String> divisa1;
	private JComboBox<String> divisa2;
	private JLabel etiquetaDivisa1;
	private JLabel etiquetaDivisa2;
	private JLabel etiquetaTipoDeCambio;
	private JLabel resultadoDeConversion1;
	private JLabel resultadoDeConversion;
	private JTextField cantidadAConvertir;
	private JLabel ingreseCantidad;
	private JButton btnConvertir;
	private JButton btnCerrar;
	private JButton btnRegresar;
	private JButton btnNuevoCalculo;
	private double etiquetaFactorConversion;
	private JTextArea acumulacionDeConsultas;
	private JScrollPane scrollpanel;
	private String textoacumulado = "";
	private int contadorDeConsultas=0;
	
	
	
	
	//Selector divisa1
	public ConvertidorDivisas() {
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(5,25,51));
		setTitle("Conversion de divisas");
		
		
		
		etiquetaDivisa1= new JLabel("Elige las divisas que deseas convertir");
		etiquetaDivisa1.setBounds(25, 20, 300,30 );
		etiquetaDivisa1.setFont(new Font("Roboto", 1, 16));
		etiquetaDivisa1.setForeground(new Color(107,208,254));
		add(etiquetaDivisa1);
		
		divisa1 = new JComboBox<String>();
		divisa1.setBounds(60, 75, 60, 20);
		add(divisa1);
		
		
		divisa1.addItem("MXN");//Peso Mexicano
		divisa1.addItem("USD");//Dolar
		divisa1.addItem("EUR");//Euro
		divisa1.addItem("GBP");//Libra Esterlina
		divisa1.addItem("JPY");//Yen Japones
		divisa1.addItem("KRW");//Won Sur Coreano
		divisa1.addItemListener(this);
		
		//Selector Divisa2
		
		etiquetaDivisa2= new JLabel("DE                    A");
		etiquetaDivisa2.setBounds(25, 68, 350,30 );
		etiquetaDivisa2.setFont(new Font("Roboto", 1, 16));
		etiquetaDivisa2.setForeground(new Color(107,208,254));
		add(etiquetaDivisa2);
		
		divisa2 = new JComboBox<String>();
		divisa2.setBounds(170, 75, 60, 20);
		add(divisa2);
		
		
		divisa2.addItem("MXN");//Peso Mexicano
		divisa2.addItem("USD");//Dolar
		divisa2.addItem("EUR");//Euro
		divisa2.addItem("GBP");//Libra Esterlina
		divisa2.addItem("JPY");//Yen Japones
		divisa2.addItem("KRW");//Won Sur Coreano
		divisa2.addItemListener(this);
		
		//
		cantidadAConvertir = new JTextField("0");
		
		cantidadAConvertir.setBounds(25, 160, 170, 20);
		add(cantidadAConvertir);
		
		//Etiqueta de tipo de cambio
		ingreseCantidad= new JLabel("Ingrese la cantidad a convertir ");
		ingreseCantidad.setBounds(25, 115, 300,30 );
		ingreseCantidad.setFont(new Font("Roboto", 1, 16));
		ingreseCantidad.setForeground(new Color(107,208,254));
		add(ingreseCantidad);
		
		
		//Boton para Convertir Divisas
		
		btnConvertir = new JButton("Convertir Divisas");
		btnConvertir.setBounds(500, 40, 200, 30);
		btnConvertir.setBackground(new Color(0, 199, 111));
		btnConvertir.setFont(new Font("Roboto", 1, 16));
		btnConvertir.setForeground(new Color(255,255,255));
		btnConvertir.addActionListener(this);
		add(btnConvertir);
		
		//Boton para limpiar campos
		
		btnNuevoCalculo = new JButton("Nuevo Calculo");
		btnNuevoCalculo.setBounds(500, 100, 200, 30);
		btnNuevoCalculo.setBackground(new Color(0, 199, 111));
		btnNuevoCalculo.setFont(new Font("Roboto", 1, 16));
		btnNuevoCalculo.setForeground(new Color(255,255,255));
		btnNuevoCalculo.addActionListener(this);
		add(btnNuevoCalculo);
		
		
		//Etiqueta de tipo de cambio
		etiquetaTipoDeCambio= new JLabel("Tipo de cambio es ");
		etiquetaTipoDeCambio.setBounds(25, 200, 400,30 );
		etiquetaTipoDeCambio.setFont(new Font("Roboto", 1, 16));
		etiquetaTipoDeCambio.setForeground(new Color(107,208,254));
		add(etiquetaTipoDeCambio);
		
		
		//
		resultadoDeConversion1= new JLabel("El total de tu conversión es  ");
		resultadoDeConversion1.setBounds(25, 240, 400,30 );
		resultadoDeConversion1.setFont(new Font("Roboto", 1, 16));
		resultadoDeConversion1.setForeground(new Color(107,208,254));
		add(resultadoDeConversion1);
		
		//
		resultadoDeConversion= new JLabel("");
		add(resultadoDeConversion);
		
		
		acumulacionDeConsultas = new JTextArea();
		scrollpanel = new JScrollPane(acumulacionDeConsultas);
		scrollpanel.setBounds(15, 300, 450,175);
		add(scrollpanel);
		
		
		
		
		//Boton para Convertir Divisas
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(500, 350, 200, 30);
		btnCerrar.setBackground(new Color(0, 199, 111));
		btnCerrar.setFont(new Font("Roboto", 1, 16));
		btnCerrar.setForeground(new Color(255,255,255));
		btnCerrar.addActionListener(this);
		add(btnCerrar);
		
		//Boton para Convertir Divisas
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(500, 300, 200, 30);
		btnRegresar.setBackground(new Color(0, 199, 111));
		btnRegresar.setFont(new Font("Roboto", 1, 16));
		btnRegresar.setForeground(new Color(255,255,255));
		btnRegresar.addActionListener(this);
		add(btnRegresar);
		
	}
	
	
	
	
	
	//Delimitador de decimales
	public class LimitarDecimales {

	    public static String limitarDecimales(double numero) {
	        DecimalFormat df = new DecimalFormat("#.####");
	        return df.format(numero);
	    }
	}
	
	//--
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==divisa1) {	
		} 
		if (e.getSource()==divisa2) {
				
		}
		
	}
	
	//Eventos 
	public void actionPerformed(ActionEvent e) {
	
		//BTN convertir divisa
		if (e.getSource()==btnConvertir) {
			
			
			
			String selecionDivisa1 = divisa1.getSelectedItem().toString();
			String selecionDivisa2 = divisa2.getSelectedItem().toString();
			String textoParaConvertir = cantidadAConvertir.getText().trim();
			
						
			//Variable local para convertir String a Double	
			Double valorAConvertir = Double.parseDouble(textoParaConvertir);
			
			//Validaciones
			if(selecionDivisa1 == selecionDivisa2) {
				JOptionPane.showMessageDialog(null,"Debes elegir divisas diferentes");
			}
			else if (textoParaConvertir.equals("")) {
				JOptionPane.showMessageDialog(null,"Debes ingresar una cantidad");
			} 
			else if (!textoParaConvertir.matches("[0-9]*.[0-9]*")) {
				JOptionPane.showMessageDialog(null,"Ingresa Numeros");
			}
			else if (valorAConvertir<=0) {
				JOptionPane.showMessageDialog(null,"Ingresa una cantidad mayor a 0");
			}else {
				//Inicio de logica
				 try {
					 
					 contadorDeConsultas++;
						String textoContadorDeConsultas = String.valueOf(contadorDeConsultas);
					 
					 
					 if(selecionDivisa1=="USD") {
						 
						// Llamada a la API para obtener la tasa de cambio
				            String urlString = "https://openexchangerates.org/api/latest.json?app_id=ac2b985ee42348c5ace4718fca5acee7&base=USD&symbols=" 
						+ selecionDivisa2;
				            URL url = new URL(urlString);
				            HttpURLConnection con = (HttpURLConnection) url.openConnection();
				            con.setRequestMethod("GET");
				            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				            String inputLine;
				            StringBuffer content = new StringBuffer();
				            while ((inputLine = in.readLine()) != null) {
				                content.append(inputLine);
				            }
				            in.close();
				            con.disconnect();
				            
				            // Analiss de respuesta JSON y obtener la tasa de cambio
				            String json = content.toString();
				            int start = json.indexOf(selecionDivisa2) + 5;
				            int end = json.indexOf("}", start);
				            String exchangeRateString = json.substring(start, end);
				            double exchangeRate = Double.parseDouble(exchangeRateString);
				            
				            double totalConversion = exchangeRate*valorAConvertir;
				            
				            String textoTipoCambio = LimitarDecimales.limitarDecimales(exchangeRate);
				            String textoTotalConversion =LimitarDecimales.limitarDecimales(totalConversion);
				                   
				            etiquetaTipoDeCambio.setText("Tipo de cambio es 1 "+ selecionDivisa1 +" = "+ textoTipoCambio +" " +selecionDivisa2  );
				            resultadoDeConversion1.setText("El total de tu conversión es:  "+textoTotalConversion +" "+ selecionDivisa2);
				            
				            //----
				        	
				            resultadoDeConversion.setText("     Esta es tu consulta "+textoContadorDeConsultas +"\n"+"     El tipo de cambio de 1 "
				            + selecionDivisa1 +" es igual a "+ textoTipoCambio +" "+ selecionDivisa2 + "\n"+"     al convertir "+ valorAConvertir + " "
				            +selecionDivisa1+" tu total es de: " +textoTotalConversion+" "+selecionDivisa2);
				    		textoacumulado += resultadoDeConversion.getText()+"\n"+"\n";
				            
				            acumulacionDeConsultas.setText(textoacumulado);
				           
				            
						 
						 }else if(selecionDivisa2=="USD") {
							 
							// Llamada a la API para obtener la tasa de cambio
					            String urlString = "https://openexchangerates.org/api/latest.json?app_id=ac2b985ee42348c5ace4718fca5acee7&base=USD&symbols=" 
							+ selecionDivisa1;
					            URL url = new URL(urlString);
					            HttpURLConnection con = (HttpURLConnection) url.openConnection();
					            con.setRequestMethod("GET");
					            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					            String inputLine;
					            StringBuffer content = new StringBuffer();
					            while ((inputLine = in.readLine()) != null) {
					                content.append(inputLine);
					            }
					            in.close();
					            con.disconnect();
					            
					            // Analiss de respuesta JSON y obtener la tasa de cambio
					            String json = content.toString();
					            int start = json.indexOf(selecionDivisa1) + 5;
					            int end = json.indexOf("}", start);
					            String exchangeRateString = json.substring(start, end);
					            double exchangeRate = Double.parseDouble(exchangeRateString);
					            double tipoDeCambioInverso = 1/exchangeRate;
					            double totalConversion = tipoDeCambioInverso*valorAConvertir;
					            
					            String textoTipoCambio = LimitarDecimales.limitarDecimales(tipoDeCambioInverso);
					            String textoTotalConversion =LimitarDecimales.limitarDecimales(totalConversion);
					                   
					            etiquetaTipoDeCambio.setText("Tipo de cambio es 1 "+ selecionDivisa1 +" = "+ textoTipoCambio +" " +selecionDivisa2  );
					            resultadoDeConversion1.setText("El total de tu conversión es:  "+textoTotalConversion +" "+ selecionDivisa1);
					            
					            //----
					        	
					            resultadoDeConversion.setText("     Esta es tu consulta "+textoContadorDeConsultas +"\n"+"     El tipo de cambio de 1 "
					            + selecionDivisa1 +" es igual a "+ textoTipoCambio +" "+ selecionDivisa2 + "\n"+"     al convertir "+ valorAConvertir + " "
					            +selecionDivisa1+" tu total es de: " +textoTotalConversion+" "+selecionDivisa2);
					    		textoacumulado += resultadoDeConversion.getText()+"\n"+"\n";
					            
					            acumulacionDeConsultas.setText(textoacumulado);
						 }else {
							   // hacer la llamada a la API para obtener la tasa de cambio
					            String urlString1 = "https://openexchangerates.org/api/latest.json?app_id=ac2b985ee42348c5ace4718fca5acee7&base=USD&symbols=" + selecionDivisa1;
					            URL url1 = new URL(urlString1);
					            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
					            con1.setRequestMethod("GET");
					            BufferedReader in1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
					            String inputLine1;
					            StringBuffer content1 = new StringBuffer();
					            while ((inputLine1 = in1.readLine()) != null) {
					                content1.append(inputLine1);
					            }
					            in1.close();
					            con1.disconnect();
					            
					            // analizar la respuesta JSON y obtener la tasa de cambio
					            String json1 = content1.toString();
					            int start1 = json1.indexOf(selecionDivisa1) + 5;
					            int end1 = json1.indexOf("}", start1);
					            String exchangeRateString1 = json1.substring(start1, end1);
					            double exchangeRate4 = Double.parseDouble(exchangeRateString1);
					            
					           
					            //--------------------------------------------------
					            
					         // hacer la llamada a la API para obtener la tasa de cambio
					            String urlString2 = "https://openexchangerates.org/api/latest.json?app_id=ac2b985ee42348c5ace4718fca5acee7&base=USD&symbols=" + selecionDivisa2;
					            URL url2 = new URL(urlString2);
					            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
					            con2.setRequestMethod("GET");
					            BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
					            String inputLine2;
					            StringBuffer content2 = new StringBuffer();
					            while ((inputLine2 = in2.readLine()) != null) {
					                content2.append(inputLine2);
					            }
					            in2.close();
					            con2.disconnect();
					            
					            // analizar la respuesta JSON y obtener la tasa de cambio
					            String json2 = content2.toString();
					            int start2 = json2.indexOf(selecionDivisa2) + 5;
					            int end2 = json2.indexOf("}", start2);
					            String exchangeRateString2 = json2.substring(start2, end2);
					            double exchangeRate5 = Double.parseDouble(exchangeRateString2);
					            
					         
					            //--------------------------------------------------
					            
					            etiquetaFactorConversion = exchangeRate5/exchangeRate4;
					            			            
					           
					            
					            double totalConversion2 = etiquetaFactorConversion*valorAConvertir;
					            
					            String textoTipoCambio = LimitarDecimales.limitarDecimales(etiquetaFactorConversion);
					            String cuatroDecimales2 =LimitarDecimales.limitarDecimales(totalConversion2);
					            
					            
					            
					            etiquetaTipoDeCambio.setText("Tipo de cambio es 1 "+ selecionDivisa1 +" = "+ textoTipoCambio +" " +selecionDivisa2  );
					            resultadoDeConversion1.setText("El total de tu conversión es:  "+ cuatroDecimales2 +" "+ selecionDivisa2);
					            
					            
					            //---
					            					            
					            resultadoDeConversion.setText("     Esta es tu consulta "+textoContadorDeConsultas +"\n"+ "     El tipo de cambio de 1 "+ selecionDivisa1 +" es igual a "+ textoTipoCambio +" "+ selecionDivisa2 + "\n"+"     al convertir "+ valorAConvertir + " "+selecionDivisa1+" tu total es de: " +cuatroDecimales2+" "+selecionDivisa2);
					    		textoacumulado += resultadoDeConversion.getText()+"\n"+"\n";
					    		acumulacionDeConsultas.setText(textoacumulado);					    		
						 }
			        } catch (Exception evn) {
			            System.out.println("Error: " + evn.getMessage());
			        }
			}
			
		}
		//Funcion BTN para limpiar
		if(e.getSource()==btnNuevoCalculo) {
		divisa1.setSelectedIndex(0);
		divisa2.setSelectedIndex(0);
		cantidadAConvertir.setText("0");
		etiquetaTipoDeCambio.setText("Tipo de cambio es ");
		resultadoDeConversion1.setText("El total de tu conversión es  ");
		}
		//Funcion BTN Regresar al menú Seleccion
		if(e.getSource()==btnRegresar) {
			SeleccionDeConvertidor ventanaBienvenida = new SeleccionDeConvertidor();
			ventanaBienvenida.setBounds(0, 0, 500, 400);
			ventanaBienvenida.setVisible(true);
			ventanaBienvenida.setResizable(false);
			ventanaBienvenida.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		//Funcion BTN cerrar
		if (e.getSource()==btnCerrar) {
			System.exit(0);
		}	
}
	
	public static void main(String[] args) {
		ConvertidorDivisas selectorDivisa1 = new ConvertidorDivisas();
		selectorDivisa1.setBounds(0, 0, 750, 530);
		selectorDivisa1.setVisible(true);
		selectorDivisa1.setResizable(false);
		selectorDivisa1.setLocationRelativeTo(null);

		
       
		
	}
	
	
	}
	
	
	
	
	
	

