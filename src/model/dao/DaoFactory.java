package model.dao;

import db.DB;
import model.dao.impl.AlunoDaoJDBC;
import model.dao.impl.DisciplinaDaoJDBC;
import model.dao.impl.ProfessorDaoJDBC;
import model.dao.impl.TurmaDaoJDBC;
import model.entites.Aluno;

public class DaoFactory {
    public static AlunoDao criarAlunoDao(){
        return  new AlunoDaoJDBC(DB.getConnection());
    }
    public static ProfessorDao criarProfessorDao(){
        return  new ProfessorDaoJDBC(DB.getConnection());
    }
    public static DisciplinaDao criarDisciplinaDao(){

        return  new DisciplinaDaoJDBC(DB.getConnection());
    }
    public static TurmaDao criarTurmaDao(){
        return  new TurmaDaoJDBC(DB.getConnection());
    }
}
