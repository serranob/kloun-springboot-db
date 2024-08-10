package com.web2.kloun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @GetMapping("/notificacoes")
    public String listarNotificacoes(Model model){
        List<Notification> notificacoesList = notificationRepository.findAll();
        model.addAttribute("notificacoesList", notificacoesList);
        return "/notificacao/notificacoes";
    }

    @GetMapping("/detalhesNotificacao")
    public String detalhesNotificacao (Model model, @RequestParam ("id") int id){
        Notification findNotification = notificationRepository.findById(id);
        model.addAttribute("notificacao", findNotification);
        return "/notificacao/ver_notificacao";
    }

    @GetMapping("/cadastrarNotificacao")
    public String cadastrarNotificacao(){
        return "/notificacao/cadastrar_notificacao";
    }

    @PostMapping("/armazenarNotificacao")
    public String armazenarNotificacao(Model model, @RequestParam String mensagem, @RequestParam String tipo, @RequestParam String prioridade, @RequestParam String criacao) {
        Notification saveNotification = new Notification();
        saveNotification.setMensagem(mensagem);
        saveNotification.setTipo(tipo);
        saveNotification.setPrioridade(prioridade);
        saveNotification.setCriacao(criacao);
        notificationRepository.save(saveNotification);
        return "redirect:/notificacoes";
    }

    @GetMapping("/atualizarNotificacao")
    public String atualizarNotificacao (Model model, @RequestParam int id){
        Notification findNotification = notificationRepository.findById(id);
        model.addAttribute("notificacao", findNotification);
        return "/notificacao/atualizar_notificacao.html";
    }

    @PostMapping("/salvarAtualizacaoNotificacao")
    public String salvarAtualizacaoNotificacao (Model model, @RequestParam String mensagem, @RequestParam String tipo, @RequestParam String prioridade, @RequestParam String criacao, @RequestParam int id){
        Notification updateNotification = new Notification();
        updateNotification.setId(id);
        updateNotification.setMensagem(mensagem);
        updateNotification.setTipo(tipo);
        updateNotification.setPrioridade(prioridade);
        updateNotification.setCriacao(criacao);
        notificationRepository.update(updateNotification);
        return "redirect:/notificacoes";
    }
    @GetMapping("/deletarNotificacao")
    public String deletarNotificacao(@RequestParam ("id") int id) {
        notificationRepository.delete(id);
        return "redirect:/notificacoes";
    }
}
