package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.exceptions.BruxoException;
import HarryPotter.HarryPotter.helper.BruxoHelper;
import HarryPotter.HarryPotter.service.BruxoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BruxoControllerTest {

    @InjectMocks
    BruxoController bruxoController;

    @Mock
    BruxoService bruxoService;

    @DisplayName("criar bruxo e retornar ResponseEntity Com o ResponseDto")
    @Test
    void criarBruxoERetornarResponseEntityComOResponseDto()  {
        BruxoRequestDto bruxoRequestDtoGrifinoria = BruxoHelper.criarBruxoRequestDtoGrifinoria();
        BruxoResponseDto bruxoResponseDto = BruxoHelper.criarBruxoResponseDtoGrifinoria();

        Mockito.when(bruxoService.criarBruxo(bruxoRequestDtoGrifinoria)).thenReturn(bruxoResponseDto);

        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = bruxoController.criarBruxo(bruxoRequestDtoGrifinoria);

        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());

    }


}
