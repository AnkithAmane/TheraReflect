package com.example.mentalhealth.repository;

import com.example.mentalhealth.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {}
