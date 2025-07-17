package HarryPotter.HarryPotter.model;

import HarryPotter.HarryPotter.enums.CasaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BruxoGrifinoria extends Bruxo implements Magia{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BruxoGrifinoria() {
        super();
    }

    public BruxoGrifinoria(String nome, CasaEnum casa) {
        super(nome, casa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String lancarFeitico(){
        return "Expelliarmus! - O bruxo da Grifinória lançou seu feitiço!";

    }
}
