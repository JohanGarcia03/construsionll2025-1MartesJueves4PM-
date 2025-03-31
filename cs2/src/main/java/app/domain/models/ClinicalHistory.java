package app.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ClinicalHistory extends Register {
    private long IdPets;
    @Setter
    private List<Register> registers;

    public ClinicalHistory(long idPets, List<Register> registers) {
        super();
        IdPets = idPets;
        this.registers = registers;
    }

    public void setIdPets(Long idPets) {
        IdPets = idPets;
    }

}
