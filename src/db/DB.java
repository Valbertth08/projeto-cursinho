package db;

import java.sql.*;

public class DB {

    private static Connection conn=null;
    public static Connection getConnection(){
        try{
            if(conn == null){
                conn= DriverManager.getConnection("jdbc:mysql://localhost/cursojdbc","root","root");
            }
            return conn;
        }catch (SQLException e){
            throw new DbException("Erro ao conectar ao banco"+e.getMessage());
        }
    }
    public static void cloceConnection(){
        if (conn != null) {
           try {
               conn.close();
           }catch (SQLException e){
               throw new DbException("Erro ao fechar a conexão"+e.getMessage());
           }

        }
    }
    public static void  closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            }catch (SQLException e){
                throw new DbException("Erro ao fechar o statement"+e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if(rs!= null){
            try {
                rs.close();
            }catch (SQLException e){
                throw new DbException("Erro ao fechar o ResultSet"+e.getMessage());
            }
        }
    }
}
