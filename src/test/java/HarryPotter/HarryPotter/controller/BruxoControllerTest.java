//package HarryPotter.HarryPotter.controller;
//
//import HarryPotter.HarryPotter.dto.BruxoRequestDto;
//import HarryPotter.HarryPotter.dto.BruxoResponseDto;
//import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
//import HarryPotter.HarryPotter.helper.BruxoHelper;
//import HarryPotter.HarryPotter.service.BruxoService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static HarryPotter.HarryPotter.helper.BruxoHelper.*;
//import static HarryPotter.HarryPotter.helper.BruxoHelper.CASA_BRUXO_SONSERINA;
//import static HarryPotter.HarryPotter.helper.BruxoHelper.ID_BRUXO;
//
//@ExtendWith(MockitoExtension.class)
//public class BruxoControllerTest {
//
//    @InjectMocks
//    BruxoController bruxoController;
//
//    @Mock
//    BruxoService bruxoService;
//
//    @DisplayName("1- deve criar bruxo e retornar ResponseEntity Com o ResponseDto")
//    @Test
//    void deveCriarBruxoERetornarResponseEntityComOResponseDto()  {
//        BruxoRequestDto bruxoRequestDtoGrifinoria = criarBruxoRequestDtoGrifinoria();
//        BruxoResponseDto bruxoResponseDto = criarBruxoResponseDtoGrifinoria();
//
//        Mockito.when(bruxoService.criarBruxo(bruxoRequestDtoGrifinoria)).thenReturn(bruxoResponseDto);
//
//        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = bruxoController.criarBruxo(bruxoRequestDtoGrifinoria);
//
//        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());
//
//    }
//
//    @DisplayName("2-deve mostrar informações de um bruxo específico")
//    @Test
//    void deveMostrarInformacoesDeUmBruxoEspecifico() throws BruxoNaoEncontradoException {
//        Mockito.when(bruxoService.mostrarInformacoes(CASA_BRUXO_GRIFINORIA, ID_BRUXO)).thenReturn(INFORMACAO_GRIFINORIA);
//        ResponseEntity<String> informacoes = bruxoController.mostrarInformacoes(CASA_BRUXO_GRIFINORIA, ID_BRUXO);
//
//        Assertions.assertEquals(HttpStatus.OK, informacoes.getStatusCode());
//
//    }
//
//    @DisplayName("3-deve lancar feitico de um bruxo especifico")
//    @Test
//    void deveLancarFeiticoDeUmBruxoEspecifico() throws BruxoNaoEncontradoException {
//        Mockito.when(bruxoService.lancaFeitico(CASA_BRUXO_GRIFINORIA, ID_BRUXO)).thenReturn(FEITICO_GRIFINORIA);
//        ResponseEntity<String> feiticoLancado = bruxoController.lancarFeitico(CASA_BRUXO_GRIFINORIA, ID_BRUXO);
//
//        Assertions.assertEquals(HttpStatus.OK, feiticoLancado.getStatusCode());
//
//    }
//    @DisplayName("4- deve listar bruxos")
//    @Test
//    void deveListarBruxos(){
//
//        List<BruxoResponseDto> listaBruxosResponseDto = List.of(criarBruxoResponseDtoGrifinoria(), criarBruxoResponseDtoSonserina());
//        Mockito.when(bruxoService.listarBruxos()).thenReturn(listaBruxosResponseDto);
//        ResponseEntity<List<BruxoResponseDto>> listaDeBruxos = bruxoController.listarBruxos();
//
//        Assertions.assertEquals(HttpStatus.OK, listaDeBruxos.getStatusCode());
//
//    }
//    @DisplayName("5- deve deletar bruxo")
//    @Test
//    void deveDeletarBruxo(){
//
//
//        Mockito.doNothing().when(bruxoService).deletarBruxo(CASA_BRUXO_GRIFINORIA, ID_BRUXO);
//
//        ResponseEntity<Void> bruxoDeletado = bruxoController.deletarBruxo(CASA_BRUXO_GRIFINORIA, ID_BRUXO);
//
//        Mockito.verify(bruxoService,Mockito.times(1)).deletarBruxo(CASA_BRUXO_GRIFINORIA, ID_BRUXO);
//        Assertions.assertEquals(HttpStatus.NO_CONTENT,bruxoDeletado.getStatusCode());
//
//
//    }
//    @DisplayName("6- deve atualizar bruxo")
//    @Test
//    void deveAtualizarBruxo() throws BruxoNaoEncontradoException {
//        BruxoResponseDto bruxoResponseDto = criarBruxoResponseDtoSonserina();
//        BruxoRequestDto bruxoRequestDto = criarBruxoRequestDtoSonserina();
//
//        Mockito.when(bruxoService.atualizarBruxo(CASA_BRUXO_SONSERINA, ID_BRUXO,bruxoRequestDto)).thenReturn(bruxoResponseDto);
//
//        ResponseEntity<BruxoResponseDto> bruxoResponseDtoResponseEntity = bruxoController.atualizarBruxo(CASA_BRUXO_SONSERINA, ID_BRUXO, bruxoRequestDto);
//
//        Assertions.assertEquals(HttpStatus.CREATED, bruxoResponseDtoResponseEntity.getStatusCode());
//    }
//
//
//
//}
