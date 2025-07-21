package HarryPotter.HarryPotter.exceptions;

public class CasaNaoEIgualATabelaException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Casa digitada nao é igual a casa escolhida";
    }
}
