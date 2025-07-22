package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.exceptions.CasaNaoEncontradaException;
import HarryPotter.HarryPotter.exceptions.NomeNaoEncontradoException;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.Bruxo;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BruxoService {

    BruxoMapper mapper;
    BruxoRepository repository;


    public BruxoService(BruxoMapper mapper, BruxoRepository repository){
        this.mapper = mapper;
        this.repository = repository;

    }

    public BruxoResponseDto criarBruxo (BruxoRequestDto bruxoRequestDto) {

        lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());
        lancarCasaNaoEncontradaException(bruxoRequestDto.getCasa());

        Bruxo bruxo = mapper.RequestDtoToBruxo(bruxoRequestDto);
        Bruxo bruxoSalvo = repository.save(bruxo);
        return mapper.bruxoToResponseDto(bruxoSalvo);
    }

    public List<BruxoResponseDto> listarBruxos(){
        List<Bruxo> listaDeBruxos = repository.findAll();

        return mapper.listaDeBruxosToResponseDto(listaDeBruxos);
    }

    public BruxoResponseDto atualizarBruxo(Long idBruxo,BruxoRequestDto bruxoRequestDto){

        Bruxo bruxo = repositorFindById(idBruxo);

        lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());

        bruxo.setNome(bruxoRequestDto.getNome());

        lancarCasaNaoEncontradaException(bruxoRequestDto.getCasa());

        bruxo.setCasa(bruxoRequestDto.getCasa());

        Bruxo bruxoSalvo = repository.save(bruxo);

        return mapper.bruxoToResponseDto(bruxoSalvo);
    }

    public String mostrarInformacoes(Long idBruxo) {

        Bruxo bruxo = repositorFindById(idBruxo);
        return bruxo.mostrarInformacoes();
    }

    public String lancaFeitico(Long idBruxo) {

        Bruxo bruxo = repositorFindById(idBruxo);

        BruxoSonserina bruxoSonserina = mapper.bruxoToBruxoSonserina(bruxo);
        BruxoGrifinoria bruxoGrifinoria = mapper.bruxoToBruxoGrifinoria(bruxo);

        return switch (bruxo.getCasa()){
            case SONSERINA -> bruxoSonserina.lancarFeitico();
            case GRIFINORIA -> bruxoGrifinoria.lancarFeitico();
        };
    }

    public void deletarBruxo(Long idBruxo) {
        Bruxo bruxo = repositorFindById(idBruxo);
        if ( bruxo != null) {
            repository.delete(bruxo);
        }

    }

    private Bruxo repositorFindById(Long idBruxo) {
        return repository.findById(idBruxo).orElseThrow(BruxoNaoEncontradoException::new);
    }
    private void lancarNomeNaoEncontradoException(String nome) {
        if (isNull(nome) || nome.isBlank()){
            throw new NomeNaoEncontradoException();
        }
    }
    private void lancarCasaNaoEncontradaException(CasaEnum casa) {
        if (isNull(casa)){
            throw new CasaNaoEncontradaException();
        }
    }

}
