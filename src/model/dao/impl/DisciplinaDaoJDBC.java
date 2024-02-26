package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DisciplinaDao;
import model.entites.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDaoJDBC implements DisciplinaDao {
    private Connection conn;
    public DisciplinaDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void inserir(Disciplina obj) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try{
            ps= conn.prepareStatement("insert into disciplina(nome) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getNome());
            int linhasAfetadas= ps.executeUpdate();
            System.out.println("Linhas afetadas: "+linhasAfetadas);
            if(linhasAfetadas> 0){
                rs=ps.getGeneratedKeys();
                while (rs.next()){
                    obj.setId(rs.getInt(1));
                    System.out.println(obj);
                }
            }
        }catch (SQLException e){
            throw  new DbException( "Erro ao inserir a disciplina: "+e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }
    @Override
    public Disciplina pesquisarId(Integer id) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try {
            ps=conn.prepareStatement("select * from disciplina where id_disciplina= ?");
            ps.setInt(1,id);
            rs= ps.executeQuery();
            Disciplina disciplina= new Disciplina();
            if(rs.next()){
                disciplina.setId(rs.getInt(1));
                disciplina.setNome(rs.getString(2));
                return disciplina;
            }
            else{
                throw  new DbException("Nenhum resultado encontrado!");
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao consultar a disciplina: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public void atualizar(Disciplina obj) {
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("update disciplina set nome=? where id_disciplina= ?");
            ps.setString(1,obj.getNome());
            ps.setInt(2,obj.getId());
            int linhasAfetadas= ps.executeUpdate();
            System.out.println("Linhas Afetadas: "+linhasAfetadas);

        }catch (SQLException e){
            System.out.println("Erro ao atualizar a disciplina: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public void apagar(Integer id) {
        PreparedStatement ps= null;
        try{
            ps=conn.prepareStatement("delete from disciplina where id_disciplina = ?");
            ps.setInt(1,id);
            int linhasAfetadas= ps.executeUpdate();
            if( linhasAfetadas>0){
                System.out.println("Linhas Afetadas: "+linhasAfetadas);
            }else {
                throw new DbException("Nenhuma linha deletada!");
            }

        }catch (SQLException e){
            throw  new DbException("Erro ao deletar a disciplina: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public List<Disciplina> pesquisarTodos() {
        Statement st= null;
        ResultSet rs=null;
        try{
            st=conn.createStatement();
            rs=st.executeQuery("select * from disciplina");
            List<Disciplina> listaAlunos= new ArrayList<>();
            while (rs.next()){
                Disciplina disciplina=new Disciplina(rs.getInt(1), rs.getString(2));
                listaAlunos.add(disciplina);
            }
            return listaAlunos;
        }catch (SQLException e){
            throw  new DbException("Erro ao consultar todas as disciplina: "+e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
