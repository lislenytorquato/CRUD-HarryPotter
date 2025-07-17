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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BruxoService {

    BruxoMapper bruxoMapper;
    BruxoGrifinoriaRepository bruxoGrifinoriaRepository;
    BruxoSonserinaRepository bruxoSonserinaRepository;

    public BruxoService(BruxoMapper bruxoMapper,BruxoGrifinoriaRepository bruxoGrifinoriaRepository, BruxoSonserinaRepository bruxoSonserinaRepository){
        this.bruxoMapper = bruxoMapper;
        this.bruxoGrifinoriaRepository = bruxoGrifinoriaRepository;
        this.bruxoSonserinaRepository = bruxoSonserinaRepository;

    }

    public BruxoResponseDto criarBruxo (BruxoRequestDto bruxoRequestDto) {

        BruxoSonserina bruxoSonserinaEntity = bruxoMapper.bruxoRequestDtoToBruxoSonserinaEntity(bruxoRequestDto);
        BruxoGrifinoria bruxoGrifinoriaEntity = bruxoMapper.bruxoRequestDtoToBruxoGrifinoriaEntity(bruxoRequestDto);
        CasaEnum casa = bruxoRequestDto.getCasa();

        return switch (casa){
            case SONSERINA -> {
                BruxoSonserina bruxoSonserinaSalvo = bruxoSonserinaRepository.save(bruxoSonserinaEntity);
                yield bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserinaSalvo);

            }
            case GRIFINORIA -> {
                BruxoGrifinoria bruxoGrifinoriaSalvo = bruxoGrifinoriaRepository.save(bruxoGrifinoriaEntity);
                yield bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoriaSalvo);
            }
        };
    }

    public List<BruxoResponseDto> listarBruxos(){
        List<BruxoResponseDto> listaBruxoResponseDto = new ArrayList<>();

        List<BruxoGrifinoria> bruxosGrifinoria = bruxoGrifinoriaRepository.findAll();
        List<BruxoSonserina> bruxosSonserina = bruxoSonserinaRepository.findAll();

        List<BruxoResponseDto> listaGrifinoriaResponseDto = bruxoMapper.listaBruxoGrifinoriaToListaResponseDto(bruxosGrifinoria);

        List<BruxoResponseDto> listaSonserinaResponseDto = bruxoMapper.listaBruxoSonserinaToListaResponseDto(bruxosSonserina);

        listaBruxoResponseDto.addAll(listaGrifinoriaResponseDto);
        listaBruxoResponseDto.addAll(listaSonserinaResponseDto);

        return listaBruxoResponseDto;
    }

    public BruxoResponseDto atualizarBruxo(BruxoRequestDto bruxoRequestDto) throws BruxoException {
        CasaEnum casa = bruxoRequestDto.getCasa();
        BruxoGrifinoria bruxoGrifinoria = bruxoMapper.bruxoRequestDtoToBruxoGrifinoriaEntity(bruxoRequestDto);
        BruxoSonserina bruxoSonserina = bruxoMapper.bruxoRequestDtoToBruxoSonserinaEntity(bruxoRequestDto);
        return switch (casa){
            case SONSERINA -> {
                Optional<BruxoSonserina> bruxoSonserinaEncontrado = bruxoSonserinaRepository.findById(bruxoGrifinoria.getId());
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Bruxo nao encontrado"));
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Nome do bruxo nao encontrado")).setNome(bruxoRequestDto.getNome());
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Casa do bruxo nao encontrada")).setCasa(bruxoRequestDto.getCasa());
                BruxoSonserina bruxoSonserinaAtualizado = bruxoSonserinaRepository.save(bruxoSonserinaEncontrado.get());
                bruxoSonserinaRepository.deleteById(bruxoSonserinaEncontrado.get().getId());
                yield bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserinaAtualizado);

            }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEncontrado = bruxoGrifinoriaRepository.findById(bruxoGrifinoria.getId());
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Bruxo nao encontrado"));
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Nome do bruxo nao encontrado")).setNome(bruxoRequestDto.getNome());
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Casa do bruxo nao encontrada")).setCasa(bruxoRequestDto.getCasa());
                bruxoGrifinoriaRepository.save(bruxoGrifinoriaEncontrado.get());
                bruxoGrifinoriaRepository.deleteById(bruxoGrifinoriaEncontrado.get().getId());
                yield bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoria);

            }
        };
    }
}
