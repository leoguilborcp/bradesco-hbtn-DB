import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class MongoDBConnection {


    // Variáveis de configuração
    private static final String USERNAME = "leoguilbor_db_user";
    private static final String PASSWORD = "5q9xThlvwV046sO8";
    private static final String CLUSTER_URL = "hbtn-leoguilbor.0fpenet.mongodb.net"; // Substitua pelo seu cluster se for diferente
    private static final String DATABASE_NAME = "hbtn-leoguilbor"; // Substitua pelo nome do seu banco de dados


    private MongoClient mongoClient;
    private MongoDatabase database;


    public MongoDBConnection() {
        try {
            // String de conexão com credenciais
            String connectionString = String.format("mongodb+srv://"+USERNAME+":"+PASSWORD+"@"+CLUSTER_URL+"/?retryWrites=true&w=majority&appName="+DATABASE_NAME);


            // Configuração do cliente MongoDB
            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();


            // Criando o cliente
            mongoClient = MongoClients.create(settings);


            // Selecionando o banco de dados
            database = mongoClient.getDatabase(DATABASE_NAME);


            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public MongoDatabase getDatabase() {
        return database;
    }


    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }


    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        UsuarioOperations usuarioOps = new UsuarioOperations(connection);

        usuarioOps.listarUsuarios().forEach(System.out::println);

        // Inserir usuários (apenas se não existirem, para evitar duplicidade)
        usuarioOps.inserirUsuario(new Usuario("Alice", 25));
        usuarioOps.inserirUsuario(new Usuario("Bob", 30));
        usuarioOps.inserirUsuario(new Usuario("Charlie", 35));

        // 1. Consultar registros
        System.out.println("Usuários cadastrados:");
        usuarioOps.listarUsuarios().forEach(System.out::println);

        // 2. Alterar idade de Bob para 32 anos
        Usuario bob = usuarioOps.buscarUsuarioPorNome("Bob");
        if (bob != null) {
            bob.setIdade(32);
            usuarioOps.atualizarUsuario("Bob", bob);
        }

        // 3. Consultar registros
        System.out.println("\nApós atualizar idade de Bob:");
        usuarioOps.listarUsuarios().forEach(System.out::println);

        // 4. Apagar registro Charlie
        usuarioOps.removerUsuario("Charlie");

        // 5. Consultar registros
        System.out.println("\nApós remover Charlie:");
        usuarioOps.listarUsuarios().forEach(System.out::println);

        connection.closeConnection();
    }


}