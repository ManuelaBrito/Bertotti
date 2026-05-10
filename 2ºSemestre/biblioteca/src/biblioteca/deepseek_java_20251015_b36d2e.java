import java.sql.*;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            // Testa se o driver está disponível
            Class.forName("org.sqlite.JDBC");
            System.out.println("🎉 Driver SQLite carregado com sucesso!");
            
            // Tenta fazer uma conexão
            Connection conn = DriverManager.getConnection("jdbc:sqlite:teste.db");
            System.out.println("✅ Conexão com banco SQLite criada!");
            
            conn.close();
            
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}