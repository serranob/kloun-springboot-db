package com.web2.kloun;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model){
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);
        return "usuarios.html";
    }

    @GetMapping("/detalhesUsuario")
    public String detalhesUsuario (Model model, @RequestParam ("id") int id){
        User findUser = userRepository.findById(id);
        model.addAttribute("user", findUser);
        return "ver_usuario.html";
    }

    @GetMapping("/cadastrarUsuario")
    public String cadastrarUsuario(){
        return "login_cadastro.html";
    }

    @PostMapping("/armazenarUsuario")
    public String armazenarUsuario(Model model, @RequestParam String email, @RequestParam String senha, @RequestParam String nome, @RequestParam String sobrenome, @RequestParam String nascimento, @RequestParam String cpf, @RequestParam String celular) {
        User saveUser = new User();
        saveUser.setEmail(email);
        saveUser.setSenha(senha);
        saveUser.setNome(nome);
        saveUser.setSobrenome(sobrenome);
        saveUser.setNascimento(nascimento);
        saveUser.setCpf(cpf);
        saveUser.setCelular(celular);
        userRepository.save(saveUser);
        return "redirect:/home";
    }

    @GetMapping("/atualizarUsuario")
    public String atualizarUsuario (Model model, @RequestParam int id){
        User findUser = userRepository.findById(id);
        model.addAttribute("user", findUser);
        return "atualizar_usuario.html";
    }

    @PostMapping("/salvarAtualizacaoUsuario")
    public String salvarAtualizacaoUsuario (Model model, @RequestParam String email, @RequestParam String senha, @RequestParam String nome, @RequestParam String sobrenome, @RequestParam String nascimento, @RequestParam String cpf, @RequestParam String celular, @RequestParam int id){
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setEmail(email);
        updateUser.setSenha(senha);
        updateUser.setNome(nome);
        updateUser.setSobrenome(sobrenome);
        updateUser.setNascimento(nascimento);
        updateUser.setCpf(cpf);
        updateUser.setCelular(celular);
        userRepository.update(updateUser);
        return "redirect:/usuarios";
    }
    @GetMapping("/deletarUsuario")
    public String deletarUsuario(@RequestParam ("id") int id) {
        userRepository.delete(id);
        return "redirect:/usuarios";
    }
}
