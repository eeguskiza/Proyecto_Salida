package app;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Inicio extends JFrame{
	
	public Inicio(User user, VentanaInicioSesion vIS) {
		this.setSize(970,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		JPanel welcomeFrase = new JPanel(new FlowLayout());
		welcomeFrase.add(new JLabel("Bienvenido, "+ user.getNombre() +"!"));
		this.add(welcomeFrase, BorderLayout.NORTH);

		//acabo de editar esto
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		
		JButton bAjustes = new JButton("Ajustes");
		bAjustes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajustes(user);
				setVisible(false);
			}});
		mainPanel.add(bAjustes);
		
		JButton bCerrarSesion = new JButton("Cerrar Sesion");
		bCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				vIS.setVisible(true);
			}});
		mainPanel.add(bCerrarSesion);
		
	}
	
	private void ajustes(User user) {
		JFrame frame = new JFrame();
		frame.setSize(970,600);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JPanel main = new JPanel(new GridLayout(1,3)); //esto es para añadir columnas/filas para mas cosas de ajustes
		frame.add(main);
		
		JPanel navegacionSuperior = new JPanel(new FlowLayout());
		frame.add(navegacionSuperior, BorderLayout.NORTH);
		JButton bAtras = new JButton("<- Atras"); navegacionSuperior.add(bAtras);
		
		JPanel pEditarDatos = new JPanel(new GridLayout(14,1));
		
		JTextField nombre = new JTextField(user.getNombre());
		JTextField apellido = new JTextField(user.getApellido());
		JTextField telefono = new JTextField(Integer.toString(user.getTelefono()));
		JTextField edad = new JTextField(Integer.toString(user.getEdad()));
		JTextField username = new JTextField(user.getUsername());
		JPasswordField password = new JPasswordField(user.getPassword());
		
		pEditarDatos.add(new JLabel("TUS DATOS PERSONALES:"));
		pEditarDatos.add(new JLabel("Nombre:")); pEditarDatos.add(nombre);
		pEditarDatos.add(new JLabel("Apellido:")); pEditarDatos.add(apellido);
		pEditarDatos.add(new JLabel("Telefono:")); pEditarDatos.add(telefono);
		pEditarDatos.add(new JLabel("Edad:")); pEditarDatos.add(edad);
		pEditarDatos.add(new JLabel("Username:")); pEditarDatos.add(username);
		pEditarDatos.add(new JLabel("Password:")); pEditarDatos.add(password);
		
		JPanel pBotonDatosUsuario = new JPanel(new FlowLayout());
		JButton bMostrarContra = new JButton("Mostrar Contraseña");
		pBotonDatosUsuario.add(bMostrarContra);
		JButton bGuardarDatosUsuario = new JButton("Actualizar Datos");
		pBotonDatosUsuario.add(bGuardarDatosUsuario);
		pEditarDatos.add(pBotonDatosUsuario);
		
		JPanel blanc = new JPanel();
		JPanel blanc2 = new JPanel();
		main.add(pEditarDatos);
		main.add(blanc);
		main.add(blanc2);
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				setVisible(true);
			}});
		
		bGuardarDatosUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passchar = password.getPassword();
				String passString = new String(passchar);
				user.setNombre(nombre.getText());
				user.setApellido(apellido.getText());
				user.setTelefono(Integer.parseInt(telefono.getText()));
				user.setEdad(Integer.parseInt(edad.getText()));
				user.setPassword(passString);
				user.setUsername(username.getText());
				System.out.println("Datos Actualizados!");
				
			}});
		
		bMostrarContra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				password.setEchoChar('\0');
			}});
	}
}
