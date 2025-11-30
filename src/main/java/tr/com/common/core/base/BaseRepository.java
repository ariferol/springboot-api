package tr.com.common.core.base;

import org.hibernate.Session;

import java.io.Serializable;

/**
 * @author arif.erol
 */
public abstract class BaseRepository<T extends IBaseEntity<T>, ID extends Serializable> implements IBaseRepository<T, ID> {
    protected Class<? extends IBaseEntity<T>> entityClass;
    //    protected final Class<T> entityClass;
//    protected Class<T> entityClass;

    public IBaseEntity<T> findById(ID id, Session session) {
        return (IBaseEntity<T>) session.get(this.entityClass, id);
    }

    public T save(T entity, Session session) {
        session.persist(entity);
        return entity;
    }

}
