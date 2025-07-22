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

    BruxoResponseDto bruxoToResponseDto(Bruxo bruxo);

    Bruxo requestDtoToBruxo(BruxoRequestDto bruxoRequestDto);
    List<BruxoResponseDto> listaDeBruxosToResponseDto(List<Bruxo> listaDeBruxos);
    BruxoSonserina bruxoToBruxoSonserina(Bruxo bruxo);
    BruxoGrifinoria bruxoToBruxoGrifinoria(Bruxo bruxo);
}

