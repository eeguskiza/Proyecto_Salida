package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


import javax.swing.*;

public class VentanaInicioSesion extends JFrame{
	JFrame frame;
	public Map<String, User> database = new HashMap<>();
	
	public VentanaInicioSesion() {
		this.setSize(970,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//-----INICIO DE SESION NULO PARA AGILIZAR PROGRAMACIÓN-----------
		database.put("",new User("UserPrueba", "PrubaYYaQueMasQuieres", 658902378, 110, "", ""));
		database.put("1",new User("UserPrueba2", "PuesSiHabiaOtroVaya", 658922578, 4440, "1", "1"));
		
		//-----PAGINA GENERAL (ESCOGER SI SIGN IN O SIGN UP) -----------------
		JPanel main = new JPanel(new FlowLayout());
		this.add(main, BorderLayout.CENTER);
		
		JPanel titulo = new JPanel(new FlowLayout());
		titulo.add(new JLabel("Bienvenido a SalDeFiesta..."));
		this.add(titulo, BorderLayout.NORTH);
		
		JButton bRegistro = new JButton("Regístrate");
		JButton bInicio = new JButton("Inicia Sesión");
		
		main.add(bInicio);
		main.add(bRegistro);
		
		//------ PAGINA SIGN UP ------------
		bRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signUp();
				setVisible(false);
			}});
		
		bInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signIn();
				setVisible(false);
			}
			
		});
	}
	
	private void signUp() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(970,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel main = new JPanel(new GridLayout(1,2));
		JPanel blanc = new JPanel();
		JPanel form = new JPanel(new GridLayout(6,2));
		
		blanc.add(new JLabel( new ImageIcon("relleno.jpg")));
		JTextField nombre = new JTextField();
		JTextField apellido = new JTextField();
		JTextField telefono = new JTextField();
		JTextField edad = new JTextField();
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		form.add(new JLabel("Nombre:")); form.add(nombre);
		form.add(new JLabel("Apellido:")); form.add(apellido);
		form.add(new JLabel("Telefono:")); form.add(telefono);
		form.add(new JLabel("Edad:")); form.add(edad);
		form.add(new JLabel("Username:")); form.add(username);
		form.add(new JLabel("Password:")); form.add(password);
		
		main.add(blanc);
		main.add(form);
		frame.add(main);
		
		JPanel pBotones = new JPanel(new FlowLayout());
		JButton bGuardarRegistro = new JButton("Regístrame");
		JButton bAtras = new JButton("Volver atras");
		pBotones.add(bAtras); pBotones.add(bGuardarRegistro);
		frame.add(pBotones, BorderLayout.SOUTH);
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				setVisible(true);
			}});
		
		bGuardarRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char passchar[] = password.getPassword();
				String passwordString = new String(passchar);
				//database.put(username.getText(), passwordString);
				User newUser = new User(nombre.getText(), apellido.getText(), Integer.parseInt(telefono.getText()), Integer.parseInt(edad.getText()), username.getText(), passwordString);
				database.put(newUser.getUsername(), newUser);
				System.out.println("Usuario: "+username.getText()+". Contraseña: "+passwordString);
				frame.dispose();
				setVisible(true);
				
			}});
		
	}

	private void signIn() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(970,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel main = new JPanel(new GridLayout(1,2));
		JPanel blanc = new JPanel(new FlowLayout());
		JPanel form = new JPanel(new GridLayout(2,2));
		
		//blanc.add(new JLabel( new ImageIcon("relleno2.png")));
		
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		form.add(new JLabel("Username:")); form.add(username);
		form.add(new JLabel("Password:")); form.add(password);
		
		
		main.add(blanc);
		main.add(form);
		frame.add(main);
		
		JPanel pBotones = new JPanel(new FlowLayout());
		JButton bIniciarSesion = new JButton("Log In");
		JButton bAtras = new JButton("Volver atras");
		pBotones.add(bAtras); pBotones.add(bIniciarSesion);
		frame.add(pBotones, BorderLayout.SOUTH);
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				setVisible(true);
			}});
		
		bIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passChar = password.getPassword();
				String passWordString =new String(passChar);
				if(database.containsKey(username.getText())) {
					User usuario = database.get(username.getText());
					if(usuario.getPassword().equals(passWordString)) {
						System.out.println("Inicio de Sesion CORRECTO!");
						frame.dispose();
						dispose();
						Inicio inicio = new Inicio(usuario, VentanaInicioSesion.this);
						inicio.setVisible(true);
					}else {
						System.out.println("Contraseña incorrecta o usuario no existente");
						System.out.println(database.get(username.getText()) + passWordString);
					}
				}
			}
			
		});
	}

}
