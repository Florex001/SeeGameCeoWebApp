package hu.seegame.SeeGameCeo.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "jobid")
    @SequenceGenerator(name = "jobid", sequenceName = "jobid", allocationSize = 1, initialValue = 1000)
    private int id;
    private int muhelyId;
    private String auto;
    private String autoszine;
    private int autoar;
    private int anyagkoltseg;
    private int folyamat1;
    private int folyamat2;
    private int folyamat3;
    private int folyamat4;
    private int folyamat5;
    private int folyamat6;
    private int folyamat7;
    private int folyamat8;
    private int folyamat9;
    private int folyamat10;
    private int folyamat11;
    private int folyamat12;
    private int fizetes1;
    private int fizetes2;
    private int fizetes3;
    private int fizetes4;
    private int fizetes5;
    private int fizetes6;
    private int fizetes7;
    private int fizetes8;
    private int fizetes9;
    private int fizetes10;
    private int fizetes11;
    private int fizetes12;
    private int osszfizetes;
    private String leadta;
    private boolean fizetve;
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

    public int getAnyagkoltseg() {
        return anyagkoltseg;
    }

    public void setAnyagkoltseg(int anyagkoltseg) {
        this.anyagkoltseg = anyagkoltseg;
    }

    public int getFolyamat1() {
        return folyamat1;
    }

    public void setFolyamat1(int folyamat1) {
        this.folyamat1 = folyamat1;
    }

    public int getFolyamat2() {
        return folyamat2;
    }

    public void setFolyamat2(int folyamat2) {
        this.folyamat2 = folyamat2;
    }

    public int getFolyamat3() {
        return folyamat3;
    }

    public void setFolyamat3(int folyamat3) {
        this.folyamat3 = folyamat3;
    }

    public int getFolyamat4() {
        return folyamat4;
    }

    public void setFolyamat4(int folyamat4) {
        this.folyamat4 = folyamat4;
    }

    public int getFolyamat5() {
        return folyamat5;
    }

    public void setFolyamat5(int folyamat5) {
        this.folyamat5 = folyamat5;
    }

    public int getFolyamat6() {
        return folyamat6;
    }

    public void setFolyamat6(int folyamat6) {
        this.folyamat6 = folyamat6;
    }

    public int getFolyamat7() {
        return folyamat7;
    }

    public void setFolyamat7(int folyamat7) {
        this.folyamat7 = folyamat7;
    }

    public int getFolyamat8() {
        return folyamat8;
    }

    public void setFolyamat8(int folyamat8) {
        this.folyamat8 = folyamat8;
    }

    public int getFolyamat9() {
        return folyamat9;
    }

    public void setFolyamat9(int folyamat9) {
        this.folyamat9 = folyamat9;
    }

    public int getFolyamat10() {
        return folyamat10;
    }

    public void setFolyamat10(int folyamat10) {
        this.folyamat10 = folyamat10;
    }

    public int getFolyamat11() {
        return folyamat11;
    }

    public void setFolyamat11(int folyamat11) {
        this.folyamat11 = folyamat11;
    }

    public int getFolyamat12() {
        return folyamat12;
    }

    public void setFolyamat12(int folyamat12) {
        this.folyamat12 = folyamat12;
    }

    public int getFizetes1() {
        return fizetes1;
    }

    public void setFizetes1(int fizetes1) {
        this.fizetes1 = fizetes1;
    }

    public int getFizetes2() {
        return fizetes2;
    }

    public void setFizetes2(int fizetes2) {
        this.fizetes2 = fizetes2;
    }

    public int getFizetes3() {
        return fizetes3;
    }

    public void setFizetes3(int fizetes3) {
        this.fizetes3 = fizetes3;
    }

    public int getFizetes4() {
        return fizetes4;
    }

    public void setFizetes4(int fizetes4) {
        this.fizetes4 = fizetes4;
    }

    public int getFizetes5() {
        return fizetes5;
    }

    public void setFizetes5(int fizetes5) {
        this.fizetes5 = fizetes5;
    }

    public int getFizetes6() {
        return fizetes6;
    }

    public void setFizetes6(int fizetes6) {
        this.fizetes6 = fizetes6;
    }

    public int getFizetes7() {
        return fizetes7;
    }

    public void setFizetes7(int fizetes7) {
        this.fizetes7 = fizetes7;
    }

    public int getFizetes8() {
        return fizetes8;
    }

    public void setFizetes8(int fizetes8) {
        this.fizetes8 = fizetes8;
    }

    public int getFizetes9() {
        return fizetes9;
    }

    public void setFizetes9(int fizetes9) {
        this.fizetes9 = fizetes9;
    }

    public int getFizetes10() {
        return fizetes10;
    }

    public void setFizetes10(int fizetes10) {
        this.fizetes10 = fizetes10;
    }

    public int getFizetes11() {
        return fizetes11;
    }

    public void setFizetes11(int fizetes11) {
        this.fizetes11 = fizetes11;
    }

    public int getFizetes12() {
        return fizetes12;
    }

    public void setFizetes12(int fizetes12) {
        this.fizetes12 = fizetes12;
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

    public boolean isFizetve() {
        return fizetve;
    }

    public void setFizetve(boolean fizetve) {
        this.fizetve = fizetve;
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
        Job job = (Job) o;
        return id == job.id && muhelyId == job.muhelyId && autoar == job.autoar && anyagkoltseg == job.anyagkoltseg && folyamat1 == job.folyamat1 && folyamat2 == job.folyamat2 && folyamat3 == job.folyamat3 && folyamat4 == job.folyamat4 && folyamat5 == job.folyamat5 && folyamat6 == job.folyamat6 && folyamat7 == job.folyamat7 && folyamat8 == job.folyamat8 && folyamat9 == job.folyamat9 && folyamat10 == job.folyamat10 && folyamat11 == job.folyamat11 && folyamat12 == job.folyamat12 && fizetes1 == job.fizetes1 && fizetes2 == job.fizetes2 && fizetes3 == job.fizetes3 && fizetes4 == job.fizetes4 && fizetes5 == job.fizetes5 && fizetes6 == job.fizetes6 && fizetes7 == job.fizetes7 && fizetes8 == job.fizetes8 && fizetes9 == job.fizetes9 && fizetes10 == job.fizetes10 && fizetes11 == job.fizetes11 && fizetes12 == job.fizetes12 && osszfizetes == job.osszfizetes && fizetve == job.fizetve && Objects.equals(auto, job.auto) && Objects.equals(autoszine, job.autoszine) && Objects.equals(leadta, job.leadta) && Objects.equals(status, job.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, muhelyId, auto, autoszine, autoar, anyagkoltseg, folyamat1, folyamat2, folyamat3, folyamat4, folyamat5, folyamat6, folyamat7, folyamat8, folyamat9, folyamat10, folyamat11, folyamat12, fizetes1, fizetes2, fizetes3, fizetes4, fizetes5, fizetes6, fizetes7, fizetes8, fizetes9, fizetes10, fizetes11, fizetes12, osszfizetes, leadta, fizetve, status);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", muhelyId=" + muhelyId +
                ", auto='" + auto + '\'' +
                ", autoszine='" + autoszine + '\'' +
                ", autoar=" + autoar +
                ", anyagkoltseg=" + anyagkoltseg +
                ", folyamat1=" + folyamat1 +
                ", folyamat2=" + folyamat2 +
                ", folyamat3=" + folyamat3 +
                ", folyamat4=" + folyamat4 +
                ", folyamat5=" + folyamat5 +
                ", folyamat6=" + folyamat6 +
                ", folyamat7=" + folyamat7 +
                ", folyamat8=" + folyamat8 +
                ", folyamat9=" + folyamat9 +
                ", folyamat10=" + folyamat10 +
                ", folyamat11=" + folyamat11 +
                ", folyamat12=" + folyamat12 +
                ", fizetes1=" + fizetes1 +
                ", fizetes2=" + fizetes2 +
                ", fizetes3=" + fizetes3 +
                ", fizetes4=" + fizetes4 +
                ", fizetes5=" + fizetes5 +
                ", fizetes6=" + fizetes6 +
                ", fizetes7=" + fizetes7 +
                ", fizetes8=" + fizetes8 +
                ", fizetes9=" + fizetes9 +
                ", fizetes10=" + fizetes10 +
                ", fizetes11=" + fizetes11 +
                ", fizetes12=" + fizetes12 +
                ", osszfizetes=" + osszfizetes +
                ", leadta='" + leadta + '\'' +
                ", fizetve=" + fizetve +
                ", status='" + status + '\'' +
                '}';
    }
}
