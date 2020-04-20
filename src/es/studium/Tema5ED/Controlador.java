package es.studium.Tema5ED;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador implements WindowListener, ActionListener, ItemListener
{
	Vista vista = null;
	Modelo modelo = null;
	
	public Controlador(Modelo modelo, Vista vista)
	{
		this.vista = vista;
		this.modelo = modelo;
		
		vista.addWindowListener(this);
		vista.nuevoEmpleado.addWindowListener(this);
		vista.eliminarEmpleado.addWindowListener(this);
		vista.consultarEmpleado.addWindowListener(this);
		vista.modificarEmpleado.addWindowListener(this);
		vista.correcto.addWindowListener(this);
		vista.incorrecto.addWindowListener(this);
		vista.btnAceptarNuevo.addActionListener(this);
		vista.btnConsultar.addActionListener(this);
		vista.btnAceptarEliminar.addActionListener(this);
		vista.btnAceptarModificar.addActionListener(this);
		vista.nuevo.addActionListener(this);
		vista.consultar.addActionListener(this);
		vista.eliminar.addActionListener(this);
		vista.modificar.addActionListener(this);
		vista.eliminarDeLista.addItemListener(this);
		vista.modificarDeLista.addItemListener(this);
	}
			
	public void actionPerformed(ActionEvent evento)
	{	
		
		//Acceder desde la barra
		if(vista.nuevo.equals(evento.getSource()))
		{
			vista.nuevoEmpleado.setVisible(true);
		}
		else if(vista.consultar.equals(evento.getSource()))
		{
			vista.consultarEmpleado.setVisible(true);
		}
		else if(vista.eliminar.equals(evento.getSource()))
		{	
			try
			{
				//Cargar los controladores para el acceso a la BD
				Class.forName(modelo.driver);
				//Establecer la conexión con la BD Empresa
				modelo.connection = DriverManager.getConnection(modelo.url, modelo.login, modelo.password);
				//Crear una sentencia
				modelo.statement = modelo.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				modelo.rs= modelo.statement.executeQuery("SELECT idEmpleado,nombreEmpleado FROM empleados");
				while(modelo.rs.next())
				{
					String s = modelo.rs.getString("idEmpleado");
					s = s + "-"+ modelo.rs.getString("nombreEmpleado");
					vista.eliminarDeLista.add(s);
				}
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Error 1-"+cnfe.getMessage());
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
			vista.eliminarEmpleado.setVisible(true);
		}
		else if(vista.modificar.equals(evento.getSource()))
		{
			try
			{
				//Cargar los controladores para el acceso a la BD
				Class.forName(modelo.driver);
				//Establecer la conexión con la BD Empresa
				modelo.connection = DriverManager.getConnection(modelo.url, modelo.login, modelo.password);
				//Crear una sentencia
				modelo.statement = modelo.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				modelo.rs= modelo.statement.executeQuery("SELECT idEmpleado,nombreEmpleado FROM empleados");
				while(modelo.rs.next())
				{
					String s = modelo.rs.getString("idEmpleado");
					s = s + "-"+ modelo.rs.getString("nombreEmpleado");
					vista.modificarDeLista.add(s);
				}
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Error 1-"+cnfe.getMessage());
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
			vista.modificarEmpleado.setVisible(true);
		}
		//Funcionamiento de botones
		else if (vista.btnAceptarNuevo.equals(evento.getSource())) 
		{
				try
				{
					//Cargar los controladores para el acceso a la BD
					Class.forName(modelo.driver);
					//Establecer la conexión con la BD Empresa
					modelo.connection = DriverManager.getConnection(modelo.url, modelo.login, modelo.password);
					//Crear una sentencia
					modelo.statement = modelo.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

					int resp = modelo.statement.executeUpdate("INSERT INTO empleados(nombreEmpleado) VALUES ('"+vista.txNuevo.getText()+"')");
					
			        if (resp > 0) 
			        {
			            vista.correcto.setVisible(true);
			        }
			        else
			        {
			        	vista.incorrecto.setVisible(true);
			        }
					vista.txNuevo.setText("");
					
				}
				catch (ClassNotFoundException cnfe)
				{
					System.out.println("Error 1-"+cnfe.getMessage());
					vista.incorrecto.setVisible(true);
				}
				catch (SQLException sqle)
				{
					System.out.println("Error 2-"+sqle.getMessage());
					vista.incorrecto.setVisible(true);
				}
		}
		else if(vista.btnConsultar.equals(evento.getSource()))
		{
			try
			{
				//Cargar los controladores para el acceso a la BD
				Class.forName(modelo.driver);
				//Establecer la conexión con la BD Empresa
				modelo.connection = DriverManager.getConnection(modelo.url, modelo.login, modelo.password);
				//Crear una sentencia
				modelo.statement = modelo.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				modelo.rs= modelo.statement.executeQuery("SELECT nombreEmpleado FROM empleados where idEmpleado='"+vista.txConsultarID1.getText()+"';");
				modelo.rs.next();
				String nombreEmpleado = modelo.rs.getString("nombreEmpleado");
				vista.txConsultarID2.setText(vista.txConsultarID1.getText());
				vista.txConsultarNombre.setText(nombreEmpleado);
				
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Error 1-"+cnfe.getMessage());
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
		}
		else if(vista.btnAceptarEliminar.equals(evento.getSource()))
		{
			String nombreEmpleado = vista.txEliminar.getText();
			try
			{
				//Cargar los controladores para el acceso a la BD
				Class.forName(modelo.driver);
				//Establecer la conexión con la BD Empresa
				modelo.connection = DriverManager.getConnection(modelo.url, modelo.login, modelo.password);
				//Crear una sentencia
				modelo.statement = modelo.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				int resp = modelo.statement.executeUpdate("DELETE FROM empleados WHERE nombreEmpleado='"+nombreEmpleado+"'");
				
		        if (resp > 0) 
		        {
		            vista.correcto.setVisible(true);
		        }
		        else
		        {
		        	vista.incorrecto.setVisible(true);
		        }
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Error 1-"+cnfe.getMessage());
				vista.incorrecto.setVisible(true);
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
				vista.incorrecto.setVisible(true);
			}
		}
		else if(vista.btnAceptarModificar.equals(evento.getSource()))
		{		
			String nombreEmpleado = vista.txModificar2.getText();
				try
				{
					
					int resp = modelo.statement.executeUpdate("UPDATE empleados SET nombreEmpleado='"+nombreEmpleado+"' WHERE nombreEmpleado='"+vista.txModificar1.getText()+"';");
					
			        if (resp > 0) 
			        {
			            vista.correcto.setVisible(true);
			        }
			        else
			        {
			        	vista.incorrecto.setVisible(true);
			        }
					
				}
				
				catch (SQLException sqle)
				{
					System.out.println("Error 2-"+sqle.getMessage());
					vista.incorrecto.setVisible(true);
				}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		if(vista.isActive())
		{
			System.exit(0);
		}
		else if(vista.nuevoEmpleado.isActive())
		{
			vista.nuevoEmpleado.setVisible(false);
		}
		else if(vista.eliminarEmpleado.isActive())
		{
			vista.eliminarEmpleado.setVisible(false);
		}
		else if(vista.modificarEmpleado.isActive())
		{
			vista.modificarEmpleado.setVisible(false);
		}
		else if(vista.consultarEmpleado.isActive())
		{
			vista.consultarEmpleado.setVisible(false);
		}
		else if(vista.correcto.isActive())
		{
			vista.correcto.setVisible(false);
		}
		else if(vista.incorrecto.isActive())
		{
			vista.incorrecto.setVisible(false);
		}
		else
		{
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) 
	{
		try
		{
			String[] array = arg0.getItem().toString().split("-");
			vista.txEliminar.setText(array[1]);
		}
		catch (ArrayIndexOutOfBoundsException AIOB)
		{
			System.out.println("Error: "+AIOB.getMessage());
		}
		
		try
		{
			String[] array = arg0.getItem().toString().split("-");
			vista.txModificar1.setText(array[1]);
		}
		catch (ArrayIndexOutOfBoundsException AIOB)
		{
			System.out.println("Error: "+AIOB.getMessage());
		}
	}
}