package hu.seegame.SeeGameCeo.V4Ceo.Models;

import jakarta.persistence.*;

@Entity
public class KarakterV4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "v4karakterIDGenerator")
    @SequenceGenerator(name = "v4karakterIDGenerator", sequenceName = "v4karakterIDGenerator", allocationSize = 1, initialValue = 1000)
    private int id;
    private  int userid;
    private String icnev;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getIcnev() {
        return icnev;
    }

    public void setIcnev(String icnev) {
        this.icnev = icnev;
    }

    @Override
    public String toString() {
        return "KarakterV4{" +
                "id=" + id +
                ", userid=" + userid +
                ", icnev='" + icnev + '\'' +
                '}';
    }
}
