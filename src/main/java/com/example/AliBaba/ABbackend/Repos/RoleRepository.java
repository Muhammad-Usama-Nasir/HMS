package com.example.AliBaba.ABbackend.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMRoles;
import com.example.AliBaba.ABbackend.ORM.ORMRoles;

@Repository
public interface RoleRepository extends JpaRepository<ORMRoles, Long> {

	
}
