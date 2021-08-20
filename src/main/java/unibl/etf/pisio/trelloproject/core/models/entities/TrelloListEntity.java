package unibl.etf.pisio.trelloproject.core.models.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import unibl.etf.pisio.trelloproject.core.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "trellolist")
public class TrelloListEntity implements BaseEntity<String> {
    @Id
    @Column(name = "id", nullable = false, length = 24)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "closed", nullable = true)
    private Boolean closed;
    @Basic
    @Column(name = "pos", nullable = false)
    private Integer pos;
    @Basic
    @Column(name = "subscribed", nullable = true)
    private Boolean subscribed;
    @ManyToOne
    @JoinColumn(name = "idBoard", referencedColumnName = "id", nullable = false)
    private BoardEntity board;
    @OneToMany(mappedBy = "trellolist")
    @JsonIgnore
    private List<CardEntity> cards;
}
