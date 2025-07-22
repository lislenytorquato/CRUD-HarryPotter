package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEncontradaException;
import HarryPotter.HarryPotter.exceptions.NomeNaoEncontradoException;
import HarryPotter.HarryPotter.helper.BruxoHelper;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.Bruxo;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoRepository;
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

import static HarryPotter.HarryPotter.helper.BruxoHelper.*;
import static HarryPotter.HarryPotter.helper.BruxoHelper.FEITICO_GRIFINORIA;
import static HarryPotter.HarryPotter.helper.BruxoHelper.ID_BRUXO;
import static HarryPotter.HarryPotter.helper.BruxoHelper.NOME_GRIFINORIA;
import static HarryPotter.HarryPotter.helper.BruxoHelper.criarBruxo;
import static HarryPotter.HarryPotter.helper.BruxoHelper.criarBruxoRequestDtoNomeNulo;

@ExtendWith(MockitoExtension.class)
public class BruxoServiceTest {

    @InjectMocks
    BruxoService bruxoService;

    @Mock
    BruxoMapper bruxoMapper;

    @Mock
    BruxoRepository bruxoRepository;

    @DisplayName("1- deve criar bruxo como sonserina e retornar bruxoResponseDto")
    @Test
    void deveCriarBruxoERetornarBruxoResponseDto() {

        BruxoRequestDto bruxoRequestDto = BruxoHelper.criarBruxoRequestDtoSonserina();
        Bruxo bruxo = criarBruxo(NOME_SONSERINA, CASA_BRUXO_SONSERINA);
        BruxoResponseDto bruxoResponseDto = BruxoHelper.criarBruxoResponseDtoSonserina();


        Mockito.when(bruxoMapper.requestDtoToBruxo(bruxoRequestDto)).thenReturn(bruxo);
        Mockito.when(bruxoRepository.save(bruxoMapper.requestDtoToBruxo(bruxoRequestDto))).thenReturn(bruxo);
        Mockito.when(bruxoMapper.bruxoToResponseDto(bruxo)).thenReturn(bruxoResponseDto);

        BruxoResponseDto response = bruxoService.criarBruxo(bruxoRequestDto);

        Assertions.assertEquals(CasaEnum.valueOf("SONSERINA"), response.getCasa());
        Assertions.assertEquals(NOME_SONSERINA, response.getNome());

    }

    @DisplayName("2- deve listar bruxos e retornar um lista de bruxos responseDto")
    @Test
    void develistarBruxosERetornarUmaListaDeBruxosResponseDto(){
        List<BruxoResponseDto> listaBruxoResponseDto = List.of(criarBruxoResponseDto(NOME_SONSERINA, CASA_BRUXO_SONSERINA), criarBruxoResponseDto(NOME_GRIFINORIA, CASA_BRUXO_GRIFINORIA));
       List<Bruxo> listaBruxo = List.of(criarBruxo(NOME_GRIFINORIA,CASA_BRUXO_GRIFINORIA),criarBruxo(NOME_SONSERINA,CASA_BRUXO_SONSERINA));

        Mockito.when(bruxoRepository.findAll()).thenReturn(listaBruxo);

        Mockito.when(bruxoMapper.listaDeBruxosToResponseDto(listaBruxo)).thenReturn(listaBruxoResponseDto);

        List<BruxoResponseDto> response = bruxoService.listarBruxos();

        Assertions.assertEquals(NOME_SONSERINA, response.get(0).getNome());
        Assertions.assertEquals(CASA_BRUXO_SONSERINA, response.get(0).getCasa());
        Assertions.assertEquals(NOME_GRIFINORIA, response.get(1).getNome());
        Assertions.assertEquals(CASA_BRUXO_GRIFINORIA, response.get(1).getCasa());


    }

    @DisplayName("3- deve mostrar informacoes do bruxo")
    @Test
    void deveMostrarInformacoesDoBruxoDeSonserina() {
        Bruxo bruxo = BruxoHelper.criarBruxo(NOME_SONSERINA,CASA_BRUXO_SONSERINA);
        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        String informacoes = bruxoService.mostrarInformacoes(ID_BRUXO);

        Assertions.assertTrue(informacoes.contains(NOME_SONSERINA));
        Assertions.assertTrue(informacoes.contains(CASA_BRUXO_SONSERINA.toString()));
    }

    @DisplayName("4- deve lancar excecao quando nao encontrar bruxo")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxo() {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()-> bruxoService.mostrarInformacoes(ID_BRUXO));
    }

    @DisplayName("5- deve lancar feitico do bruxo de grifinoria")
    @Test
    void deveLancarFeiticoDoBruxoDeGrifinoria() {
        Bruxo bruxo = criarBruxo(NOME_GRIFINORIA,CASA_BRUXO_GRIFINORIA);
        BruxoGrifinoria bruxoGrifinoria = criarBruxoGrifinoria();

        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        Mockito.when(bruxoMapper.bruxoToBruxoGrifinoria(bruxo)).thenReturn(bruxoGrifinoria);

        String feiticoLancado = bruxoService.lancaFeitico(ID_BRUXO);

        Assertions.assertTrue(feiticoLancado.contains(FEITICO_GRIFINORIA));
    }
    @DisplayName("6- deve lancar feitico do bruxo de sonserina")
    @Test
    void deveLancarFeiticoDoBruxoDeSonserina(){
        Bruxo bruxo= criarBruxo(NOME_SONSERINA,CASA_BRUXO_SONSERINA);
        BruxoSonserina bruxoSonserina = criarBruxoSonserina();

        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        Mockito.when(bruxoMapper.bruxoToBruxoSonserina(bruxo)).thenReturn(bruxoSonserina);

        String feiticoLancado = bruxoService.lancaFeitico(ID_BRUXO);

        Assertions.assertTrue(feiticoLancado.contains(FEITICO_SONSERINA));
    }

    @DisplayName("7- deve lancar excecao quando nao encontrar bruxo de grifinoria ao lancar Feitico")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarBruxoDeGrifinoriaAoLancarFeitico() throws BruxoNaoEncontradoException {
        Assertions.assertThrows(BruxoNaoEncontradoException.class,()-> bruxoService.lancaFeitico(ID_BRUXO));
    }

    @DisplayName("8-deve deletar bruxo")
    @Test
    void deveDeletarBruxo(){

        Bruxo bruxo= criarBruxo(NOME_SONSERINA,CASA_BRUXO_SONSERINA);
        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        Mockito.doNothing().when(bruxoRepository).delete(bruxo);

        bruxoService.deletarBruxo(ID_BRUXO);

        Mockito.verify(bruxoRepository,Mockito.times(1)).delete(bruxo);

    }

    @DisplayName("9-deve lancar NomeNaoEncontradoException")
    @Test
    void deveLancarNomeNaoEncontradoException() {

        BruxoRequestDto bruxoRequestDto = criarBruxoRequestDtoNomeNulo();
        Bruxo bruxo = criarBruxo(null, CASA_BRUXO_SONSERINA);
        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));

        Assertions.assertThrows(NomeNaoEncontradoException.class,() -> bruxoService.atualizarBruxo(ID_BRUXO,bruxoRequestDto));
    }

    @DisplayName("10-deve lancar CasaNaoEncontradaException")
    @Test
    void deveLancarCasaNaoEncontradaException() {

        BruxoRequestDto bruxoRequestDto = criarBruxoRequestDtoCasaNula();
        Bruxo bruxo = criarBruxo(NOME_SONSERINA, null);
        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        Assertions.assertThrows(CasaNaoEncontradaException.class,() -> bruxoService.atualizarBruxo(ID_BRUXO,bruxoRequestDto));
    }

    @DisplayName("11-deve atualizar bruxo")
    @Test
    void deveAtualizarBruxo() {

        BruxoRequestDto bruxoRequestDto = criarBruxoRequestDto(NOME_SONSERINA, CASA_BRUXO_SONSERINA);
        Bruxo bruxo = criarBruxo(NOME_SONSERINA, CASA_BRUXO_SONSERINA);
        BruxoResponseDto bruxoResponseDto = criarBruxoResponseDto(NOME_SONSERINA, CASA_BRUXO_SONSERINA);

        Mockito.when(bruxoRepository.findById(ID_BRUXO)).thenReturn(Optional.of(bruxo));
        Mockito.when(bruxoRepository.save(bruxo)).thenReturn(bruxo);
        Mockito.when(bruxoMapper.bruxoToResponseDto(bruxo)).thenReturn(bruxoResponseDto);

        BruxoResponseDto response = bruxoService.atualizarBruxo(ID_BRUXO, bruxoRequestDto);

        Assertions.assertEquals(NOME_SONSERINA, response.getNome());
        Assertions.assertEquals(CASA_BRUXO_SONSERINA, response.getCasa());
    }

    }
