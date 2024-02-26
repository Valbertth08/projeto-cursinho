package aplication;

import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.entites.Aluno;
import model.entites.Turma;

import java.util.Date;
import java.util.List;

public class ProgramAluno {

    public static void main(String[] args) {

      System.out.println("======= INSERIR ALUNO ========");
      AlunoDao alunoDao= DaoFactory.criarAlunoDao();
      alunoDao.inserir(new Aluno(7,"raimundo",new Date(),98981595141L,8887283811L,"jose@gmail.com",new Turma(2)));


      System.out.println("======= PESQUISAR ALUNO ========");
      Aluno aluno= alunoDao.pesquisarId(4);
      System.out.println(aluno);

      System.out.println("======= ATUALIZAR ALUNO ========");
      alunoDao.atualizar(new Aluno(7,"raimundo",new Date(),98981595141L,8887289811L,"jose@gmail.com",new Turma(2)));

      System.out.println("======= DELETAR ALUNO ========");
      alunoDao.apagar(7);


      System.out.println("======= PESQUISAR TODOS ALUNOS ========");
      List<Aluno> alunos= alunoDao.pesquisarTodos();
      for (Aluno i: alunos){
          System.out.println(i);
      }

    }
}
