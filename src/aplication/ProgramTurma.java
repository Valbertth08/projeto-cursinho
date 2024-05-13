package aplication;

import model.dao.DaoFactory;
import model.dao.ProfessorDao;
import model.dao.TurmaDao;
import model.entites.Professor;
import model.entites.Turma;

import java.util.List;
public class ProgramTurma {
    public static void main(String[] args) {

        System.out.println("======= INSERIR TURMA ========");
        TurmaDao turmaDao= DaoFactory.criarTurmaDao();
        turmaDao.inserir(new Turma(1,"209"));

        System.out.println("======= PESQUISAR TURMA ========");
        Turma turma= turmaDao.pesquisarId(4);
        System.out.println(turma);

        System.out.println("======= ATUALIZAR TURMA ========");
        turmaDao.atualizar(new Turma(3,"600"));

        System.out.println("======= DELETAR TURMA ========");
        turmaDao.apagar(12);

        System.out.println("======= PESQUISAR TODAS AS TURMAS ========");
        List<Turma> turmas= turmaDao.pesquisarTodos();
        for (Turma i: turmas){
            System.out.println(i);
        }
    }
}
