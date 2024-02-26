package model.dao.impl;

import com.mysql.cj.protocol.x.ReusableOutputStream;
import db.DB;
import db.DbException;
import model.dao.AlunoDao;
import model.entites.Aluno;
import model.entites.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDaoJDBC implements AlunoDao {
    private Connection conn;
    public AlunoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void inserir(Aluno obj) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try {
            ps= conn.prepareStatement("insert into aluno " +
                    "(nome,data_nascimento,telefone,cpf,email,id_turma) " +
                    "values( ?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getNome());
            ps.setDate(2,new java.sql.Date(obj.getData_Nascimento().getTime()));
            ps.setString(3, String.valueOf(obj.getTelefone()));
            ps.setString(4,String.valueOf(obj.getCpf()));
            ps.setString(5,obj.getEmail());
            ps.setInt(6,obj.getTurma().getId());
            int linhasAfetadas= ps.executeUpdate();
            if(linhasAfetadas>0){
                rs=ps.getGeneratedKeys();
                System.out.println("Linhas afetadas: "+linhasAfetadas);
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao inserir o aluno: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public Aluno pesquisarId(Integer id) {
        PreparedStatement ps=null;
        ResultSet rs= null;
        try{
            ps= conn.prepareStatement("select aluno.*,turma.sala from aluno inner join turma on aluno.id_turma = turma.id_turma where id_aluno=?");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            Aluno aluno = new Aluno();
            if(rs.next()){
                aluno.setId(rs.getInt(1));
                aluno.setNome(rs.getString(2));
                aluno.setEmail(rs.getString(3));
                aluno.setData_Nascimento(rs.getDate(4));
                aluno.setCpf(rs.getLong(5));
                aluno.setTelefone(rs.getLong(6));
                aluno.setTurma(istanciarTurma(rs));
                return aluno;
            }
            else {
                throw  new DbException("Nenhum aluno encontrado");
            }
        }catch (SQLException e){
            throw  new DbException("Erro ao pesquisar aluno: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public void atualizar(Aluno obj) {
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("update aluno set " +
                    "nome=?,data_nascimento=?,cpf=?,email=?,telefone=?,id_turma=? where id_aluno=?");
            ps.setString(1,obj.getNome());
            ps.setDate(2, new java.sql.Date(obj.getData_Nascimento().getTime()));
            ps.setString(3,String.valueOf(obj.getCpf()));
            ps.setString(4,obj.getEmail());
            ps.setString(5,String.valueOf(obj.getTelefone()));
            ps.setInt(6,obj.getTurma().getId());
            ps.setInt(7,obj.getId());
            int linhasAfetadas= ps.executeUpdate();
            System.out.println("Linhas afetadas: "+linhasAfetadas);
        }catch (SQLException e){
            throw  new DbException("Erro ao atualizar aluno: "+e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public void apagar(Integer id) {
        PreparedStatement ps= null;
        try{
            ps=conn.prepareStatement("delete from aluno where id_aluno=? ");
            ps.setInt(1,id);
            int linhasAfetadas= ps.executeUpdate();
            if( linhasAfetadas>0){
                System.out.println("Linhas afetadas: "+linhasAfetadas);
            }
        }
        catch (SQLException e){
            throw new DbException("Erro ao deletar o aluno: "+e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public List<Aluno> pesquisarTodos() {
        Statement st=null;
        ResultSet rs=null;
        try{
             st= conn.createStatement();
            rs=  st.executeQuery("select aluno.*,turma.sala from aluno inner join turma on aluno.id_turma = turma.id_turma");
            List<Aluno>Listalunos= new ArrayList<>();
            Map<Integer, Turma> map= new HashMap<>();
            while (rs.next()){
                Turma turma= map.get(rs.getInt(1));
                if(turma== null){
                    turma=istanciarTurma(rs);
                    map.put(rs.getInt(1),turma);
                }
                Aluno aluno= istanciarAluno(rs,turma);
                Listalunos.add(aluno);
            }
            return Listalunos;
        }catch (SQLException e){
            throw  new DbException("Erro ao buscar os alunos: "+e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    public Turma istanciarTurma(ResultSet rs) throws SQLException{
        Turma turma= new Turma();
        turma.setId(rs.getInt("id_turma"));
        turma.setSala(rs.getString("sala"));
        return  turma;
    }
    public Aluno istanciarAluno(ResultSet rs,Turma turma) throws  SQLException{
        Aluno aluno= new Aluno();
            aluno.setId(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setData_Nascimento(rs.getDate("data_nascimento"));
            aluno.setTelefone(rs.getLong("telefone"));
            aluno.setEmail(rs.getString("email"));
            aluno.setCpf(rs.getLong("cpf"));
            aluno.setTurma(turma);
            return aluno;
    }

}
