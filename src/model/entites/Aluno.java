package model.entites;


import java.util.Date;
import java.util.Objects;

public class Aluno {
    private Integer id;
    private String nome;
    private Date data_Nascimento;
    private Long telefone;
    private Long cpf;
    private String email;
    private Turma turma;

    public Aluno() {
    }

    public Aluno(Integer id, String nome, Date data_Nascimento, Long telefone, Long cpf, String email, Turma turma) {
        this.id = id;
        this.nome = nome;
        this.data_Nascimento = data_Nascimento;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.turma = turma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_Nascimento() {
        return data_Nascimento;
    }

    public void setData_Nascimento(Date data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data_Nascimento=" + data_Nascimento +
                ", telefone=" + telefone +
                ", cpf=" + cpf +
                ", email='" + email + '\'' +
                ", turma=" + turma +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
