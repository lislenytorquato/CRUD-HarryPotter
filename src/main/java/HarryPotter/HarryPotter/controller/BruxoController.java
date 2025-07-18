package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoException;
import HarryPotter.HarryPotter.service.BruxoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bruxo")
public class BruxoController {

    BruxoService bruxoService;

    public BruxoController(BruxoService bruxoService){
        this.bruxoService = bruxoService;
    }

    @PostMapping
    public ResponseEntity<BruxoResponseDto> criarBruxo(@RequestBody BruxoRequestDto bruxoRequestDto) {
        BruxoResponseDto bruxoResponseDto = bruxoService.criarBruxo(bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BruxoResponseDto>> listarBruxos(){
        List<BruxoResponseDto> listaBruxoResponseDto = bruxoService.listarBruxos();
        return new ResponseEntity<>(listaBruxoResponseDto,HttpStatus.OK);
    }

//    @PutMapping("/casa/{casaBruxo}/id/{idBruxo}")
//    public ResponseEntity<BruxoResponseDto> atualizarBruxo(@PathVariable CasaEnum casaBruxo, @PathVariable Long idBruxo, @RequestBody BruxoRequestDto bruxoRequestDto) throws BruxoException {
//        BruxoResponseDto bruxoResponseDto = bruxoService.atualizarBruxo(casaBruxo,idBruxo,bruxoRequestDto);
//        return new ResponseEntity<>(bruxoResponseDto,HttpStatus.CREATED);
//    }

    @GetMapping("/mostrar-informacoes/casa/{casaBruxo}/id/{idBruxo}")
    public ResponseEntity<String> mostrarInformacoes(@PathVariable CasaEnum casaBruxo,@PathVariable Long idBruxo) throws BruxoException {
        String informacoes = bruxoService.mostrarInformacoes(casaBruxo,idBruxo);
        return new ResponseEntity<>(informacoes,HttpStatus.OK);
    }

    @GetMapping("/lancar-feitico/casa/{casaBruxo}/id/{idBruxo}")
    public ResponseEntity<String> lancarFeitico(@PathVariable CasaEnum casaBruxo,@PathVariable Long idBruxo) throws BruxoException {
        String feiticoLancado = bruxoService.lancaFeitico(casaBruxo,idBruxo);
        return new ResponseEntity<>(feiticoLancado,HttpStatus.OK);
    }

    @DeleteMapping("/casa/{casaBruxo}/id/{idBruxo}")
    public ResponseEntity<Void> deletarBruxo(@PathVariable CasaEnum casaBruxo,@PathVariable Long idBruxo){
        bruxoService.deletarBruxo(casaBruxo,idBruxo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
