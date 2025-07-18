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

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BruxoControllerTest {

    @InjectMocks
    BruxoController bruxoController;

    @Mock
    BruxoService bruxoService;

    @DisplayName("1- deve criar bruxo e retornar ResponseEntity Com o ResponseDto")
    @Test
    void deveCriarBruxoERetornarResponseEntityComOResponseDto()  {
        BruxoRequestDto bruxoRequestDtoGrifinoria = BruxoHelper.criarBruxoRequestDtoGrifinoria();
        BruxoResponseDto bruxoResponseDto = BruxoHelper.criarBruxoResponseDtoGrifinoria();

        Mockito.when(bruxoService.criarBruxo(bruxoRequestDtoGrifinoria)).thenReturn(bruxoResponseDto);

        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = bruxoController.criarBruxo(bruxoRequestDtoGrifinoria);

        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());

    }

    @DisplayName("2-deve mostrar informações de um bruxo específico")
    @Test
    void deveMostrarInformacoesDeUmBruxoEspecifico() throws BruxoException {
        Mockito.when(bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO)).thenReturn(BruxoHelper.INFORMACAO_GRIFINORIA);
        ResponseEntity<String> informacoes = bruxoController.mostrarInformacoes(BruxoHelper.CASA_BRUXO_GRIFINORIA, BruxoHelper.ID_BRUXO);

        Assertions.assertEquals(HttpStatus.OK, informacoes.getStatusCode());

    }

    @DisplayName("3-deve lancar feitico de um bruxo especifico")
    @Test
    void deveLancarFeiticoDeUmBruxoEspecifico() throws BruxoException {
        Mockito.when(bruxoService.lancaFeitico(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO)).thenReturn(BruxoHelper.FEITICO_GRIFINORIA);
        ResponseEntity<String> feiticoLancado = bruxoController.lancarFeitico(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO);

        Assertions.assertEquals(HttpStatus.OK, feiticoLancado.getStatusCode());

    }
    @DisplayName("4- deve listar bruxos")
    @Test
    void deveListarBruxos(){

        List<BruxoResponseDto> listaBruxosResponseDto = List.of(BruxoHelper.criarBruxoResponseDtoGrifinoria(), BruxoHelper.criarBruxoResponseDtoSonserina());
        Mockito.when(bruxoService.listarBruxos()).thenReturn(listaBruxosResponseDto);
        ResponseEntity<List<BruxoResponseDto>> listaDeBruxos = bruxoController.listarBruxos();

        Assertions.assertEquals(HttpStatus.OK, listaDeBruxos.getStatusCode());

    }
    @DisplayName("5- deve deletar bruxo")
    @Test
    void deveDeletarBruxo(){


        Mockito.doNothing().when(bruxoService).deletarBruxo(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO);

        ResponseEntity<Void> bruxoDeletado = bruxoController.deletarBruxo(BruxoHelper.CASA_BRUXO_GRIFINORIA, BruxoHelper.ID_BRUXO);

        Mockito.verify(bruxoService,Mockito.times(1)).deletarBruxo(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,bruxoDeletado.getStatusCode());


    }



}
