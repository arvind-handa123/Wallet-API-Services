package com.keypot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.keypot.app.entity.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
