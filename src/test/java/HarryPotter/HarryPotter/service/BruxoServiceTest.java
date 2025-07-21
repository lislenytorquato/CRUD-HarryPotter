package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.helper.BruxoHelper;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoGrifinoriaRepository;
import HarryPotter.HarryPotter.repository.BruxoSonserinaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BruxoServiceTest {

    @InjectMocks
    BruxoService bruxoService;

    @Mock
    BruxoMapper bruxoMapper;

    @Mock
    BruxoGrifinoriaRepository bruxoGrifinoriaRepository;

    @Mock
    BruxoSonserinaRepository bruxoSonserinaRepository;

    @DisplayName("1- deve criar bruxo como sonserina e retornar bruxoResponseDto")
    @Test
    void deveCriarBruxoComoSonserinaERetornarBruxoResponseDto() {

        BruxoRequestDto bruxoRequestDtoSonserina = BruxoHelper.criarBruxoRequestDtoSonserina();
        BruxoSonserina bruxoSonserina = BruxoHelper.criarBruxoSonserina();
        BruxoResponseDto bruxoResponseDto = BruxoHelper.criarBruxoResponseDtoSonserina();


        Mockito.when(bruxoMapper.bruxoRequestDtoToBruxoSonserinaEntity(bruxoRequestDtoSonserina)).thenReturn(bruxoSonserina);
        Mockito.when(bruxoSonserinaRepository.save(bruxoMapper.bruxoRequestDtoToBruxoSonserinaEntity(bruxoRequestDtoSonserina))).thenReturn(bruxoSonserina);
        Mockito.when(bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserina)).thenReturn(bruxoResponseDto);

        BruxoResponseDto bruxo = bruxoService.criarBruxo(bruxoRequestDtoSonserina);

        Assertions.assertEquals(CasaEnum.valueOf("SONSERINA"), bruxo.getCasa());
        Assertions.assertEquals(BruxoHelper.NOME_SONSERINA, bruxo.getNome());

    }

    @DisplayName("2- deve criar bruxo como grifinoria e retornar bruxoResponseDto")
    @Test
    void deveCriarBruxoComoGrifinoriaERetornarBruxoResponseDto() {

        BruxoRequestDto bruxoRequestDtoGrifinoria = BruxoHelper.criarBruxoRequestDtoGrifinoria();
        BruxoGrifinoria bruxoGrifinoria = BruxoHelper.criarBruxoGrifinoria();
        BruxoResponseDto bruxoResponseDto = BruxoHelper.criarBruxoResponseDtoGrifinoria();


        Mockito.when(bruxoMapper.bruxoRequestDtoToBruxoGrifinoriaEntity(bruxoRequestDtoGrifinoria)).thenReturn(bruxoGrifinoria);
        Mockito.when(bruxoGrifinoriaRepository.save(bruxoMapper.bruxoRequestDtoToBruxoGrifinoriaEntity(bruxoRequestDtoGrifinoria))).thenReturn(bruxoGrifinoria);
        Mockito.when(bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoria)).thenReturn(bruxoResponseDto);

        BruxoResponseDto bruxo = bruxoService.criarBruxo(bruxoRequestDtoGrifinoria);

        Assertions.assertEquals(CasaEnum.valueOf("GRIFINORIA"), bruxo.getCasa());
        Assertions.assertEquals(BruxoHelper.NOME_GRIFINORIA, bruxo.getNome());

    }
    @DisplayName("3- deve listar bruxos e retornar um lista de bruxos responseDto")
    @Test
    void develistarBruxosERetornarUmaListaDeBruxosResponseDto(){
        List<BruxoResponseDto> listaBruxoResponseDtoGrifinoria = List.of(BruxoHelper.criarBruxoResponseDtoGrifinoria());
        List<BruxoResponseDto> listaBruxoResponseDtoSonserina = List.of(BruxoHelper.criarBruxoResponseDtoSonserina());
        List<BruxoGrifinoria> listaBruxoGrifinoria = List.of(BruxoHelper.criarBruxoGrifinoria());
        List<BruxoSonserina> listaBruxoSonserina = List.of(BruxoHelper.criarBruxoSonserina());

        Mockito.when(bruxoGrifinoriaRepository.findAll()).thenReturn(listaBruxoGrifinoria);
        Mockito.when(bruxoSonserinaRepository.findAll()).thenReturn(listaBruxoSonserina);

        Mockito.when(bruxoMapper.listaBruxoGrifinoriaToListaResponseDto(listaBruxoGrifinoria)).thenReturn(listaBruxoResponseDtoGrifinoria);
        Mockito.when(bruxoMapper.listaBruxoSonserinaToListaResponseDto(listaBruxoSonserina)).thenReturn(listaBruxoResponseDtoSonserina);

        List<BruxoResponseDto> listaBruxoResponseDto = bruxoService.listarBruxos();

        Assertions.assertEquals(BruxoHelper.NOME_GRIFINORIA, listaBruxoResponseDto.get(0).getNome());
        Assertions.assertEquals(BruxoHelper.CASA_BRUXO_GRIFINORIA, listaBruxoResponseDto.get(0).getCasa());
        Assertions.assertEquals(BruxoHelper.NOME_SONSERINA, listaBruxoResponseDto.get(1).getNome());
        Assertions.assertEquals(BruxoHelper.CASA_BRUXO_SONSERINA, listaBruxoResponseDto.get(1).getCasa());

    }

    @DisplayName("4- deve mostrar informacoes do bruxo de sonserina")
    @Test
    void deveMostrarInformacoesDoBruxoDeSonserina() throws BruxoNaoEncontradoException {
        BruxoSonserina bruxoSonserina = BruxoHelper.criarBruxoSonserina();
        Mockito.when(bruxoSonserinaRepository.findById(BruxoHelper.ID_BRUXO)).thenReturn(Optional.of(bruxoSonserina));
        String informacoes = bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_SONSERINA, BruxoHelper.ID_BRUXO);

        Assertions.assertTrue(informacoes.contains(BruxoHelper.NOME_SONSERINA));
        Assertions.assertTrue(informacoes.contains(BruxoHelper.CASA_BRUXO_SONSERINA.toString()));
    }

    @DisplayName("5- deve lancar excecao quando nao encontrar bruxo de sonserina")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxoDeSonserina() throws BruxoNaoEncontradoException {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()->bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_SONSERINA,BruxoHelper.ID_BRUXO));
    }

    @DisplayName("6- deve mostrar informacoes do bruxo de grifinoria")
    @Test
    void deveMostrarInformacoesDoBruxoDeGrifinoria() throws BruxoNaoEncontradoException {
        BruxoGrifinoria bruxoGrifinoria = BruxoHelper.criarBruxoGrifinoria();
        Mockito.when(bruxoGrifinoriaRepository.findById(BruxoHelper.ID_BRUXO)).thenReturn(Optional.of(bruxoGrifinoria));
        String informacoes = bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_GRIFINORIA, BruxoHelper.ID_BRUXO);

        Assertions.assertTrue(informacoes.contains(BruxoHelper.NOME_GRIFINORIA));
        Assertions.assertTrue(informacoes.contains(BruxoHelper.CASA_BRUXO_GRIFINORIA.toString()));
    }

    @DisplayName("6- deve lancar excecao quando nao encontrar bruxo de grifinoria ao mostrar informacoes")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxoDeGrifinoriaAoMostrarInformacoes() throws BruxoNaoEncontradoException {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()->bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_SONSERINA,BruxoHelper.ID_BRUXO));
    }

    @DisplayName("7- deve lancar feitico do bruxo de sonserina")
    @Test
    void deveLancarFeiticoDoBruxoDeSonserina() throws BruxoNaoEncontradoException {
        BruxoSonserina bruxoSonserina = BruxoHelper.criarBruxoSonserina();
        Mockito.when(bruxoSonserinaRepository.findById(BruxoHelper.ID_BRUXO)).thenReturn(Optional.of(bruxoSonserina));
        String feiticoLancado = bruxoService.lancaFeitico(BruxoHelper.CASA_BRUXO_SONSERINA, BruxoHelper.ID_BRUXO);

        Assertions.assertTrue(feiticoLancado.contains(BruxoHelper.FEITICO_SONSERINA));
    }

    @DisplayName("8- deve lancar excecao quando nao encontrar bruxo de sonserina ao lancar feitico")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxoDeSonserinaAoLancarFeitico() throws BruxoNaoEncontradoException {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()->bruxoService.lancaFeitico(BruxoHelper.CASA_BRUXO_SONSERINA,BruxoHelper.ID_BRUXO));
    }

    @DisplayName("9- deve lancar feitico do bruxo de grifinoria")
    @Test
    void deveLancarFeiticoDoBruxoDeGrifinoria() throws BruxoNaoEncontradoException {
        BruxoGrifinoria bruxoGrifinoria = BruxoHelper.criarBruxoGrifinoria();
        Mockito.when(bruxoGrifinoriaRepository.findById(BruxoHelper.ID_BRUXO)).thenReturn(Optional.of(bruxoGrifinoria));
        String feiticoLancado = bruxoService.lancaFeitico(BruxoHelper.CASA_BRUXO_GRIFINORIA, BruxoHelper.ID_BRUXO);

        Assertions.assertTrue(feiticoLancado.contains(BruxoHelper.FEITICO_GRIFINORIA));
    }

    @DisplayName("10- deve lancar excecao quando nao encontrar bruxo de grifinoria ao lancar Feitico")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxoDeGrifinoriaAoLancarFeitico() throws BruxoNaoEncontradoException {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()->bruxoService.mostrarInformacoes(BruxoHelper.CASA_BRUXO_SONSERINA,BruxoHelper.ID_BRUXO));
    }

    @DisplayName("11-deve deletar bruxo de sonserina")
    @Test
    void deveDeletarBruxoDeSonserina(){

        Mockito.doNothing().when(bruxoSonserinaRepository).deleteById(BruxoHelper.ID_BRUXO);

        bruxoService.deletarBruxo(BruxoHelper.CASA_BRUXO_SONSERINA,BruxoHelper.ID_BRUXO);

        Mockito.verify(bruxoSonserinaRepository,Mockito.times(1)).deleteById(BruxoHelper.ID_BRUXO);

    }

    @DisplayName("12-deve deletar bruxo de grifinoria")
    @Test
    void deveDeletarBruxoDeGrifinoria(){

        Mockito.doNothing().when(bruxoGrifinoriaRepository).deleteById(BruxoHelper.ID_BRUXO);

        bruxoService.deletarBruxo(BruxoHelper.CASA_BRUXO_GRIFINORIA,BruxoHelper.ID_BRUXO);

        Mockito.verify(bruxoGrifinoriaRepository,Mockito.times(1)).deleteById(BruxoHelper.ID_BRUXO);

    }

    @DisplayName("13-deve lancar NomeNaoEncontradoException")
    @Test
    void deveLancarNomeNaoEncontradoException(){
        Assertions.assertThrows(()->bruxoService.atualizarBruxo())
}
