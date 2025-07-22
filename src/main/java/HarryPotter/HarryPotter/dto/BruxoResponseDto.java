package HarryPotter.HarryPotter.dto;

import HarryPotter.HarryPotter.enums.CasaEnum;

public class BruxoResponseDto {
    private String nome;
    private CasaEnum casa;

    public BruxoResponseDto(){}

    public BruxoResponseDto(String nome, CasaEnum casa){
        this.nome = nome;
        this.casa = casa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CasaEnum getCasa() {
        return casa;
    }

    public void setCasa(CasaEnum casa) {
        this.casa = casa;
    }
}
