package com.company.greeninvoice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.greeninvoice.dto.User;

/**
 * UserDao interface extends JpaRepository.
 * @author Venkatraman
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, String>{

}
