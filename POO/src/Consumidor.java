import java.util.List;
import java.io.Serializable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * @author Grupo 77
 */
public class Consumidor extends Contribuinte implements Serializable{
    private int depsagreg; //nº de dependentes do agregado familiar
    private int[] nifsagreg; //nif's do agregado familiar
    private int coeffiscal; //
    private List<Fatura> faturas;
    
    /**
     * Construtor vazio
     */
    public Consumidor(){
        super("Consumidor",0,"","",""); 
        this.depsagreg = 0; //nº de dependentes do agregado familiar
        this.nifsagreg = null; //nif's do agregado familiar
        this.coeffiscal = 0; //
        this.faturas = null;
    }

    /**
     * Construtor parametrizado 
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param password
     * @param depsagreg
     * @param nifsagreg
     * @param coeffiscal
     */
    public Consumidor(String nome, int nif, 
    String email, String morada, String password, 
    int depsagreg, int[] nifsagreg,int coeffiscal, 
    List<Fatura> faturas){
        super(nome, nif, email, morada, password); 
        this.depsagreg = depsagreg;
        this.nifsagreg = nifsagreg; //not sure
        this.coeffiscal = coeffiscal;
        this.faturas = faturas;
    }
    
    /**
     * Construtor de cópia 
     * @param c (Consumidor)
     */
    public Consumidor(Consumidor c){
        super(c); 
        this.depsagreg = c.getDepsagreg();
        this.nifsagreg = c.getNifsagreg();
        this.coeffiscal = c.getCoeffiscal();
        this.faturas = c.getFaturas();
    }
    
    /**
     * Getters
     */
    public int getDepsagreg(){
        return this.depsagreg;
    }
    
    public int[] getNifsagreg(){
        return this.nifsagreg;
    }
    
    public int getCoeffiscal(){
        return this.coeffiscal;
    }
    
    public List<Fatura> getFaturas(){
        return faturas.stream()
                      .map(Fatura::clone)
                      .collect(toList());
    }
    
    /**
     * Setters
     */
    public void setDepsagreg(int da){
        this.depsagreg = da;
    }
    
    public void setNifsagreg(int[] na){
        this.nifsagreg = na;
    }
    
    public void setCoeffiscal(int cf){
        this.coeffiscal = cf;
    }
    
    public void setFaturas(List<Fatura> f){
       this.faturas=f.stream().map(Fatura::clone)
                    .collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Metodo Equals
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
        
        if (o == null || this.getClass() != o.getClass())
            return false;
        
        Consumidor c = (Consumidor) o;
        
        return super.equals(c) && 
        this.depsagreg == c.getDepsagreg() && 
        this.nifsagreg == c.getNifsagreg() && 
        this.coeffiscal == c.getCoeffiscal() &&
        this.faturas == c.getFaturas();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append("Dependentes do agregado familiar: " +this.getDepsagreg() + "\n");
        sb.append("NIF's do agregado familiar: " + this.getNifsagreg() + "\n");
        sb.append("Coeficiente fiscal: " + this.getCoeffiscal() + "\n");
        sb.append("Codigo da atividade: " +this.getFaturas() + "\n");
        return sb.toString();
    }
    
    public Consumidor clone (){
        return new Consumidor(this);    
    }
}