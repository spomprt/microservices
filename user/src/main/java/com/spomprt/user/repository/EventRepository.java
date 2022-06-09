package com.spomprt.user.repository;

import com.spomprt.user.aggregate.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
