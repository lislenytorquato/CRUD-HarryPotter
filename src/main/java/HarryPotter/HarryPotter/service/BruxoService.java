package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEIgualATabelaException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEncontradaException;
import HarryPotter.HarryPotter.exceptions.NomeNaoEncontradoException;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoGrifinoriaRepository;
import HarryPotter.HarryPotter.repository.BruxoSonserinaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

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

        lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());
        lancarCasaNaoEncontradaException(bruxoRequestDto.getCasa());

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

    public BruxoResponseDto atualizarBruxo(CasaEnum casaBruxo,Long idBruxo,BruxoRequestDto bruxoRequestDto) throws BruxoNaoEncontradoException,NomeNaoEncontradoException,CasaNaoEncontradaException {

        return switch (casaBruxo){
            case SONSERINA -> {

                BruxoSonserina bruxoSonserina = bruxoSonserinaRepository.findById(idBruxo).orElseThrow(BruxoNaoEncontradoException::new);

                lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());
                bruxoSonserina.setNome(bruxoRequestDto.getNome());

                lancarCasaNaoEncontradaException(bruxoRequestDto.getCasa());
                lancarCasaNaoEIgualATabelaException(casaBruxo,bruxoRequestDto.getCasa());
                bruxoSonserina.setCasa(bruxoRequestDto.getCasa());

                bruxoSonserinaRepository.save(bruxoSonserina);
                yield bruxoMapper.bruxoSonserinaToResponseDto(bruxoSonserina);

            }

            case GRIFINORIA -> {
                BruxoGrifinoria bruxoGrifinoria = bruxoGrifinoriaRepository.findById(idBruxo).orElseThrow(BruxoNaoEncontradoException::new);

                lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());
                bruxoGrifinoria.setNome(bruxoRequestDto.getNome());

                lancarCasaNaoEncontradaException(bruxoRequestDto.getCasa());
                lancarCasaNaoEIgualATabelaException(casaBruxo,bruxoRequestDto.getCasa());
                bruxoGrifinoria.setCasa(bruxoRequestDto.getCasa());

                bruxoGrifinoriaRepository.save(bruxoGrifinoria);
                yield bruxoMapper.bruxoGrifinoriaToResponseDto(bruxoGrifinoria);
            }
        };


    }

    public String mostrarInformacoes(CasaEnum casaBruxo,Long idBruxo) throws BruxoNaoEncontradoException {

        return switch (casaBruxo){
                case SONSERINA -> {
                    Optional<BruxoSonserina> bruxoSonserinaEscolhido = bruxoSonserinaRepository.findById(idBruxo);
                   yield  bruxoSonserinaEscolhido.orElseThrow(BruxoNaoEncontradoException::new).mostrarInformacoes();
                }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEscolhido = bruxoGrifinoriaRepository.findById(idBruxo);
                yield bruxoGrifinoriaEscolhido.orElseThrow(BruxoNaoEncontradoException::new).mostrarInformacoes();
            }

        };
    }

    public String lancaFeitico(CasaEnum casaBruxo,Long idBruxo) throws BruxoNaoEncontradoException {

        return switch (casaBruxo){
            case SONSERINA -> {
                Optional<BruxoSonserina> bruxoSonserinaEscolhido = bruxoSonserinaRepository.findById(idBruxo);
                yield bruxoSonserinaEscolhido.orElseThrow(BruxoNaoEncontradoException::new).lancarFeitico();
            }
            case GRIFINORIA -> {
                Optional<BruxoGrifinoria> bruxoGrifinoriaEscolhido = bruxoGrifinoriaRepository.findById(idBruxo);
                yield bruxoGrifinoriaEscolhido.orElseThrow(BruxoNaoEncontradoException::new).lancarFeitico();
            }
        };

    }

    public void deletarBruxo(CasaEnum casaBruxo,Long idBruxo){
         switch (casaBruxo){
            case SONSERINA -> {
               bruxoSonserinaRepository.deleteById(idBruxo);
            }
            case GRIFINORIA -> {
                bruxoGrifinoriaRepository.deleteById(idBruxo);
            }
        };
    }

    public void lancarNomeNaoEncontradoException(String nome) throws NomeNaoEncontradoException{
        if (isNull(nome) || nome.isBlank()){
            throw new NomeNaoEncontradoException();
        }
    }
    public void lancarCasaNaoEncontradaException(CasaEnum casa) throws CasaNaoEncontradaException{
        if (isNull(casa)){
            throw new CasaNaoEncontradaException();
        }
    }
    public void lancarCasaNaoEIgualATabelaException(CasaEnum casaBruxo, CasaEnum casaBruxoRequestDto) throws CasaNaoEIgualATabelaException{
        if (!casaBruxo.equals(casaBruxoRequestDto)){
            throw  new CasaNaoEIgualATabelaException();
        }

    }
}
