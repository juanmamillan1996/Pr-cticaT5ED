package es.studium.Tema5ED;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;

public class Vista extends Frame
{
	private static final long serialVersionUID = 1L;
	
	//Ventanas del programa
	Dialog nuevoEmpleado = new Dialog(this,"Nuevo empleado",true);
	Dialog consultarEmpleado = new Dialog(this,"Consultar empleado",true);
	Dialog eliminarEmpleado = new Dialog(this,"Eliminar empleado",true);
	Dialog modificarEmpleado = new Dialog(this,"Modificar empleado",true);
	Dialog correcto = new Dialog(this,"Cambios guardados",true);
	Dialog incorrecto = new Dialog(this,"Error",true);
	
	//Objetos para el menu
	MenuBar barraMenu = new MenuBar();
	Menu empleados = new Menu("Empleados");
		//Items de Empleados
		MenuItem nuevo = new MenuItem("Nuevo");
		MenuItem consultar = new MenuItem("Consultar");
		MenuItem modificar = new MenuItem("Modificar");
		MenuItem eliminar = new MenuItem("Eliminar");
		
	//Nuevo empleado
	Label lbNuevo = new Label("Nuevo Empleado");
	TextField txNuevo = new TextField(20);
	Button btnAceptarNuevo = new Button("Aceptar");
	
	//Consultar empleado
	Label lbConsultarID1 = new Label("Introduzca id del empleado:");
	TextField txConsultarID1 = new TextField(5);
	Label lbConsultarNombre = new Label("Nombre:");
	Label lbConsultarID2 = new Label("ID:");
	TextField txConsultarNombre = new TextField(20);
	TextField txConsultarID2 = new TextField(5);
	Button btnConsultar = new Button("Consultar");
	
	//Eliminar empleado
	Choice eliminarDeLista = new Choice();
	Label lbEliminar = new Label("Empleado que se eliminará:");
	TextField txEliminar = new TextField(20);
	Button btnAceptarEliminar = new Button("Eliminar");
	
	//Modificar empleado
	Choice modificarDeLista = new Choice();
	Label lbModificar1 = new Label("Nombre actual:");
	TextField txModificar1 = new TextField(20);
	Label lbModificar2 = new Label("Nuevo nombre:");
	TextField txModificar2 = new TextField(20);
	Button btnAceptarModificar = new Button("Modificar");
	
	//Acción correcta
	Label lbCorrecto = new Label("La acción se ha realizado de forma correcta");
	
	//Acción Incorrecta
	Label lbIncorrecto = new Label("La acción no se ha realizado correctamente");
		
 
	public Vista() 
	{
		//Menú principal
		setLayout(new FlowLayout());
		setTitle("Menú principal");
		setMenuBar(barraMenu);
		barraMenu.add(empleados);
		empleados.add(nuevo);
		empleados.add(consultar);
		empleados.add(modificar);
		empleados.add(eliminar);
		setSize(350,200);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Ventana de nuevo empleado
		nuevoEmpleado.setLayout(new FlowLayout());
		nuevoEmpleado.add(lbNuevo);
		nuevoEmpleado.add(txNuevo);
		nuevoEmpleado.add(btnAceptarNuevo);
		nuevoEmpleado.setSize(200,150);
		nuevoEmpleado.setLocationRelativeTo(null);
		nuevoEmpleado.setVisible(false);
		
		//Ventana de consultar empleado
		consultarEmpleado.setLayout(new FlowLayout());
		consultarEmpleado.add(lbConsultarID1);
		consultarEmpleado.add(txConsultarID1);
		consultarEmpleado.add(btnConsultar);
		consultarEmpleado.add(lbConsultarNombre);
		consultarEmpleado.add(txConsultarNombre);
		consultarEmpleado.add(lbConsultarID2);
		consultarEmpleado.add(txConsultarID2);
		consultarEmpleado.setSize(200,200);
		consultarEmpleado.setLocationRelativeTo(null);
		consultarEmpleado.setVisible(false);
		
		//Ventana de eliminar empleado
		eliminarEmpleado.setLayout(new FlowLayout());
		eliminarEmpleado.add(eliminarDeLista);
		eliminarDeLista.add("Selecciona un empleado para borrarlo");
		eliminarEmpleado.add(lbEliminar);
		eliminarEmpleado.add(txEliminar);
		eliminarEmpleado.add(btnAceptarEliminar);
		eliminarEmpleado.setSize(270,160);
		eliminarEmpleado.setLocationRelativeTo(null);
		eliminarEmpleado.setVisible(false);
		
		//Ventana de modificar empleado
		modificarEmpleado.setLayout(new FlowLayout());
		modificarEmpleado.add(modificarDeLista);
		modificarDeLista.add("Escoge un empleado");
		modificarEmpleado.add(lbModificar1);
		modificarEmpleado.add(txModificar1);
		modificarEmpleado.add(lbModificar2);
		modificarEmpleado.add(txModificar2);
		modificarEmpleado.add(btnAceptarModificar);
		modificarEmpleado.setSize(230,230);
		modificarEmpleado.setLocationRelativeTo(null);
		modificarEmpleado.setVisible(false);
		
		//Ventana de acción Correcta
		correcto.setLayout(new FlowLayout());
		correcto.add(lbCorrecto);
		correcto.setSize(280,100);
		correcto.setLocationRelativeTo(null);
		correcto.setVisible(false);
		
		//ventana de acción incorrecta
		incorrecto.setLayout(new FlowLayout());
		incorrecto.add(lbIncorrecto);
		incorrecto.setSize(280,100);
		incorrecto.setLocationRelativeTo(null);
		incorrecto.setVisible(false);
	}
}