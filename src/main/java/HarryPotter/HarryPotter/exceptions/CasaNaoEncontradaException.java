package HarryPotter.HarryPotter.exceptions;

public class CasaNaoEncontradaException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Casa nao encontrada";
    }
}
