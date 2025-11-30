package tr.com.common.core.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @author arif.erol
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityAudit<T> implements IBaseEntity<T> {

    private String createdBy;
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BaseEntityAudit)) return false;
//        if (!super.equals(o)) return false;
//        BaseEntityAudit that = (BaseEntityAudit) o;
//        return createdBy.equals(that.createdBy) &&
//                updatedBy.equals(that.updatedBy) &&
//                createdAt.equals(that.createdAt) &&
//                updatedAt.equals(that.updatedAt);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(),
//                createdBy, updatedBy, createdAt, updatedAt);
//    }

//    @Override
//    public String toString() {
//        return "BaseEntityAudit{" +
//                "createdBy='" + createdBy + '\\'' +
//        ", updatedBy='" + updatedBy + '\\'' +
//        ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                "}" +
//                super.toString();
//    }
}
