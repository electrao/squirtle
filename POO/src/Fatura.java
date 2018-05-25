import java.time.LocalDate;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * Write a description of class Fatura here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fatura implements Serializable{
    
    

    private Integer nifE; //nif do emitente
    private String emitente; //designaçao do emitente
    private LocalDate date; //data da Fatura; 
    private Integer nifC; //nif do cliente
    private String descricao; // descricao da fatura
    private float valor; //valor da Fatura
    private int idFatura; //numero da fatura
    private boolean fatValida; //diz se a fatura esta validada
    
    /**
     * Construtor vazio
     */
    public Fatura(){
        this.nifE = 0;
        this.emitente = "";
        this.date = null; // nao sei se e assim ou LocalDate date = null;
        this.nifC = 0;
        this.descricao = "";
        //this.tipo = null;
        this.valor = 0;
        this.idFatura = 1;
        this.fatValida = false;
    }
    
    /**
     * Construtor parametrizado 
     * @param nifE
     * @param emitente
     * @param date
     * @param nifC
     * @param descricao 
     * @param valor
     */
    public Fatura(int nifE, String emitente, LocalDate date, 
    int nifC, String descricao, float valor, int idFatura, 
    boolean fatValida){
        this.nifE = nifE;
        this.emitente = emitente;
        this.date = date;
        this.nifC = nifC;
        this.descricao = descricao;
        this.valor = valor;
        this.idFatura = idFatura;
        this.fatValida = fatValida;
    }
    
    /**
     * Construtor de cópia 
     * @param d (Fatura)
     */
    public Fatura(Fatura d){
        this.nifE = d.getNifE();
        this.emitente = d.getEmitente();
        this.date = d.getDate();
        this.nifC = d.getNifC();
        this.descricao = d.getDescricao();
        this.valor = d.getValor();
        this.idFatura = d.getIdFatura();
        this.fatValida = d.getFatValida();
    }
    
    /**
     * Getters
     */
    public int getNifE(){
        return this.nifE;
    } 
    
    public String getEmitente(){
        return this.emitente;
    }

    public LocalDate getDate(){
        return this.date;
    }
    
    public int getNifC(){
        return this.nifC;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public int getIdFatura(){
        return this.idFatura;
    }
    
    public float getValor(){
        return this.valor;
    }
    
    public boolean getFatValida(){
        return this.fatValida;
    }
    
    /**
     * Setters
     */
    public void setNifE(int n){
        this.nifE = n;
    } 
    
    public void setEmitente(String e){
        this.emitente = e;
    }
    
    public void setDate(LocalDate d){
        this.date = d;
    }
    
    public void setNifC(int n){
        this.nifC = n;
    }
    
    public void setDescricao(String d){
        this.descricao = d;
    }
        
    public void setValor(float v){
        this.valor = v;
    }
    
    public void setIdFatura(int i){
        this.idFatura = i;
    } 
    
    public void setFatValida(boolean f){
        this.fatValida = f;
    }
    
    /**
     * Metodo Equals
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
            
        if (o == null || this.getClass() != o.getClass())
            return false;
            
        Fatura d = (Fatura) o;
        
        return this.nifE == d.getNifE() &&
        this.emitente.equals(d.getEmitente()) &&
        this.date.equals(d.getDate()) && 
        this.nifC == d.getNifC() &&
        this.descricao.equals(d.getDescricao()) &&
        this.valor == d.getValor() && 
        this.idFatura == d.getIdFatura() &&
        this.fatValida == d.getFatValida();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        sb.append("NIF do emitente: " +this.getNifE() + "\n");
        sb.append("Nome do emitente: " + this.getEmitente() + "\n");
        sb.append("Data de emissao: " + this.getDate() + "\n");
        sb.append("NIF do emitente: " + this.getNifC() + "\n");
        sb.append("Descricao da fatura: " +this.getDescricao() + "\n");
        sb.append("Valor da fatura: " +this.getValor()+ "\n");  
        sb.append("Id da fatura: " +this.getIdFatura()+"\n");
        sb.append("A Fatura esta validada? "+this.getFatValida()+"\n");
        return sb.toString(); 
    }  
    
    public Fatura clone (){
        return new Fatura(this);    
    }
}
