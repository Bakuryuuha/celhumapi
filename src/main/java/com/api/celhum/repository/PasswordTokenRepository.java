package com.api.celhum.repository;

import com.api.celhum.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository  extends JpaRepository<Account, String> {
}
