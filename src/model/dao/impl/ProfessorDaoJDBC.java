package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProfessorDao;
import model.entites.Aluno;
import model.entites.Disciplina;
import model.entites.Professor;
import model.entites.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfessorDaoJDBC implements ProfessorDao {

    private Connection conn;
    public ProfessorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Professor obj) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try {
            ps= conn.prepareStatement("insert into professor " +
                    "(nome,email) " +
                    "values( ?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,obj.getNome());
            ps.setString(2,obj.getEmail());
            int linhasAfetadas= ps.executeUpdate();
            if(linhasAfetadas>0){
                rs=ps.getGeneratedKeys();
                System.out.println("Linhas afetadas: "+linhasAfetadas);
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                    System.out.println(obj);
                }
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao inserir o professor: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public Professor pesquisarId(Integer id) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try {
            ps=conn.prepareStatement("select * from professor where id_professor= ?");
            ps.setInt(1,id);
            rs= ps.executeQuery();
            Professor professor= new Professor();
            if(rs.next()){
                professor.setId(rs.getInt(1));
                professor.setNome(rs.getString(2));
                professor.setEmail(rs.getString(3));
                return professor;
            }
            else{
                throw  new DbException("Nenhum resultado encontrado!");
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao consultar a professor: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public void atualizar(Professor obj) {
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("update professor set " +
                    "nome=?,email=? where id_professor=?");
            ps.setString(1,obj.getNome());
            ps.setString(2,obj.getEmail());
            ps.setInt(3,obj.getId());
            int linhasAfetadas= ps.executeUpdate();
            System.out.println("Linhas afetadas: "+linhasAfetadas);
        }catch (SQLException e){
            throw  new DbException("Erro ao atualizar o professor: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public void apagar(Integer id) {
        PreparedStatement ps= null;
        try{
            ps=conn.prepareStatement("delete from professor where id_professor=? ");
            ps.setInt(1,id);
            int linhasAfetadas= ps.executeUpdate();
            if( linhasAfetadas>0){
                System.out.println("Linhas afetadas: "+linhasAfetadas);
            }
        }
        catch (SQLException e){
            throw new DbException("Erro ao deletar o professor: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public List<Professor> pesquisarTodos() {
        Statement st=null;
        ResultSet rs=null;
        try{
            st= conn.createStatement();
            rs=  st.executeQuery("select * from professor");
            List<Professor>ListaProfessor= new ArrayList<>();
            while (rs.next()){
                Professor professor= new Professor(rs.getInt(1),rs.getString(2),rs.getString(3));
                ListaProfessor.add(professor);
            }
            return ListaProfessor;
        }catch (SQLException e){
            throw  new DbException("Erro ao buscar os professores: "+e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}

