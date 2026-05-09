package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/simulador_energia?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Milkz!";

    public static Connection getConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) { 
        
            System.out.println("Erro ao estabelecer conexão ou carregar driver.");
            System.out.println("Detalhe: " + e.getMessage());

            return null;
        }
    }
}