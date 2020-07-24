package model;

import java.util.Collection;
import java.util.LinkedHashMap;

public class Carrello {
     public static class ProdottoQuantita {     //sottoclasse di Carrello
         private Prodotto prodotto;
         private int quantita;

         private ProdottoQuantita(Prodotto p,int q) {
             prodotto=p;
             quantita=q;
         }

         public int getQuantita() {
             return quantita;
         }
         public void setQuantita(int q) {
             quantita=q;
         }
         public Prodotto getProdotto() {
             return prodotto;
         }
         public void setProdotto(Prodotto p) { prodotto=p; }
         public long getTotCent() {
             return quantita*prodotto.getPrezzoCent();
         }
         public String getTotEuro() {
             return String.format("%.2f",quantita * prodotto.getPrezzoCent()/100.);
         }
     }

     private LinkedHashMap<Integer,ProdottoQuantita> prodotti=new LinkedHashMap<Integer, ProdottoQuantita>();

     public Collection<ProdottoQuantita> getProdotti() {
         return prodotti.values();
     }

     public ProdottoQuantita get(int id) {
         return prodotti.get(id);
     }

     public void put(Prodotto p,int q) {
         prodotti.put(p.getCodice(),new ProdottoQuantita(p,q));
     }

     public ProdottoQuantita remove(int id) {
         return prodotti.remove(id);
     }

     public long getTotCent() {
         return prodotti.values().stream().mapToLong(ProdottoQuantita::getTotCent).sum();
     }

     public String getTotEuro() {
         return String.format("%.2f",getTotCent()/100.);
     }

     public boolean isEmpty() {
         return prodotti.isEmpty();
     }
}
