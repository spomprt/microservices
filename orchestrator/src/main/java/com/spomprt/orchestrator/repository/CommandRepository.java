package com.spomprt.orchestrator.repository;

import com.spomprt.orchestrator.aggregate.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommandRepository extends JpaRepository<Command, UUID> {
}
