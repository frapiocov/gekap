package model;

public class Prodotto {
    private String nome, trama, listaImmagini, trailer, genere, lingua, prezzoEuro;
    private int codice, categoria, anno, durata;
    private int prezzoCent;

    //setters
    public void setGenere(String genere) { this.genere = genere; }
    public void setAnno(int anno) { this.anno = anno; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTrama(String trama) { this.trama = trama; }
    public void setListaImmagini(String listaImmagini) { this.listaImmagini = listaImmagini; }
    public void setTrailer(String trailer) { this.trailer = trailer; }
    public void setCodice(int codice) { this.codice = codice; }
    public void setPrezzoCent(int prezzoCent) { this.prezzoCent = prezzoCent; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
    public void setLingua(String lingua) { this.lingua = lingua; }
    public void setDurata(int durata) { this.durata = durata; }

    //getters
    public String getGenere() { return genere; }
    public int getAnno() { return anno; }
    public String getNome() { return nome; }
    public String getTrama() { return trama; }
    public String getListaImmagini() { return listaImmagini; }
    public String getTrailer() {
        return trailer;
    }
    public int getCodice() {
        return codice;
    }
    public int getPrezzoCent() { return prezzoCent;}
    public String getPrezzoEuro() {
        return String.format("%.2f", prezzoCent / 100.00);
    }
    public int getCategoria() {
        return categoria;
    }
    public String getLingua() { return lingua; }
    public int getDurata() { return durata; }
}
