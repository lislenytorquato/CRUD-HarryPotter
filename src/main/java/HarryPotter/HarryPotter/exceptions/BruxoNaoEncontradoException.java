package HarryPotter.HarryPotter.exceptions;

public class BruxoNaoEncontradoException extends Exception{
    @Override
    public String getMessage() {
        return "Bruxo nao encontrado";
    }
}
