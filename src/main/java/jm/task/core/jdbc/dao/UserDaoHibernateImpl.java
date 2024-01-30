package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory factory = Util.getSessionFactory();
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(255) NOT NULL," +
            "lastName VARCHAR(255) NOT NULL," +
            "age INT NOT NULL" +
            ")";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private static final String CLEAR = "TRUNCATE TABLE users";
    private Transaction transaction = null;

    @Override
    public void createUsersTable() {
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User " + name + " добавлен");
        } catch (Exception e) {
            transaction.rollback();
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from User ").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAR)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getStackTrace();
        }
    }
}