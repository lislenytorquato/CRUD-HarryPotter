package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.service.BruxoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bruxo")
public class BruxoController {

    private final BruxoService bruxoService;

    public BruxoController(BruxoService bruxoService){
        this.bruxoService = bruxoService;
    }

    @PostMapping
    public ResponseEntity<BruxoResponseDto> criar(@Valid @RequestBody BruxoRequestDto bruxoRequestDto) {
        BruxoResponseDto bruxoResponseDto = bruxoService.criarBruxo(bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BruxoResponseDto>> listar(){
        List<BruxoResponseDto> listaBruxoResponseDto = bruxoService.listarBruxos();
        return new ResponseEntity<>(listaBruxoResponseDto,HttpStatus.OK);
    }

    @PutMapping("/{idBruxo}")
    public ResponseEntity<BruxoResponseDto> atualizar(@PathVariable Long idBruxo, @Valid @RequestBody BruxoRequestDto bruxoRequestDto) {
        BruxoResponseDto bruxoResponseDto = bruxoService.atualizarBruxo(idBruxo,bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto,HttpStatus.OK);
    }
    @DeleteMapping("/{idBruxo}")
    public ResponseEntity<Void> deletar(@PathVariable Long idBruxo){
        bruxoService.deletarBruxo(idBruxo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mostrar-informacoes/{idBruxo}")
    public ResponseEntity<String> mostrarInformacoes(@PathVariable Long idBruxo) {
        String informacoes = bruxoService.mostrarInformacoes(idBruxo);
        return new ResponseEntity<>(informacoes,HttpStatus.OK);
    }

    @GetMapping("/lancar-feitico/{idBruxo}")
    public ResponseEntity<String> lancarFeitico(@PathVariable Long idBruxo) {
        String feiticoLancado = bruxoService.lancaFeitico(idBruxo);
        return new ResponseEntity<>(feiticoLancado,HttpStatus.OK);
    }



}
