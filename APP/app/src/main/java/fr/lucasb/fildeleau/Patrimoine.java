package fr.lucasb.fildeleau;

public class Patrimoine {

    private int identifiant;
    private String commune;
    private String elem_patri;
    private String elem_princ;

    public Patrimoine(){}

    public Patrimoine(int identifiant, String commune, String elem_patri, String elem_princ){
        this.commune = commune;
        this.identifiant = identifiant;
        this.elem_patri= elem_patri;
        this.elem_princ=elem_princ;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getElem_patri() {
        return elem_patri;
    }

    public void setElem_patri(String elem_patri) {
        this.elem_patri = elem_patri;
    }

    public String getElem_princ() {
        return elem_princ;
    }

    public void setElem_princ(String elem_princ) {
        this.elem_princ = elem_princ;
    }

    public String toString(){
        return "ID : "+identifiant+"\nCommune : "+commune+"\nElement patriarchal : "+elem_patri+"\nElement principales : "+elem_princ;
    }
}