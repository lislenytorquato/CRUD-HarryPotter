package HarryPotter.HarryPotter.mapper;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;

public class BruxoMapper {


    public BruxoGrifinoria converterRequestDtoParaBruxoGrifinoria(BruxoRequestDto bruxoRequestDto){

       return new BruxoGrifinoria(bruxoRequestDto.getNome(), bruxoRequestDto.getCasa());

    }
    public BruxoSonserina converterRequestDtoParaBruxoSonserina(BruxoRequestDto bruxoRequestDto){

        return new BruxoSonserina(bruxoRequestDto.getNome(), bruxoRequestDto.getCasa());
    }
    
}
