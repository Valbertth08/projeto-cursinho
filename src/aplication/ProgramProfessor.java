package aplication;

import model.dao.*;
import model.entites.Disciplina;
import model.entites.Professor;

import java.util.List;

public class ProgramProfessor {

    public static void main(String[] args) {

        System.out.println("======= INSERIR PROFESSOR ========");
        ProfessorDao professorDao= DaoFactory.criarProfessorDao();
        professorDao.inserir(new Professor(2,"antonio","antonio@gmail.com"));

        System.out.println("======= PESQUISAR PROFESSOR ========");
        Professor professor= professorDao.pesquisarId(4);
        System.out.println(professor);

        System.out.println("======= ATUALIZAR PROFESSOR ========");
        professorDao.atualizar(new Professor(2,"carlos","antonio@gmail.com"));

        System.out.println("======= DELETAR PROFESSOR ========");
        professorDao.apagar(12);

        System.out.println("======= PESQUISAR TODAS AS PROFESSORES ========");
        List<Professor> professores= professorDao.pesquisarTodos();
        for (Professor i: professores){
            System.out.println(i);
        }

    }

}
