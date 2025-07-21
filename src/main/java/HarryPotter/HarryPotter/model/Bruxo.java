package HarryPotter.HarryPotter.model;

import HarryPotter.HarryPotter.enums.CasaEnum;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Bruxo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated
    private CasaEnum casa;

    public Bruxo() {
    }

    public Bruxo(String nome, CasaEnum casa){
        this.nome = nome;
        this.casa = casa;
    }
    public String mostrarInformacoes(){
        return "Bruxo{" +
                "nome='" + nome + '\'' +
                ", casa='" + casa + '\'' +
                '}';
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
