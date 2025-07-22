package HarryPotter.HarryPotter.exceptions.exceptionshandler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException ex) {

        List<FieldError> listaDeCampoDeErro = ex.getBindingResult().getFieldErrors();
        Map<String, String> erros = new HashMap<>();

        listaDeCampoDeErro.forEach(erro ->{
            String campo = erro.getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        return ResponseEntity.badRequest().body(erros);
    }
}
