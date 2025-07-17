package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
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

    @DisplayName("1- criar bruxo como sonserina e retornar bruxoResponseDto")
    @Test
    void criarBruxoComoSonserinaERetornarBruxoResponseDto() {

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

    @DisplayName("2- criar bruxo como grifinoria e retornar bruxoResponseDto")
    @Test
    void criarBruxoComoGrifinoriaERetornarBruxoResponseDto() {

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
    @DisplayName("3- listar bruxos e retornar um lista de bruxos responseDto")
    @Test
    void listarBruxosERetornarUmaListaDeBruxosResponseDto(){
        List<BruxoResponseDto> bruxoResponseDtoGrifinoriaLista = List.of(BruxoHelper.criarBruxoResponseDtoGrifinoria());
        List<BruxoResponseDto> bruxoResponseDtoSonserinaLista = List.of(BruxoHelper.criarBruxoResponseDtoSonserina());


    }
}
