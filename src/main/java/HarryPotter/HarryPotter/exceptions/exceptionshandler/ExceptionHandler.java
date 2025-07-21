package HarryPotter.HarryPotter.exceptions.exceptionshandler;

import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEIgualATabelaException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEncontradaException;
import HarryPotter.HarryPotter.exceptions.NomeNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> bruxoNaoEncontradoExceptionHandler(BruxoNaoEncontradoException bruxoNaoEncontradoException){
        return new ResponseEntity<>(bruxoNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> nomeNaoEncontradoExceptionHandler(NomeNaoEncontradoException nomeNaoEncontradoException){
        return new ResponseEntity<>(nomeNaoEncontradoException.getMessage(),HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> casaNaoEncontradaExceptionHandler(CasaNaoEncontradaException casaNaoEncontradaException){
        return new ResponseEntity<>(casaNaoEncontradaException.getMessage(),HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> casaNaoEIgualATabelaExceptionHandler(CasaNaoEIgualATabelaException casaNaoEIgualATabelaException){
        return new ResponseEntity<>(casaNaoEIgualATabelaException.getMessage(),HttpStatus.CONFLICT);
    }


}
