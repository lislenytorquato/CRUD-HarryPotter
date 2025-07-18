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

    public BruxoResponseDto atualizarBruxo(CasaEnum casaBruxoASerAtualizado,Long idBruxo,BruxoRequestDto bruxoRequestDto) throws BruxoException {

        return switch (casaBruxoASerAtualizado){
            case SONSERINA -> {
                Optional<BruxoSonserina> bruxoSonserinaEncontrado = bruxoSonserinaRepository.findById(idBruxo);
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Bruxo nao encontrado"));
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Nome do bruxo nao encontrado")).setNome(bruxoRequestDto.getNome());
                bruxoSonserinaEncontrado.orElseThrow(()-> new BruxoException("Casa do bruxo nao encontrada")).setCasa(bruxoRequestDto.getCasa());
                BruxoSonserina bruxoSonserinaAtualizado = bruxoSonserinaRepository.save(bruxoSonserinaEncontrado.get());
                bruxoSonserinaRepository.deleteById(bruxoSonserinaEncontrado.get().getId());
                yield bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserinaAtualizado);

            }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEncontrado = bruxoGrifinoriaRepository.findById(idBruxo);
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Bruxo nao encontrado"));
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Nome do bruxo nao encontrado")).setNome(bruxoRequestDto.getNome());
                bruxoGrifinoriaEncontrado.orElseThrow(()-> new BruxoException("Casa do bruxo nao encontrada")).setCasa(bruxoRequestDto.getCasa());
                BruxoGrifinoria bruxoGrifinoria = bruxoGrifinoriaRepository.save(bruxoGrifinoriaEncontrado.get());
                bruxoGrifinoriaRepository.deleteById(bruxoGrifinoriaEncontrado.get().getId());
                yield bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoria);

            }
        };
    }

    public String mostrarInformacoes(CasaEnum casaBruxo,Long idBruxo) throws BruxoException {

        return switch (casaBruxo){
                case SONSERINA -> {
                    Optional<BruxoSonserina> bruxoSonserinaEscolhido = bruxoSonserinaRepository.findById(idBruxo);
                   yield  bruxoSonserinaEscolhido.orElseThrow(()-> new BruxoException("Bruxo nao encontrado")).mostrarInformacoes();
                }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEscolhido = bruxoGrifinoriaRepository.findById(idBruxo);
                yield bruxoGrifinoriaEscolhido.orElseThrow(()-> new BruxoException("Bruxo nao encontrado")).mostrarInformacoes();
            }

        };
    }

    public String lancaFeitico(CasaEnum casaBruxo,Long idBruxo) throws BruxoException {

        return switch (casaBruxo){
            case SONSERINA -> {
                Optional<BruxoSonserina> bruxoSonserinaEscolhido = bruxoSonserinaRepository.findById(idBruxo);
                yield bruxoSonserinaEscolhido.orElseThrow(()->new BruxoException("Bruxo nao encontrado")).lancarFeitico();
            }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEscolhido = bruxoGrifinoriaRepository.findById(idBruxo);
                yield bruxoGrifinoriaEscolhido.orElseThrow(()-> new BruxoException("Bruxo nao encontrado")).lancarFeitico();
            }
        };

    }
}
