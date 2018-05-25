import java.io.Serializable;
import java.util.Arrays; 

/**
 * Write a description of class Contribuinte here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Contribuinte implements Serializable{
    private String nome; //nome do contribuinte
    private Integer nif; //nif do contribuinte
    private String email; //email do contribuinte
    private String morada; //morada do contribuinte
    private String password; //password do contribuinte
    
    /**
     * Construtor vazio
     */
    public Contribuinte(){
        this.nome = "";
        this.nif = null;
        this.email = "";
        this.morada = "";
        this.password = "";
    }
    
    /**
     * Construtor parametrizado 
     * @param nome
     * @param nif
     * @param email
     * @param morada
     * @param password
     */
    public Contribuinte(String nome, Integer nif, String email, 
    String morada, String password){
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.morada = morada;
        this.password = password;
    }
    
    /**
     * Construtor de cópia 
     * @param c (Contribuinte)
     */
    public Contribuinte(Contribuinte c){
        this.nome = c.getNome();
        this.nif = c.getNif();
        this.email = c.getEmail();
        this.morada = c.getMorada();
        this.password = c.getPassword();
    }
    
    /**
     * Getters
     */
    public String getNome(){
        return this.nome;
    } 
    
    public Integer getNif(){
        return this.nif;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Setters
     */
    public void setNome(String n){
        this.nome = n;
    } 
    
    public void setNif(Integer n){
        this.nif = n;
    }
    
    public void setEmail(String e){
        this.email = e;
    }
    
    public void setMorada(String m){
        this.morada = m;
    }
    
    public void setPassword(String p){
        this.password = p;
    }
    
    /**
     * Metodo Equals
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
            
        if (o == null || this.getClass() != o.getClass())
            return false;
            
        Contribuinte c = (Contribuinte) o;
        
        return this.nome.equals(c.getNome()) &&
        this.nif == c.getNif() &&
        this.email.equals(c.getEmail()) &&
        this.morada.equals(c.getMorada()) &&
        this.password.equals(c.getPassword());
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append("Nome: " +this.getNome() + "\n");
        sb.append("NIF: " + this.getNif() + "\n");
        sb.append("Email: " + this.getEmail() + "\n");
        sb.append("Morada: " +this.getMorada() + "\n");
        sb.append("Password: " +esconderPassword(getPassword().length(), '*') + "\n" ); //+this.getPassword() + "\n");
        return sb.toString();
    }
    
    /**
     * método que esconde a password substituindo o nr de caracteres da password pelo mesmo numero de * 
     */
   private  String esconderPassword (int tamanho, char caracter) {
       if (tamanho > 0) {
           char[] array = new char[tamanho];
           Arrays.fill(array, caracter);
           return new String(array);
        }
        return "";
    }
}
