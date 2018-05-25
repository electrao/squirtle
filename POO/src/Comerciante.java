import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Grupo 77
 */
public class Comerciante extends Contribuinte implements Serializable{
    private Map<Integer,Fatura> faturas;
    private String atiEco; //conjunto de strings
    private boolean singular; //tem uma ou mais atividades ? singular igual a true so tem uma atividade, false tem mais
    private boolean eDoInterior; //so se boolean for igual a 1 faz deducao para o interior senao fazz normalmente
    
    /**
     * Construtor vazio
     */
    public Comerciante(){
        super("Comerciante",0,"","","");
        this.atiEco = "";
        this.singular = false;
        this.eDoInterior = false;
    }
    
    /**
     * Construtor parametrizado 
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param password
     */
    public Comerciante(String nome, int nif, String email, 
    String morada, String password, boolean singular,
    String atiEco, boolean eDoInterior){
        super(nome, nif, email, morada, password);
        this.atiEco = atiEco;
        this.singular = singular;
        this.eDoInterior = eDoInterior;
    }
    
    /**
     * Construtor de cópia 
     * @param c (Comerciante)
     */
    public Comerciante(Comerciante c){
        super(c);
        this.atiEco = c.getAtiEco();
        this.singular = c.getSingular();
        this.eDoInterior = c.getEDoInterior();
    }
    
    /**
     * Getters
     */
    public String getAtiEco(){
        return this.atiEco;
    } 
    
    public boolean getEDoInterior(){
        return this.eDoInterior;
    }
    
    public Map<Integer,Fatura> getFaturas(){
        Map<Integer,Fatura> copia = new  HashMap<>();
        for(Integer nif : this.faturas.keySet()){
            Fatura f = this.faturas.get(nif);
            copia.put(nif,f);
        }
        return copia;
    }
    
    public boolean getSingular(){    
        return this.singular;
    }
    
 
    
    /**
     * Setters
     */
    public void setAtiEco(String a){
        this.atiEco = a;
    } 
    
    public void setEDoInterior(boolean e){
        this.eDoInterior = e;
    }
    
    public void setFaturas(Map<Integer,Fatura> faturas){
        Map<Integer,Fatura> copia = new  HashMap<>();
        for(Map.Entry<Integer,Fatura> f : faturas.entrySet()){
            copia.put(f.getKey(),f.getValue());
        }
        this.faturas = copia;
    }
    
    public void setSingular(boolean s){
        this.singular = s;
    }
    
    /**
     * Metodo Equals
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
            
        if (o == null || this.getClass() != o.getClass())
            return false;
            
        Comerciante c = (Comerciante) o;
        
        return super.equals(c) &&
        this.atiEco.equals(c.getAtiEco()) &&
        this.eDoInterior == c.getEDoInterior();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        
        sb.append("Atividades economicas: " + this.getAtiEco() + "\n");
        sb.append("E do interior: " + this.getEDoInterior() + "\n");
        return sb.toString();
    }
    
    public Comerciante clone (){
        return new Comerciante(this);    
    }
    
    /**
     * Adiciona faturas
     */
    public void adicionaFatura(Fatura i) {
      this.faturas.put(i.getIdFatura(),i);
   }
  
}
