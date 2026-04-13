package com.sockets;

import java.io.*;
import java.net.*;
import java.sql.*;

public class Server {

    public static void main(String[] args) {
        try {
            // Configurar el servidor socket
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Servidor esperando conexiones...");

            // Conexión a la base de datos MySQL en XAMPP
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_telefonico", "root", "root123456");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Leer el número de teléfono del cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String numeroTelefono = input.readLine();

                // Consultar la base de datos
                PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT dir_nombre, dir_direccion, ciud_nombre FROM personas "
                    + "INNER JOIN ciudades ON personas.dir_ciud_id = ciudades.ciud_id "
                    + "WHERE dir_tel = ?");
                preparedStatement.setString(1, numeroTelefono);
                ResultSet resultSet = preparedStatement.executeQuery();

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                if (resultSet.next()) {
                    String nombre = resultSet.getString("dir_nombre");
                    String direccion = resultSet.getString("dir_direccion");
                    String ciudad = resultSet.getString("ciud_nombre");
                    output.println("Nombre: " + nombre + ", Dirección: " + direccion + ", Ciudad: " + ciudad);
                } else {
                    output.println("Persona dueña de ese número telefónico no existe.");
                }

                socket.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
