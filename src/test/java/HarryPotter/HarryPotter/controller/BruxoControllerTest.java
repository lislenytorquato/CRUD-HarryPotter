package HarryPotter.HarryPotter.controller;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
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

import static HarryPotter.HarryPotter.helper.BruxoHelper.*;
import static HarryPotter.HarryPotter.helper.BruxoHelper.ID_BRUXO;

@ExtendWith(MockitoExtension.class)
public class BruxoControllerTest {

    @InjectMocks
    BruxoController controller;

    @Mock
    BruxoService service;

    @DisplayName("1- deve criar bruxo e retornar ResponseEntity Com o ResponseDto")
    @Test
    void deveCriarBruxoERetornarResponseEntityComOResponseDto()  {
        BruxoRequestDto bruxoRequestDtoGrifinoria = criarBruxoRequestDtoGrifinoria();
        BruxoResponseDto bruxoResponseDto = criarBruxoResponseDtoGrifinoria();

        Mockito.when(service.criarBruxo(bruxoRequestDtoGrifinoria)).thenReturn(bruxoResponseDto);

        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = controller.criar(bruxoRequestDtoGrifinoria);

        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());

    }

    @DisplayName("2-deve mostrar informações de um bruxo específico")
    @Test
    void deveMostrarInformacoesDeUmBruxoEspecifico() throws BruxoNaoEncontradoException {
        Mockito.when(service.mostrarInformacoes(ID_BRUXO)).thenReturn(INFORMACAO_GRIFINORIA);
        ResponseEntity<String> informacoes = controller.mostrarInformacoes(ID_BRUXO);

        Assertions.assertEquals(HttpStatus.OK, informacoes.getStatusCode());

    }

    @DisplayName("3-deve lancar feitico de um bruxo especifico")
    @Test
    void deveLancarFeiticoDeUmBruxoEspecifico() throws BruxoNaoEncontradoException {
        Mockito.when(service.lancaFeitico(ID_BRUXO)).thenReturn(FEITICO_GRIFINORIA);
        ResponseEntity<String> feiticoLancado = controller.lancarFeitico(ID_BRUXO);

        Assertions.assertEquals(HttpStatus.OK, feiticoLancado.getStatusCode());

    }
    @DisplayName("4- deve listar bruxos")
    @Test
    void deveListarBruxos(){

        List<BruxoResponseDto> listaBruxosResponseDto = List.of(criarBruxoResponseDtoGrifinoria(), criarBruxoResponseDtoSonserina());
        Mockito.when(service.listarBruxos()).thenReturn(listaBruxosResponseDto);
        ResponseEntity<List<BruxoResponseDto>> listaDeBruxos = controller.listar();

        Assertions.assertEquals(HttpStatus.OK, listaDeBruxos.getStatusCode());

    }
    @DisplayName("5- deve deletar bruxo")
    @Test
    void deveDeletarBruxo(){


        Mockito.doNothing().when(service).deletarBruxo(ID_BRUXO);

        ResponseEntity<Void> bruxoDeletado = controller.deletar(ID_BRUXO);

        Mockito.verify(service,Mockito.times(1)).deletarBruxo(ID_BRUXO);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,bruxoDeletado.getStatusCode());


    }
    @DisplayName("6- deve atualizar bruxo")
    @Test
    void deveAtualizarBruxo() throws BruxoNaoEncontradoException {
        BruxoResponseDto bruxoResponseDto = criarBruxoResponseDtoSonserina();
        BruxoRequestDto bruxoRequestDto = criarBruxoRequestDtoSonserina();

        Mockito.when(service.atualizarBruxo(ID_BRUXO,bruxoRequestDto)).thenReturn(bruxoResponseDto);

        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = controller.atualizar(ID_BRUXO, bruxoRequestDto);

        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());
    }



}
