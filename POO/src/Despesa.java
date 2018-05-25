
/**
 * Write a description of class Despesa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Despesa{
    public static final String saude = "saude";
    public static final String energia = "energia";
    public static final String transportes = "transportes";
    public static final String educacao = "educacao";
    public static final String restauracao = "restauraçao";
    public static final String outros = "outros";
    private Consumidor consumidor;
    private Comerciante comerciante;
    private boolean estadoDaDespesa; //diz se a despesa ja foi validada pelo consumidor
    
    public boolean getEstadoDaDespesa(){
        return this.estadoDaDespesa;
    }
    
    public void setEstadoDaDespesa(boolean e){
        this.estadoDaDespesa = e;
    }
    
    public static String getDeducao(){
        int valores=0;
        switch(valores){
            case 1: 
                return saude;
            case 2: 
                return energia;
            case 3: 
                return transportes;
            case 4: 
                return educacao;
            case 5:
                return restauracao;
            case 6: 
                return outros;
            default : 
                return outros;    
            }
    } 
    
    public static float getPercentagemDedu(String estado){
        switch(estado){
            case saude: return 0.6f;
            case energia: return 0.6f;
            case transportes: return 0.6f;
            case educacao: return 0.6f;
            case restauracao: return 0.6f;
            default : return 0.1f;
        }
    }
    
    //
    public float calculaDeducaoContribuinte(){
        float resultado=0;
        if(comerciante.getSingular() == true && estadoDaDespesa ==true ){ //se so tiver uma ativi economica 
            adicionaDespesa();
        }
        else{
            contribuinteTemDeValidar();
        } 
        return resultado;
    }
    
    /**Verificar o tipo de atividade da empresa e calcular deduçao
    * fiscal para o consumidor
    */
    private void adicionaDespesa(){
        
    }
    
    /**o contribuinte devera classificar de acordo 
     * com a natureza da despesa so depois  que podemos calcular 
     * o valor da deducao fiscal 
     */
    private void contribuinteTemDeValidar(){
        
    }
    
    
    /**
       Funçao para calcular deduçao das despesas de uma empresa
       */
    public float calculaDeducaoComerciante(){
        float resultado=0;
        if(comerciante.getEDoInterior() == true && estadoDaDespesa ==true){ //se so tiver uma ativi economica 
            adicionaDespesa();
        }
        else{
            adicionaDespesaEmpresaLitoral();
            
        } 
        return resultado;
    }
    
    
    private void adicionaDespesaEmpresaLitoral(){
   
    }
}