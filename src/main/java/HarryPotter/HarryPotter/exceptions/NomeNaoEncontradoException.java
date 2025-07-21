package HarryPotter.HarryPotter.exceptions;

public class NomeNaoEncontradoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Nome nao encontrado";
    }
}
