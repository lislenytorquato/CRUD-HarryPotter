package HarryPotter.HarryPotter.model;

import HarryPotter.HarryPotter.enums.CasaEnum;

public class BruxoSonserina extends Bruxo implements Magia{

    public BruxoSonserina(String nome, CasaEnum casa) {
        super(nome, casa);
    }

    @Override
    public String lancarFeitico(){
        return "Serpensortia! - O bruxo da Sonserina lançou seu feitiço!";
    }
}
