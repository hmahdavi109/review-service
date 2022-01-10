package mahdavi.example.review.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public abstract class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false, nullable = false)
    private Date creationTime;

    @LastModifiedDate
    @Column(name = "last_update_time")
    private Date updateTime;

    public EntityBase(Long id) {
        this.id = id;
    }


}