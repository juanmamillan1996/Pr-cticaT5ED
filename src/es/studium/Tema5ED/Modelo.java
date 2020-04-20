package es.studium.Tema5ED;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class Modelo {

    public int contador = 0;

    public int posicionActual;

    // Nombre e introducción a la BD empresa

    String driver = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://localhost:3306/empresa?autoReconnect=true&useSSL=false";

    String login = "root";

    String password = "Studium2019;";

    // Creamos una consulta a la base de datos empleados

    String sentencia = "SELECT * FROM empleados";

    // Crear un objeto tipo Connection

    public Connection con = null;

    // Creamos un statement de SQL

    public Statement stmt = null;

    // Objeto donde se guarda la información de la consulta a la base de datos

    public ResultSet rs = null;

    public Connection connection;

    public Statement statement;

    public Modelo() {

        // Cargamos el Driver

        try {

            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            System.out.println("Hay un error al cargar el Driver");

        }

        // Establecer la conexión con la base de datos empresa

        try {

            con = DriverManager.getConnection(url, login, password);

        } catch (SQLException e) {

            System.out.println("Existe error al conectar a la Base de Datos");

        }

        //Realizar la consulta de la tabla empleados

        try {

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = stmt.executeQuery("SELECT * FROM empleados");

            //Averiguamos el número de registros obtenidos

            while (rs.next()) {

                contador++;

            }

            rs.first();

            posicionActual = 1;

        } catch (SQLException e) {

            System.out.println("Hay un error en la sentencia SQL");

        }

    }

}