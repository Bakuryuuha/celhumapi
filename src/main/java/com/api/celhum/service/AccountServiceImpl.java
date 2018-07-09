package com.api.celhum.service;

import com.api.celhum.model.Account;
import com.api.celhum.model.PasswordResetToken;
import com.api.celhum.repository.AccountRepository;
import com.api.celhum.repository.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class AccountServiceImpl implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        accountRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        //accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(String user) {
        return accountRepository.findById(user);
    }

    @Override
    public Account updateById(String user) {
        return accountRepository.getOne(user);
    }

    @Override
    public Account save(Account user) {
        return accountRepository.save(user);
    }

    @Override
    public Account findByEmail(String user) {
        return accountRepository.findByEmail(user);
    }

}
