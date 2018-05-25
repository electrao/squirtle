
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main{
    
    public static void main(String [] args){
    Comerciante c1 = new Comerciante();
    JavaFatura j1 = new JavaFatura();
    
    c1.setNome("susi");
    c1.setEmail("123@gmail.com");
    c1.setNif(123456789);
    c1.setMorada("susi");
    c1.setPassword("susi");
    j1.addConsumidor(c1);
    
    Comerciante c2 = new Comerciante();
    c2.setNome("tone");
    c2.setEmail("tone@gmail.com");
    c2.setNif(123456789);
    c2.setMorada("tone");
    c2.setPassword("tone");
    j1.addConsumidor(c2);
    
    }
}
