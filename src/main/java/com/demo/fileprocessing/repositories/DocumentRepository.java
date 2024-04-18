package com.demo.fileprocessing.repositories;

import com.demo.fileprocessing.models.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Documents, Long> {
}
