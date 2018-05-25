import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import Exceptions.*;

/**
 * Write a description of class JavaFatura here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFatura implements Serializable{
    private Contribuinte consumidor;
    private Contribuinte comerciante;
    private Map<Integer,Fatura> faturas;
    private Map<Integer,Contribuinte> contribuintes;
    private Contribuinte utilizador;
    private Contribuinte contribuinteLogado;
    private Despesa despesa;
    
    private Map<Integer,Contribuinte> comerciantes;
    private Map<Integer,Contribuinte> consumidores; 
    private Map<Integer,Contribuinte> admins;
    /**
     * Construtor vazio de uma JavaFatura.
     */
    public JavaFatura(){
        this.faturas = new TreeMap<Integer,Fatura>();
        this.contribuintes = new TreeMap<Integer,Contribuinte>();
    }
    
    public Contribuinte getContribuinteLogado(){
        return this.contribuinteLogado;
    }
    
    public Map<Integer, Fatura> getFaturas(){
       Map <Integer, Fatura> mapFaturas = new TreeMap<>(); 
       for(Integer codContribuinte: this.faturas.keySet()){
           Fatura c = this.faturas.get(codContribuinte); 
           mapFaturas.put(codContribuinte, c); //tenho de fazer clone para por c.clone()
       }
       
       return mapFaturas; 
    }
    
    public void set(Contribuinte contribuinteLogado){
        this.contribuinteLogado = contribuinteLogado;
    }
    
   /**
     * Método addCliente - Adiciona um cliente 
     * @param cliente
     */
    public void addConsumidor(Consumidor c){
        this.consumidores.put(c.getNif(), c);
    }
    
     /**
     * Método addMotorista - Adiciona um motorista
     * @param motorista
     */
    
    /**
     * Método addCliente - Adiciona um cliente 
     * @param cliente
     */
    public void addConsumidor(Comerciante c){
        this.comerciantes.put(c.getNif(), c);
    }
    
    
     /**
     * Método addAdmin - Adiciona um admin
     * @param admin
     *
    public void addAdmin(Admin admin){
        this.admins.put(admin.getEmail(), admin.clone());
    }*/
    
    public Contribuinte getUtilizador(){
       return this.utilizador;
   }
    
   public void setUtilizador(Contribuinte u){
       this.utilizador = u;
   }
   
    /**
     * Construtor parametrizado de uma JavaFatura.
     */
    public JavaFatura(Map<Integer,Fatura> faturas, 
    Map<Integer,Contribuinte> contribuintes,
    Integer nifLogged){
        this.faturas = faturas;
        this.contribuintes = contribuintes;
    }
    
       
    /**
     * Resgistar contribuinte
     */
    public void registarContribuinte(Contribuinte contribuinte) throws ContribuinteExistenteException{
        if(this.contribuintes.containsKey(contribuinte.getNif())){
            throw new ContribuinteExistenteException("O contribuinte ja existe!");
        }
        else{
            this.contribuintes.put(contribuinte.getNif(),contribuinte);
        }
    }
    
    /**
     * Adiciona faturas
     */
    public void adicionaFatura(Fatura i) {
      this.faturas.put(i.getIdFatura(),i);
   }
    
    /**
     * Registar um Imóvel na aplicação.
     * @param im
     */
    public void registaFatura(Fatura im) throws FaturaExisteException , SemAutorizacaoException {
        if(this.utilizador.getClass().getSimpleName().equals("Vendedor")){
            if(this.faturas.containsValue(im) == false) {
                this.faturas.put(im.getIdFatura(),im);
                Comerciante v1 = (Comerciante) this.utilizador;
                v1.adicionaFatura(im);
                //this.id++;
            }
            else throw new FaturaExisteException("Fatura já existe.");
        }
        else throw new SemAutorizacaoException("Apenas Vendedores estão autorizados.");
    }
    
    /**
     * Inicio de sessao na app
     */
    public void iniciaSessao(Integer nif, String password) throws SemAutorizacaoException{
        
        if(this.utilizador == null){
            
            if(contribuintes.containsKey(nif)){
                Contribuinte cont = contribuintes.get(nif);
                if(password.equals(cont.getPassword())){
                    utilizador = cont;
                }
                else{
                    throw new SemAutorizacaoException("Nome de utilizador ou password errados");
                }
            }
            else throw new SemAutorizacaoException("Nome de utilizador ou password errados");
        }
        else{
            throw new SemAutorizacaoException("A sua sessão já foi iniciada");
        }
    }
    
    /**
     * Fazer logout da app
     */
    public void encerraSessao(){
        this.utilizador= null;
    }
    
    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     * @param fich
     * @return
     */
    public static JavaFatura leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(fich));

        JavaFatura t = (JavaFatura) o.readObject();

        o.close();
        return t;
    }
    
    
  
   
   /**
     * Gravar o estado da aplicação num determinado ficheiro.
     * @param fich
     */
    public void gravaObj(String fich) throws IOException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(fich));
        o.writeObject(this);
        
        o.flush();
        o.close();
    }
    
    
    
    /**
     * Fazer um ficheiro de texto log com toda a informação na Imobiliária no momento em que é fechada.
     * @param f
     * @param ap
     */
    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n-------------------------- LOG --------------------------\n");
        fw.write(this.toString());
        fw.write("\n-------------------------- LOG --------------------------\n");
        fw.flush();
        fw.close();
    }
    
}

