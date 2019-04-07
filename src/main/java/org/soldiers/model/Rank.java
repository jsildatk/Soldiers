package org.soldiers.model;

import javax.persistence.*;

@Entity
@Table(name = "rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;

    @Column(name = "rank", nullable = false)
    private String rank;

    public Rank(String rank) {
        this.rank = rank;
    }

    public Rank() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Rank(" + this.id + " " + this.rank + ")";
    }
}
