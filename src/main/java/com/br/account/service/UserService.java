package com.br.account.service;

import com.br.account.entity.Feature;
import com.br.account.entity.News;
import com.br.account.entity.User;
import com.br.account.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para encontrar usuário por ID
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    // Método para criar um novo usuário
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("Este número de conta já existe");
        }
        return userRepository.save(userToCreate);
    }

    // Método para atualizar um usuário existente
    public User updateUser(Long id, User updatedUser) {
        User existingUser = findById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setAccount(updatedUser.getAccount());
        existingUser.setCard(updatedUser.getCard());
        existingUser.setFeatures(updatedUser.getFeatures());
        existingUser.setNews(updatedUser.getNews());
        return userRepository.save(existingUser);
    }

    // Método para deletar um usuário por ID
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    // Método para encontrar todos os usuários
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Método para encontrar usuário pelo número da conta
    public User findByAccountNumber(String accountNumber) {
        return (User) userRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("\n" +
                "Usuário não encontrado com número de conta: " + accountNumber));
    }

    // Método para adicionar uma nova Feature a um usuário
    public User addFeatureToUser(Long userId, Feature feature) {
        User user = findById(userId);
        user.getFeatures().add(feature);
        return userRepository.save(user);
    }

    // Método para adicionar uma nova News a um usuário
    public User addNewsToUser(Long userId, News news) {
        User user = findById(userId);
        user.getNews().add(news);
        return userRepository.save(user);
    }
}
