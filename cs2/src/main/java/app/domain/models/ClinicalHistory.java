package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.util.List;


@Setter
@Getter
public class ClinicalHistory extends Pet{
    @Setter
    @Getter
    private Date date;
    private String users;
    private String reason;
    private String symptoms;
    private String diagnostic;
    private String procedure;
    private String dose;
    private long idOrder;
    private List<String> vaccine;
    private  List<String> allergies;
    private String detailsProcess;
    private boolean orderAnnulled;

    public ClinicalHistory(long idPet, long DniOwner, String name, int agePet, String species, String race, String color, String size, Float weight, Date date, String users, String reason, String symptoms, String diagnostic, String procedure, String dose, long idOrder, List<String> vaccine, List<String> allergies, String detailsProcess, boolean orderAnnulled) {
        super(idPet, DniOwner, name, agePet, species, race, color, size, weight);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public List<String> getVaccine() {
        return vaccine;
    }

    public void setVaccine(List<String> vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getDetailsProcess() {
        return detailsProcess;
    }

    public void setDetailsProcess(String detailsProcess) {
        this.detailsProcess = detailsProcess;
    }

    public boolean isOrderAnnulled() {
        return orderAnnulled;
    }

    public void setOrderAnnulled(boolean orderAnnulled) {
        this.orderAnnulled = orderAnnulled;
    }
}
