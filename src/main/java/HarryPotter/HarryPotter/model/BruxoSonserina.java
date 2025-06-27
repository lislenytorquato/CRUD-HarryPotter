package HarryPotter.HarryPotter.model;

import HarryPotter.HarryPotter.enums.CasaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BruxoSonserina extends Bruxo implements Magia{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BruxoSonserina(String nome, CasaEnum casa) {
        super(nome, casa);
    }

    @Override
    public String lancarFeitico(){
        return "Serpensortia! - O bruxo da Sonserina lançou seu feitiço!";
    }
}
