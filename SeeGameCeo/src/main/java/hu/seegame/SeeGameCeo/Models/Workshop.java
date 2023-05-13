package hu.seegame.SeeGameCeo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "workshopid")
    @SequenceGenerator(name = "workshopid", sequenceName = "workshopid", allocationSize = 1, initialValue = 1000)
    private int id;
    private int muhelyid;
    private int tulajId;
    private String muhelynev;
    private String dolgozo1;
    private String dolgozo2;
    private String dolgozo3;
    private String dolgozo4;
    private String dolgozo5;
    private String dolgozo6;
    private String dolgozo7;
    private String dolgozo8;
    private String dolgozo9;
    private String dolgozo10;
    private String dolgozo11;
    private String dolgozo12;
    private LocalDateTime lejarat;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMuhelyid() {
        return muhelyid;
    }

    public void setMuhelyid(int muhelyid) {
        this.muhelyid = muhelyid;
    }

    public int getTulajId() {
        return tulajId;
    }

    public void setTulajId(int tulajId) {
        this.tulajId = tulajId;
    }

    public String getMuhelynev() {
        return muhelynev;
    }

    public void setMuhelynev(String muhelynev) {
        this.muhelynev = muhelynev;
    }

    public String getDolgozo1() {
        return dolgozo1;
    }

    public void setDolgozo1(String dolgozo1) {
        this.dolgozo1 = dolgozo1;
    }

    public String getDolgozo2() {
        return dolgozo2;
    }

    public void setDolgozo2(String dolgozo2) {
        this.dolgozo2 = dolgozo2;
    }

    public String getDolgozo3() {
        return dolgozo3;
    }

    public void setDolgozo3(String dolgozo3) {
        this.dolgozo3 = dolgozo3;
    }

    public String getDolgozo4() {
        return dolgozo4;
    }

    public void setDolgozo4(String dolgozo4) {
        this.dolgozo4 = dolgozo4;
    }

    public String getDolgozo5() {
        return dolgozo5;
    }

    public void setDolgozo5(String dolgozo5) {
        this.dolgozo5 = dolgozo5;
    }

    public String getDolgozo6() {
        return dolgozo6;
    }

    public void setDolgozo6(String dolgozo6) {
        this.dolgozo6 = dolgozo6;
    }

    public String getDolgozo7() {
        return dolgozo7;
    }

    public void setDolgozo7(String dolgozo7) {
        this.dolgozo7 = dolgozo7;
    }

    public String getDolgozo8() {
        return dolgozo8;
    }

    public void setDolgozo8(String dolgozo8) {
        this.dolgozo8 = dolgozo8;
    }

    public String getDolgozo9() {
        return dolgozo9;
    }

    public void setDolgozo9(String dolgozo9) {
        this.dolgozo9 = dolgozo9;
    }

    public String getDolgozo10() {
        return dolgozo10;
    }

    public void setDolgozo10(String dolgozo10) {
        this.dolgozo10 = dolgozo10;
    }

    public String getDolgozo11() {
        return dolgozo11;
    }

    public void setDolgozo11(String dolgozo11) {
        this.dolgozo11 = dolgozo11;
    }

    public String getDolgozo12() {
        return dolgozo12;
    }

    public void setDolgozo12(String dolgozo12) {
        this.dolgozo12 = dolgozo12;
    }

    public LocalDateTime getLejarat() {
        return lejarat;
    }

    public void setLejarat(LocalDateTime lejarat) {
        this.lejarat = lejarat;
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
        Workshop workshop = (Workshop) o;
        return id == workshop.id && muhelyid == workshop.muhelyid && tulajId == workshop.tulajId && Objects.equals(muhelynev, workshop.muhelynev) && Objects.equals(dolgozo1, workshop.dolgozo1) && Objects.equals(dolgozo2, workshop.dolgozo2) && Objects.equals(dolgozo3, workshop.dolgozo3) && Objects.equals(dolgozo4, workshop.dolgozo4) && Objects.equals(dolgozo5, workshop.dolgozo5) && Objects.equals(dolgozo6, workshop.dolgozo6) && Objects.equals(dolgozo7, workshop.dolgozo7) && Objects.equals(dolgozo8, workshop.dolgozo8) && Objects.equals(dolgozo9, workshop.dolgozo9) && Objects.equals(dolgozo10, workshop.dolgozo10) && Objects.equals(dolgozo11, workshop.dolgozo11) && Objects.equals(dolgozo12, workshop.dolgozo12) && Objects.equals(lejarat, workshop.lejarat) && Objects.equals(status, workshop.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, muhelyid, tulajId, muhelynev, dolgozo1, dolgozo2, dolgozo3, dolgozo4, dolgozo5, dolgozo6, dolgozo7, dolgozo8, dolgozo9, dolgozo10, dolgozo11, dolgozo12, lejarat, status);
    }
}
