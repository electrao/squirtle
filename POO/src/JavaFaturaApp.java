import java.io.IOException;
import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Exceptions.*;

/**
 * @author Grupo 77
 */
public class JavaFaturaApp{
   
    private static JavaFatura fat;
    private static Menu menu_logado, menu_principal, 
    menu_registo, menu_comerciante, menu_consumidor,
    menu_consumidor_registado, menu_cria_fatura;
   //menu ocnsumidor registado may not be necessary
    private JavaFaturaApp(){}
    
    /**
     * Função que faz executar a aplicação J-Fatura.
     */
    public static void main(String[] args) {
        String file_name = "fat_estado.obj";
        carregarMenus();
        initApp(file_name);
        apresentarMenu();
        atualizarApp(file_name);
        System.out.println("Aplicação encerrada.");
    }
    
    /**
     * Apresenta o menu principal.
     */
    private static void apresentarMenu(){
        int running = 1;
        System.out.print("\f");
        do {
            if(fat.getUtilizador() != null){
                menu_logado.executa();
                switch(menu_logado.getOpcao()){
                    case 1: menu();
                            break;
                    case 2: encerrarSessao();
                            break;
                    case 0: running = 0;
                }

            }
            else{
                menu_principal.executa();
                switch (menu_principal.getOpcao()) {
                    case 1: registo();
                            break;
                    case 2: iniciarSessao();
                            break;
                    case 0: running = 0;
                }
            }
        } while (running!=0);
    }
    
    /**
     * Atualiza o estado da aplicação. 
     * @param fich
     */
    static private void atualizarApp(String fich)
    {
        try {
            fat.gravaObj(fich);
            fat.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Atenção! Os seus dados nao ficaram gravados.");
            System.out.println(e);
        }
    }
   
    /**
     * Apresenta o Menu consoante o tipo de utilizador com sessão iniciada.
     */
    private static void menu(){

        if(fat.getUtilizador() == null)
            running_menu_consumidor();
        else{
            Contribuinte cont = fat.getUtilizador();
            if(cont.getClass().getSimpleName().equals("Comerciante"))
                running_menu_comerciante();
            else running_menu_consumidor_registado();
        }
    }
    
    /**
     * Executar o menu para utilizadores não registados na JavaFaturaApp.
     */
     private static void running_menu_consumidor(){
        do{
            menu_consumidor.executa();
            switch(menu_consumidor.getOpcao()){
                case 1: //consultarImoveisTipo();
                        break;
                case 2: //habitaveisPreco();
                        break;
                case 3: //imoveisVendedores();
                        break;
            }
        }while(menu_consumidor.getOpcao()!=0);
    }
    
    /**
     * Executar menu para comerciantes.
     */
    private static void running_menu_comerciante(){
        do{
            menu_comerciante.executa();
            switch(menu_comerciante.getOpcao()){
                case 1: adicionaFatura();
                        break;
                case 2: //consultarImoveis();
                        break;
                case 3: //alterarEstado();
                        break;
                case 4: //topConsultados();
                        break;
                case 5: //consultarImoveisTipo();
                        break;
                case 6: //habitaveisPreco();
                        break;
                case 7: //imoveisVendedores();
                        break;   
                case 8: //running_menu_leilao_vendedor();
                        break;
            }
        }while(menu_comerciante.getOpcao()!=0);
    }
    
    private static void adicionaFatura(){
        Fatura fatura = criaFatura();
        if(fatura!=null){
            try{
                fat.registaFatura(fatura);
            }
            catch(FaturaExisteException | SemAutorizacaoException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private static LocalDate lerData(String msg) {
        Scanner input = new Scanner(System.in);
        String aux;
        LocalDate data;

        System.out.println(msg);
        aux = input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            data = LocalDate.parse(aux, formatter);
        }
        catch (DateTimeParseException e) {
            System.out.println("Formato da data incorreto\n");
            data = lerData(msg);
        }
        finally {
            input.close();
        }

        return data;
    }
    
    /**
     * Criar uma fatura para ser adicionada à au utilizador.
     * @return 
     */
    private static Fatura criaFatura(){
        Fatura fatura = null;
        Scanner is = new Scanner(System.in);
        
        menu_cria_fatura.executa();
        if(menu_cria_fatura.getOpcao()!=0){
            Integer nifC, nifE;
            String emitente, descriçao;
            LocalDate date;
            float valor;
            int idFatura;
            boolean fatValida;
        
            nifE = fat.getUtilizador().getNif();
            emitente = fat.getUtilizador().getNome();
            date = lerData("Data de emissao (YYYY-MM-DD): ");
            System.out.print("NIF do consumidor: ");
            nifC = is.nextInt();
            is.nextLine();
            System.out.print("Descricao da compra: ");
            is.nextLine();
            System.out.print("Valor da compra (virgula): ");
            valor = is.nextFloat();
            is.nextLine();
            System.out.print("ID da fatura: ");
            idFatura = is.nextInt();
            is.nextLine();
            
        }
        return fatura;
     }
    
    /**
     * Executar menu para consumidores ja registados.
     */
    private static void running_menu_consumidor_registado(){
        do{
            menu_consumidor_registado.executa();
            switch(menu_consumidor_registado.getOpcao()){
                case 1: //consultarImoveisTipo();
                        break;
                case 2: //habitaveisPreco();
                        break;
                case 3: //imoveisVendedores();
                        break;
                case 4: //favoritoImovel();
                        break;
                case 5: //consultarFavoritos();
                        break;
            }
        }while(menu_consumidor_registado.getOpcao()!=0);
    }
    
    /**
     * Carrega todos os menus para apresentar.
     */
    private static void carregarMenus(){
        String [] menu0 = {"Menu", 
                            "Fechar sessão"};
        String [] menu1 = {"Registar utilizador", 
                            "Iniciar sessão"};
        String [] menu2 = {"Comerciante", 
                            "Consumidor"};
        String [] menu3 = {"Criar fatura/Associar tipo",
                            "Corrigir tipo da despesa",
                            "Listagem das faturas por contribuinte num intervalo de datas",
                            "Listagem das faturas por contribuinte por valor decrescente de despesa",
                            "Total faturado num periodo",
                            "Opçao5",
                            "Opçao6",
                            "Opçao7"};                     
         String [] menu4 = {"Visualizar faturas",
                             "Validar",
                             "Opçao3"};
    
         String [] menu5 = {"Opçao1",
                             "Opçao2",
                             "Opçao3",
                             "Opçao4",
                             "Opçao5",};
                             
         String [] menu6 = {"Saude",
                             "Educacao",
                             "Transportes",
                             "Energia",
                             "Restauracao",};
                             
         String [] menu7 = {"Listar faturas de determinada empresa por data ou valor",
                             "10 contribuintes que mais gastam",
                             "Empresas que mais faturam e respetiva deducao",
                             "opcao",
                             "opcao",};
    //menu_consumidor_registado may not be necessary;
        
    
        menu_logado = new Menu(menu0);
        menu_principal = new Menu("-Bem vindo ao J-Fatura-", menu1);
        menu_registo = new Menu(menu2);
        menu_comerciante = new Menu(menu3);
        menu_consumidor = new Menu(menu4);
        menu_consumidor_registado = new Menu(menu5);
        menu_cria_fatura = new Menu(menu6);
    }
    
    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     * @param fich
     */
    private static void initApp(String fich){
        try {
            fat = JavaFatura.leObj(fich);
        }
        catch (IOException e) {
            fat = new JavaFatura();
            System.out.println("Impossível carregar os dados guardados!\nHouve um erro de leitura.");
        }
        catch (ClassNotFoundException e) {
            fat = new JavaFatura();
            System.out.println("Impossível carregar os dados guardados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            fat = new JavaFatura();
            System.out.println("Impossível carregar os dados guardados!\nErro de formato.");
        }
    }
    
    /**
     * Registo na JavaFaturaApp.
     */
    private static void registo(){
        Contribuinte cont;
        Scanner is = new Scanner(System.in);

        menu_registo.executa();
        if(menu_registo.getOpcao() !=0){
            String nome,email,morada, password;
            Integer nif=0;
            System.out.print("Nome: ");
            nome = is.nextLine();
            System.out.print("Nif: ");
            nif = is.nextInt();
            is.nextLine();
            System.out.print("Email: ");
            email = is.nextLine();
            System.out.print("Password: ");
            password = is.nextLine();
            System.out.print("Morada: ");
            morada = is.nextLine();

            switch(menu_registo.getOpcao()){
                case 1: cont = new Comerciante(nome,nif,email,morada,password,false,null,false);
                        break;
                case 2: cont = new Consumidor(nome,nif,email,morada,password,0,null,0,null);
                        break;
                default: cont = new Consumidor();
            }
            try{
                fat.registarContribuinte(cont);
            }
            catch(ContribuinteExistenteException e){
                System.out.println("Este contribuinte já existe!");
            }
        }
        else System.out.println("Registo cancelado!");
        is.close();
    }
    
    /**
     * Inicio de sessão na JavaFaturaApp.
     */
    private static void iniciarSessao(){
        Scanner is = new Scanner(System.in);
        String password;
        Integer nif;
        System.out.print("NIF: ");
        nif = is.nextInt();
        is.nextLine();
        System.out.print("Password: ");
        password = is.nextLine();

        try{
            fat.iniciaSessao(nif,password);
        }
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }

        is.close();
    }
    
    /**
     * Encerrar sessão na JavaFaturaApp.
     */
    private static void encerrarSessao(){
        fat.encerraSessao();
    }
}
