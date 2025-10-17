package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class MaterialCurso implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    public MaterialCurso() {}

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MaterialCurso{" + "id=" + id + ", url='" + url + '\'' + '}';
    }
}
