package com.web2.kloun.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web2.kloun.model.Notification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class NotificationRepository {
        @PersistenceContext

    public EntityManager em;

    public List<Notification> findAll() {
        String sql = "SELECT * FROM notificacao";
        Query q = em.createNativeQuery(sql, Notification.class);
        List<Notification> notifications = q.getResultList();
        return notifications;
    }

    public Notification findById(int id) {
        String sql = "SELECT * FROM notificacao WHERE id = :id";
        Query q = em.createNativeQuery(sql, Notification.class);
        q.setParameter("id", id);
        Notification notification = (Notification) q.getSingleResult();
        return notification;
    }

    @Transactional
    public void save(Notification notification) {
        String sql = "INSERT INTO notificacao (mensagem, tipo, prioridade, criacao) VALUES (:mensagem, :tipo, :prioridade, :criacao)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("mensagem", notification.getMensagem());
        query.setParameter("tipo", notification.getTipo());
        query.setParameter("prioridade", notification.getPrioridade());
        query.setParameter("criacao", notification.getCriacao());
        query.executeUpdate();
    }

    @Transactional
    public void update(Notification notification) {
        String sql = "UPDATE notificacao SET mensagem = :mensagem, tipo = :tipo, prioridade = :prioridade, criacao = :criacao WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", notification.getId());
        query.setParameter("mensagem", notification.getMensagem());
        query.setParameter("tipo", notification.getTipo());
        query.setParameter("prioridade", notification.getPrioridade());
        query.setParameter("criacao", notification.getCriacao());
        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        String sql = "DELETE FROM notificacao WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    
}
