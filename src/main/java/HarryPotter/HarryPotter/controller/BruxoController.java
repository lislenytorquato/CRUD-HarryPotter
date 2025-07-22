package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.service.BruxoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bruxo")
public class BruxoController {

    BruxoService service;

    public BruxoController(BruxoService bruxoService){
        this.service = bruxoService;
    }

    @PostMapping
    public ResponseEntity<BruxoResponseDto> criar(@RequestBody BruxoRequestDto bruxoRequestDto) {
        BruxoResponseDto bruxoResponseDto = service.criarBruxo(bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BruxoResponseDto>> listar(){
        List<BruxoResponseDto> listaBruxoResponseDto = service.listarBruxos();
        return new ResponseEntity<>(listaBruxoResponseDto,HttpStatus.OK);
    }

    @PutMapping("/{idBruxo}")
    public ResponseEntity<BruxoResponseDto> atualizar(@PathVariable Long idBruxo, @RequestBody BruxoRequestDto bruxoRequestDto) {
        BruxoResponseDto bruxoResponseDto = service.atualizarBruxo(idBruxo,bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto,HttpStatus.OK);
    }
    @DeleteMapping("/{idBruxo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idBruxo){
        service.deletarBruxo(idBruxo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mostrar-informacoes/{idBruxo}")
    public ResponseEntity<String> mostrarInformacoes(@PathVariable Long idBruxo) {
        String informacoes = service.mostrarInformacoes(idBruxo);
        return new ResponseEntity<>(informacoes,HttpStatus.OK);
    }

    @GetMapping("/lancar-feitico/{idBruxo}")
    public ResponseEntity<String> lancarFeitico(@PathVariable Long idBruxo) {
        String feiticoLancado = service.lancaFeitico(idBruxo);
        return new ResponseEntity<>(feiticoLancado,HttpStatus.OK);
    }



}
