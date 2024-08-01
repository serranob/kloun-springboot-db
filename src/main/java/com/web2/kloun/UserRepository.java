package com.web2.kloun;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository {
    @PersistenceContext

    public EntityManager em;

    public List<User> findAll() {
        String sql = "SELECT * FROM usuario";
        Query q = em.createNativeQuery(sql, User.class);
        List<User> users = q.getResultList();
        return users;
    }

    public User findById(int id) {
        String sql = "SELECT * FROM usuario WHERE id = :id";
        Query q = em.createNativeQuery(sql, User.class);
        q.setParameter("id", id);
        User user = (User) q.getSingleResult();
        return user;
    }

    @Transactional
    public void save(User user) {
        String sql = "INSERT INTO usuario (email, senha, nome, sobrenome, dataNascimento, celular, cpf) VALUES (:email, :senha, :nome, :sobrenome, :dataNascimento, :celular, :cpf)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("email", user.getEmail());
        query.setParameter("senha", user.getSenha());
        query.setParameter("nome", user.getNome());
        query.setParameter("sobrenome", user.getSobrenome());
        query.setParameter("dataNascimento", user.getDataNascimento());
        query.setParameter("cpf", user.getCpf());
        query.setParameter("celular", user.getCelular());
        query.executeUpdate();
    }

    @Transactional
    public void update(User user) {
        String sql = "UPDATE usuario SET email = :email, senha = :senha, nome = :nome, sobrenome = :sobrenome, dataNascimento = :dataNascimento, cpf =:cpf, celular = :celular WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("email", user.getEmail());
        query.setParameter("senha", user.getSenha());
        query.setParameter("nome", user.getNome());
        query.setParameter("sobrenome", user.getSobrenome());
        query.setParameter("dataNascimento", user.getDataNascimento());
        query.setParameter("cpf", user.getCpf());
        query.setParameter("celular", user.getCelular());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    
}