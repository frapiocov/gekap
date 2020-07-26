package model;

import java.util.ArrayList;

public class Ordine {
    int id, prezzo, utente;
    ArrayList<Prodotto> prodotti;
    String prezzoEuro, data;

    public String getPrezzoEuro() {
        return String.format("%.2f", prezzo / 100.00);
    }

    public void setPrezzoEuro(String prezzoEuro) {
        this.prezzoEuro = prezzoEuro;
    }

    public int getUtente() {
        return utente;
    }

    public void setUtente(int utente) {
        this.utente = utente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
