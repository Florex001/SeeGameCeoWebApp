package hu.seegame.SeeGameCeo.V4Ceo.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class JobV4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JobV4IDGenerator")
    @SequenceGenerator(name = "JobV4IDGenerator", sequenceName = "JobV4IDGenerator", allocationSize = 1, initialValue = 1000)
    private int id;
    private int muhelyId;
    private String auto;
    private String autoszine;
    private int autoar;
    private String elvalalta;
    private int anyagkoltseg;
    private int osszfizetes;
    private String leadta;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMuhelyId() {
        return muhelyId;
    }

    public void setMuhelyId(int muhelyId) {
        this.muhelyId = muhelyId;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getAutoszine() {
        return autoszine;
    }

    public void setAutoszine(String autoszine) {
        this.autoszine = autoszine;
    }

    public int getAutoar() {
        return autoar;
    }

    public void setAutoar(int autoar) {
        this.autoar = autoar;
    }

    public String getElvalalta() {
        return elvalalta;
    }

    public void setElvalalta(String elvalalta) {
        this.elvalalta = elvalalta;
    }

    public int getAnyagkoltseg() {
        return anyagkoltseg;
    }

    public void setAnyagkoltseg(int anyagkoltseg) {
        this.anyagkoltseg = anyagkoltseg;
    }

    public int getOsszfizetes() {
        return osszfizetes;
    }

    public void setOsszfizetes(int osszfizetes) {
        this.osszfizetes = osszfizetes;
    }

    public String getLeadta() {
        return leadta;
    }

    public void setLeadta(String leadta) {
        this.leadta = leadta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobV4 jobV4 = (JobV4) o;
        return id == jobV4.id && muhelyId == jobV4.muhelyId && autoar == jobV4.autoar && anyagkoltseg == jobV4.anyagkoltseg && osszfizetes == jobV4.osszfizetes && Objects.equals(auto, jobV4.auto) && Objects.equals(autoszine, jobV4.autoszine) && Objects.equals(elvalalta, jobV4.elvalalta) && Objects.equals(leadta, jobV4.leadta) && Objects.equals(status, jobV4.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, muhelyId, auto, autoszine, autoar, elvalalta, anyagkoltseg, osszfizetes, leadta, status);
    }

    @Override
    public String toString() {
        return "JobV4{" +
                "id=" + id +
                ", muhelyId=" + muhelyId +
                ", auto='" + auto + '\'' +
                ", autoszine='" + autoszine + '\'' +
                ", autoar=" + autoar +
                ", elvalalta='" + elvalalta + '\'' +
                ", anyagkoltseg=" + anyagkoltseg +
                ", osszfizetes=" + osszfizetes +
                ", leadta='" + leadta + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
