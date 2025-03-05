package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class Register {
    private Date date;
    private String users;
    private String reason;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private String dose;
    private String idOrder;
    private List<String> vaccine;
    private  List<String> allergies;
    private String detailsProcess;
    private boolean orderAnnulled;

    public Register(Date date,String users,String reason, String symptoms, String diagnostic, String procedure, String dose, String idOrder, List<String> vaccine, List<String> allergies, String detailsProcess, boolean orderAnnulled) {
        this.date = date;
        this.users = users;
        this.reason = reason;
        this.symptoms = symptoms;
        this.diagnostic = diagnostic;
        this.procedure = procedure;
        this.dose = dose;
        this.idOrder = idOrder;
        this.vaccine = vaccine;
        this.allergies = allergies;
        this.detailsProcess = detailsProcess;
        this.orderAnnulled = orderAnnulled;
    }
}
