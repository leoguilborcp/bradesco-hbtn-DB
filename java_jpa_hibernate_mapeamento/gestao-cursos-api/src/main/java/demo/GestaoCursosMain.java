package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {
        CursoModel cursoModel = new CursoModel();

        AlunoModel alunoModel = new AlunoModel();


        Endereco e = new Endereco();
        e.setLogradouro("Rua");
        e.setEndereco("Endereço");
        e.setNumero("123");
        e.setBairro("Centro");
        e.setCidade("Cida de");
        e.setEstado("Es tado");
        e.setCep("12345-678");

        Telefone t = new Telefone();
        t.setDDD("021");
        t.setNumero("555-0123");

        Aluno a = new Aluno();
        a.setNomeCompleto("Aluno A");
        a.setEmail("aluno.a@email.com");
        a.addTelefone(t);
        a.addEndereco(e);

        alunoModel.create(a);

        Professor p = new Professor();
        p.setEmail("pr@fessor.com");
        p.setMatricula("123");
        p.setNomeCompleto("Profe Sor");

        MaterialCurso mc = new MaterialCurso();
        mc.setUrl("www.profe.sor");

        Curso c = new Curso();
        c.setNome("Java Avançado");
        c.setSigla("JA");
        c.setProfessor(p);
        c.setMaterialCurso(mc);
        c.addAluno(a);

        // 1) Criando um curso
        cursoModel.create(c);

        // 2) Buscando todos os cursos na base de dados
        List<Aluno> alunos = alunoModel.findAll();
        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Qtde de cursos encontrados : " + cursos.size());
        System.out.println("Qtde de alunos encontrados : " + alunos.size());
        // TODO - Testar os demais metódos das classes: ProdutoModel e PessoaModel

    }
}
