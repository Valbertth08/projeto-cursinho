package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.TurmaDao;
import model.entites.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDaoJDBC implements TurmaDao {
    private Connection conn;
    public TurmaDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void inserir(Turma obj) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try{
            ps=conn.prepareStatement("insert into turma (sala) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,obj.getSala());
            int linhasAfetadas =  ps.executeUpdate();
            System.out.println("Linhas afetadas: "+linhasAfetadas);
            rs=ps.getGeneratedKeys();
            if(rs.next()){
                obj.setId(rs.getInt(1));
                System.out.println(obj);
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao inserir uma turma: "+e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }
    @Override
    public Turma pesquisarId(Integer id) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try {
            ps=conn.prepareStatement("select * from turma where id_turma= ?");
            ps.setInt(1,id);
            rs= ps.executeQuery();
            Turma turma= new Turma();
            if (rs.next()){
                turma.setId(rs.getInt(1));
                turma.setSala(rs.getString(2));
                return turma;
            }
            else{
                throw  new DbException("Nenhum resultado encontrado");
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao pesquisar a turma: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public void atualizar(Turma obj) {
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("update turma set sala =? where id_turma= ?");
            ps.setString(1,obj.getSala());
            ps.setInt(2,obj.getId());
            int linhasAfetadas= ps.executeUpdate();
            if(linhasAfetadas>0){
                System.out.println("Linhas afetadas: "+linhasAfetadas);
            }
            else{
                throw new DbException("Nenhuma linha afetada!");
            }
        }catch (SQLException e){
                throw  new DbException("Erro ao atualizar: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public void apagar(Integer id) {
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("delete from turma where id_turma=?");
            ps.setInt(1,id);
            int linhasAfetadas= ps.executeUpdate();
            if(linhasAfetadas>0){
                System.out.println("Linhas afetadas: "+linhasAfetadas);
            }
            else{
                throw new DbException("Nenhuma linha afetada!");
            }
        }catch (SQLException e){
            throw new DbException("Erro ao apagar a turma: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public List<Turma> pesquisarTodos() {
        Statement ps=null;
        ResultSet rs= null;
        try {
            ps= conn.createStatement();
            rs=ps.executeQuery("select * from turma");
            List<Turma> turmas= new ArrayList<>();
            while (rs.next()){
                Turma turma= new Turma(rs.getInt(1),rs.getString(2));
                turmas.add(turma);
            }
            return turmas;

        }catch (SQLException e){
            throw new DbException("Erro ao pesquisar as turmas: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
