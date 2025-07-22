package HarryPotter.HarryPotter.exceptions;

public class BruxoNaoEncontradoException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Bruxo nao encontrado";
    }
}
