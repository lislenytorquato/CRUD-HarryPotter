package HarryPotter.HarryPotter.mapper;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.model.Bruxo;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BruxoMapper {
    BruxoMapper INSTANCE = Mappers.getMapper(BruxoMapper.class);

    BruxoRequestDto bruxoToRequestDto(Bruxo bruxo);

    BruxoResponseDto bruxoToResponseDto(Bruxo bruxo);

    BruxoRequestDto bruxoGrifinoriaToRequestDto(BruxoGrifinoria bruxoGrifinoria);

    BruxoResponseDto bruxoGrifinoriaToResponseDto(BruxoGrifinoria bruxoGrifinoria);

    BruxoRequestDto bruxoSonserinaToRequestDto(BruxoSonserina bruxoSonserina);

    BruxoResponseDto bruxoSonserinaToResponseDto(BruxoSonserina bruxoSonserina);

    BruxoGrifinoria bruxoRequestDtoToBruxoGrifinoriaEntity(BruxoRequestDto bruxoRequestDto);

    BruxoSonserina bruxoRequestDtoToBruxoSonserinaEntity(BruxoRequestDto bruxoRequestDto);

    BruxoGrifinoria bruxoResponseDtoToBruxoGrifinoriaEntity(BruxoResponseDto bruxoResponseDto);

    BruxoSonserina bruxoResponseDtoToBruxoSonserinaEntity(BruxoResponseDto bruxoResponseDto);

    BruxoResponseDto toResponseDto(BruxoRequestDto bruxoRequestDto);

    List<BruxoResponseDto> listaBruxoGrifinoriaToListaResponseDto(List<BruxoGrifinoria> listaBruxoGrifinoria);

    List<BruxoResponseDto> listaBruxoSonserinaToListaResponseDto(List<BruxoSonserina> listaBruxoSonserina);
}

