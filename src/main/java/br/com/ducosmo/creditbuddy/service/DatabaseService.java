package br.com.ducosmo.creditbuddy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {

    // Constrói o caminho para o banco de dados de forma universal (funciona em Windows, Mac, Linux)
    // Ex: C:\Users\Ducosmo\.creditbuddy\creditbuddy.db
    private static final String DATABASE_FOLDER = System.getProperty("user.home") + File.separator + ".creditbuddy";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_FOLDER + File.separator + "creditbuddy.db";

    private static Connection connection;

    // Impede que esta classe seja instanciada diretamente
    private DatabaseService() {}

    /**
     * Obtém a conexão única com o banco de dados (padrão Singleton).
     * Se a conexão ainda não existir, ela é criada.
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DATABASE_URL);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            // Em uma aplicação real, trataríamos esse erro de forma mais elegante
        }
        return connection;
    }

    /**
     * Método chamado na inicialização da aplicação para garantir que o diretório
     * e as tabelas do banco de dados existam.
     */
    public static void initializeDatabase() {
        // 1. Garante que a pasta de dados exista
        try {
            Path folderPath = Paths.get(DATABASE_FOLDER);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
                System.out.println("Diretório de dados criado em: " + DATABASE_FOLDER);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o diretório do banco de dados: " + e.getMessage());
            return; // Se não conseguir criar a pasta, não continua
        }

        // 2. Conecta ao banco e cria as tabelas se não existirem
        String sqlCreateTablePessoas = "CREATE TABLE IF NOT EXISTS pessoas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "celular TEXT);";

        String sqlCreateTableCartoes = "CREATE TABLE IF NOT EXISTS cartoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome_cartao TEXT NOT NULL," +
                "bandeira TEXT," +
                "dia_vencimento INTEGER," +
                "dia_fechamento INTEGER);";

        // Usamos try-with-resources para garantir que o Statement seja fechado
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCreateTablePessoas);
            System.out.println("Tabela 'pessoas' verificada/criada com sucesso.");

            stmt.execute(sqlCreateTableCartoes);
            System.out.println("Tabela 'cartoes' verificada/criada com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela 'pessoas': " + e.getMessage());
        }
    }
}