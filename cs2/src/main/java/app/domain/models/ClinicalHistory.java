package app.domain.models;

import lombok.Getter;
import lombok.Setter;


import java.sql.Date;
import java.util.List;


@Setter
@Getter
public class ClinicalHistory{


    private long idPet;
    private Date date;
    private String users;
    private String reason;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private String medication;
    private String dose;
    private Long idOrder;
    private List<String> vaccine;
    private  List<String> allergies;
    private String detailsProcess;
    private boolean orderAnnulled;


    public ClinicalHistory() {
    }


}
