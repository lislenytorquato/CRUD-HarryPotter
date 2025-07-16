package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoException;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoGrifinoriaRepository;
import HarryPotter.HarryPotter.repository.BruxoSonserinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BruxoService {

    private static final Logger log = LoggerFactory.getLogger(BruxoService.class);
    BruxoMapper bruxoMapper;
    BruxoGrifinoriaRepository bruxoGrifinoriaRepository;
    BruxoSonserinaRepository bruxoSonserinaRepository;

    public BruxoService(BruxoMapper bruxoMapper,BruxoGrifinoriaRepository bruxoGrifinoriaRepository, BruxoSonserinaRepository bruxoSonserinaRepository){
        this.bruxoMapper = bruxoMapper;
        this.bruxoGrifinoriaRepository = bruxoGrifinoriaRepository;
        this.bruxoSonserinaRepository = bruxoSonserinaRepository;

    }



    public BruxoResponseDto criarBruxo (BruxoRequestDto bruxoRequestDto) throws BruxoException {

        BruxoSonserina bruxoSonserinaEntity = bruxoMapper.bruxoRequestDtoToBruxoSonserinaEntity(bruxoRequestDto);
        BruxoGrifinoria bruxoGrifinoriaEntity = bruxoMapper.bruxoRequestDtoToBruxoGrifinoriaEntity(bruxoRequestDto);
        CasaEnum casa = bruxoRequestDto.getCasa();

        switch (casa){
            case SONSERINA -> {
                BruxoSonserina bruxoSonserinaSalvo = bruxoSonserinaRepository.save(bruxoSonserinaEntity);
                return bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserinaSalvo);
            }
            case GRIFINORIA -> {
                BruxoGrifinoria bruxoGrifinoriaSalvo = bruxoGrifinoriaRepository.save(bruxoGrifinoriaEntity);
                return bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoriaSalvo);
            }
            default -> throw new BruxoException("Casa do bruxo nao encontrada");
        }


    }
}
