package modelo.persistencia;

public class DadosException extends Exception{

    public DadosException(String string) {
        super(string);
    }

    public DadosException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
