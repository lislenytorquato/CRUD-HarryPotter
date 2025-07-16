package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.exceptions.BruxoException;
import HarryPotter.HarryPotter.service.BruxoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bruxo")
public class BruxoController {

    BruxoService bruxoService;

    public BruxoController(BruxoService bruxoService){
        this.bruxoService = bruxoService;
    }

    @PostMapping
    public ResponseEntity<BruxoResponseDto> criarBruxo(@RequestBody BruxoRequestDto bruxoRequestDto) throws BruxoException {
        BruxoResponseDto bruxoResponseDto = bruxoService.criarBruxo(bruxoRequestDto);
        return new ResponseEntity<>(bruxoResponseDto, HttpStatus.CREATED);
    }
}
