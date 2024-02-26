package aplication;

import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.dao.DisciplinaDao;
import model.entites.Aluno;
import model.entites.Disciplina;

import java.util.Date;
import java.util.List;

public class ProgramDisciplina {
    public static void main(String[] args) {
        System.out.println("======= INSERIR DISCIPLINA ========");
        DisciplinaDao disciplinaDao= DaoFactory.criarDisciplinaDao();
        disciplinaDao.inserir(new Disciplina(1,"portuges"));

        System.out.println("======= PESQUISAR DISCIPLINA ========");
        Disciplina disciplina= disciplinaDao.pesquisarId(4);
        System.out.println(disciplina);

        System.out.println("======= ATUALIZAR DISCIPLINA ========");
        disciplinaDao.atualizar(new Disciplina(12,"fisica"));

        System.out.println("======= DELETAR DISCIPLINA ========");
        disciplinaDao.apagar(12);


        System.out.println("======= PESQUISAR TODAS AS DISCIPLINAS ========");
        List<Disciplina> disciplinas= disciplinaDao.pesquisarTodos();
        for (Disciplina i: disciplinas){
            System.out.println(i);
        }
    }

}
