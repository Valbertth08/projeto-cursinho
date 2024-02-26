package model.dao;

import java.util.List;

public interface Dao<T> {
    void  inserir(T obj);
    T  pesquisarId(Integer id);
    void atualizar(T obj);
    void apagar( Integer id);
    List<T> pesquisarTodos();

}
