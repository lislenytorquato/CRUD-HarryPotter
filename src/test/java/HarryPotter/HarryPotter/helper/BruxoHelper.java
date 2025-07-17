package HarryPotter.HarryPotter.helper;

import HarryPotter.HarryPotter.dto.BruxoRequestDto;
import HarryPotter.HarryPotter.dto.BruxoResponseDto;
import HarryPotter.HarryPotter.enums.CasaEnum;
import HarryPotter.HarryPotter.model.BruxoGrifinoria;
import HarryPotter.HarryPotter.model.BruxoSonserina;

public class BruxoHelper {

    public static final  String NOME_GRIFINORIA = "Harry Potter";

    public static final  String NOME_SONSERINA = "Lúcio Malfoy";

    public static final String MENSAGEM_BRUXO_EXCECAO = "Casa do bruxo nao encontrada";

    public static BruxoRequestDto criarBruxoRequestDtoGrifinoria(){
        return new BruxoRequestDto(NOME_GRIFINORIA, CasaEnum.GRIFINORIA);
    }

    public static BruxoRequestDto criarBruxoRequestDtoSonserina(){
        return new BruxoRequestDto(NOME_SONSERINA, CasaEnum.SONSERINA);
    }

    public static BruxoResponseDto criarBruxoResponseDtoGrifinoria(){
        return new BruxoResponseDto(NOME_GRIFINORIA, CasaEnum.GRIFINORIA);
    }

    public static BruxoResponseDto criarBruxoResponseDtoSonserina(){
        return new BruxoResponseDto(NOME_SONSERINA, CasaEnum.SONSERINA);
    }

    public static BruxoGrifinoria criarBruxoGrifinoria(){
        return new BruxoGrifinoria(NOME_GRIFINORIA, CasaEnum.GRIFINORIA);
    }

    public static BruxoSonserina criarBruxoSonserina(){
        return new BruxoSonserina(NOME_SONSERINA, CasaEnum.SONSERINA);
    }

}
