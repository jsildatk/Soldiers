package org.soldiers.model;

import javax.persistence.*;

@Entity
@Table(name = "rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long rankId;

    @Column(name = "rank", nullable = false)
    private String rank;

    public Rank(String rank) {
        this.rank = rank;
    }

    public Rank() {}

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return this.rankId + " " + this.rank;
    }
}
