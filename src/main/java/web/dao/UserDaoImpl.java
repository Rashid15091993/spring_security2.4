package web.dao;

import org.springframework.stereotype.Repository;

import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByName(String s) {
        return entityManager.createQuery(
                        "SELECT user FROM User user WHERE user.name =:username", User.class)
                .setParameter("username", s)
                .getSingleResult();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User readUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(User user) throws NullPointerException {

        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}

