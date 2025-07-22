package HarryPotter.HarryPotter.service;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.exceptions.BruxoNaoEncontradoException;
import HarryPotter.HarryPotter.mapper.BruxoMapper;
import HarryPotter.HarryPotter.model.Bruxo;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;
import HarryPotter.HarryPotter.repository.BruxoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BruxoService {

    private final BruxoMapper bruxoMapper;
    private final BruxoRepository bruxoRepository;


    public BruxoService(BruxoMapper bruxoMapper, BruxoRepository bruxoRepository){
        this.bruxoMapper = bruxoMapper;
        this.bruxoRepository = bruxoRepository;

    }

    public BruxoResponseDto criarBruxo (BruxoRequestDto bruxoRequestDto) {

        Bruxo bruxo = bruxoMapper.requestDtoToBruxo(bruxoRequestDto);
        Bruxo bruxoSalvo = bruxoRepository.save(bruxo);
        return bruxoMapper.bruxoToResponseDto(bruxoSalvo);
    }

    public List<BruxoResponseDto> listarBruxos(){
        List<Bruxo> listaDeBruxos = bruxoRepository.findAll();

        return bruxoMapper.listaDeBruxosToResponseDto(listaDeBruxos);
    }

    public BruxoResponseDto atualizarBruxo(Long idBruxo,BruxoRequestDto bruxoRequestDto){

        Bruxo bruxo = repositorFindById(idBruxo);

        //lancarNomeNaoEncontradoException(bruxoRequestDto.getNome());

        bruxo.setNome(bruxoRequestDto.getNome());

        bruxo.setCasa(bruxoRequestDto.getCasa());

        Bruxo bruxoSalvo = bruxoRepository.save(bruxo);

        return bruxoMapper.bruxoToResponseDto(bruxoSalvo);
    }

    public String mostrarInformacoes(Long idBruxo) {

        Bruxo bruxo = repositorFindById(idBruxo);
        return bruxo.mostrarInformacoes();
    }

    public String lancaFeitico(Long idBruxo) {

        Bruxo bruxo = repositorFindById(idBruxo);

        BruxoSonserina bruxoSonserina = bruxoMapper.bruxoToBruxoSonserina(bruxo);
        BruxoGrifinoria bruxoGrifinoria = bruxoMapper.bruxoToBruxoGrifinoria(bruxo);

        return switch (bruxo.getCasa()){
            case SONSERINA -> bruxoSonserina.lancarFeitico();
            case GRIFINORIA -> bruxoGrifinoria.lancarFeitico();
        };
    }

    public void deletarBruxo(Long idBruxo) {
        Bruxo bruxo = repositorFindById(idBruxo);
        if ( bruxo != null) {
            bruxoRepository.delete(bruxo);
        }

    }

    private Bruxo repositorFindById(Long idBruxo) {
        return bruxoRepository.findById(idBruxo).orElseThrow(BruxoNaoEncontradoException::new);
    }

}
