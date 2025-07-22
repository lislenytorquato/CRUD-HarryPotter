package HarryPotter.HarryPotter.dto;

import HarryPotter.HarryPotter.enums.CasaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BruxoRequestDto {


    @NotBlank (message = "Não há nome")
    private String nome;

    @NotNull (message = "A casa está nula")
    private CasaEnum casa;


    public BruxoRequestDto(String nome,CasaEnum casa){
        this.nome = nome;
        this.casa = casa;
    }

    public String getNome() {
        return nome;
    }

    public CasaEnum getCasa() {
        return casa;
    }

}
