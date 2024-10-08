package com.br.account.service;

import com.br.account.entity.Account;
import com.br.account.entity.Transfer;
import com.br.account.entity.User;
import com.br.account.repository.TransferRepository;
import com.br.account.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    public TransferService(UserRepository userRepository, TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new NoSuchElementException("Usuário de origem não encontrado."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new NoSuchElementException("Usuário de destino não encontrado."));

        Account fromAccount = fromUser.getAccount();
        Account toAccount = toUser.getAccount();

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para a transferência.");
        }

        // Realiza a transferência
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Salva os estados atualizados das contas
        userRepository.save(fromUser);  // Salva o usuário com a conta atualizada
        userRepository.save(toUser);    // Salva o usuário com a conta atualizada

        // Cria e salva a transferência
        Transfer transfer = new Transfer();
        transfer.setFromUser(fromUser);  // Atribui o objeto User diretamente
        transfer.setToUser(toUser);      // Atribui o objeto User diretamente
        transfer.setAmount(amount);
        transfer.setTransferDate(LocalDateTime.now());

        transferRepository.save(transfer);
    }


    public List<String> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();

        return transfers.stream()
                .map(transfer -> "De: " + transfer.getFromUser().getName() + ", Para: " + transfer.getToUser().getName() + ", Transferência: " + transfer.getAmount())
                .collect(Collectors.toList());
    }


}

