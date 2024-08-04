package com.br.account.service;


import com.br.account.entity.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
