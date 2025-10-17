import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class UsuarioOperations {
    private final MongoCollection<Document> collection;

    public UsuarioOperations(MongoDBConnection connection) {
        this.collection = connection.getDatabase().getCollection("usuarios");
    }

    // CREATE
    public void inserirUsuario(Usuario usuario) {
        collection.insertOne(usuario.toDocument());
    }

    // READ (listar todos)
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                usuarios.add(Usuario.fromDocument(cursor.next()));
            }
        }
        return usuarios;
    }

    // READ (buscar por nome)
    public Usuario buscarUsuarioPorNome(String nome) {
        Document doc = collection.find(Filters.eq("nome", nome)).first();
        return doc != null ? Usuario.fromDocument(doc) : null;
    }

    // UPDATE
    public boolean atualizarUsuario(String nome, Usuario novoUsuario) {
        Document update = new Document("$set", novoUsuario.toDocument());
        return collection.updateOne(Filters.eq("nome", nome), update).getModifiedCount() > 0;
    }

    // DELETE
    public boolean removerUsuario(String nome) {
        return collection.deleteOne(Filters.eq("nome", nome)).getDeletedCount() > 0;
    }
}
