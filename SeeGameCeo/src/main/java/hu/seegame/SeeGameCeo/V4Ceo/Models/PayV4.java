package hu.seegame.SeeGameCeo.V4Ceo.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PayV4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PayV4IDGenerator")
    @SequenceGenerator(name = "PayV4IDGenerator", sequenceName = "PayV4IDGenerator", allocationSize = 1, initialValue = 1000)
    private int id;
    private int munkaid;
    private String icnev;
    private int folyamat;
    private double fizetes;
    private boolean fizetve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunkaid() {
        return munkaid;
    }

    public void setMunkaid(int munkaid) {
        this.munkaid = munkaid;
    }

    public String getIcnev() {
        return icnev;
    }

    public void setIcnev(String icnev) {
        this.icnev = icnev;
    }

    public int getFolyamat() {
        return folyamat;
    }

    public void setFolyamat(int folyamat) {
        this.folyamat = folyamat;
    }

    public double getFizetes() {
        return fizetes;
    }

    public void setFizetes(double fizetes) {
        this.fizetes = fizetes;
    }

    public boolean isFizetve() {
        return fizetve;
    }

    public void setFizetve(boolean fizetve) {
        this.fizetve = fizetve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayV4 payV4 = (PayV4) o;
        return id == payV4.id && munkaid == payV4.munkaid && folyamat == payV4.folyamat && Double.compare(payV4.fizetes, fizetes) == 0 && fizetve == payV4.fizetve && Objects.equals(icnev, payV4.icnev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, munkaid, icnev, folyamat, fizetes, fizetve);
    }

    @Override
    public String toString() {
        return "PayV4{" +
                "id=" + id +
                ", munkaid=" + munkaid +
                ", icnev='" + icnev + '\'' +
                ", folyamat=" + folyamat +
                ", fizetes=" + fizetes +
                ", fizetve=" + fizetve +
                '}';
    }
}
