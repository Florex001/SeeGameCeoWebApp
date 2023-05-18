package hu.seegame.SeeGameCeo.V4Ceo.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class FolyamatTMPV4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FolyamatV4IDGenerator")
    @SequenceGenerator(name = "FolyamatV4IDGenerator", sequenceName = "FolyamatV4IDGenerator", allocationSize = 1, initialValue = 1000)
    private int id;
    private int munkaid;
    private String icnev;
    private int folyamat;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolyamatTMPV4 that = (FolyamatTMPV4) o;
        return id == that.id && munkaid == that.munkaid && folyamat == that.folyamat && Objects.equals(icnev, that.icnev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, munkaid, icnev, folyamat);
    }

    @Override
    public String toString() {
        return "FolyamatTMPV4{" +
                "id=" + id +
                ", munkaid=" + munkaid +
                ", icnev='" + icnev + '\'' +
                ", folyamat=" + folyamat +
                '}';
    }
}
