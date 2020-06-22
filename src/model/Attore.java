package model;

public class Attore {
    private int id;
    private String nome;
    private String ruolo;

    //getters
    public int getId() {
        return id;
    }
    public String getNome() { return nome; }
    public String getRuolo() { return ruolo; }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
