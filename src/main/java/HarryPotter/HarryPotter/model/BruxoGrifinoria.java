package HarryPotter.HarryPotter.model;

import HarryPotter.HarryPotter.enums.CasaEnum;

public class BruxoGrifinoria extends Bruxo implements Magia{

    public BruxoGrifinoria(String nome, CasaEnum casa) {
        super(nome, casa);
    }

    @Override
    public String lancarFeitico(){
        return "Expelliarmus! - O bruxo da Grifinória lançou seu feitiço!";

    }
}
