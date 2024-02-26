package model.entites;

import java.util.Objects;

public class Turma {
    private Integer id;
    private  String sala;

    public Turma() {
    }

    public Turma(Integer id) {
        this.id = id;
    }

    public Turma(Integer id, String sala) {
        this.id = id;
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", sala='" + sala + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
