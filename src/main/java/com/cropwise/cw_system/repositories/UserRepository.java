package com.cropwise.cw_system.repositories;

import com.cropwise.cw_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
