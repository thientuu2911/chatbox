package com.tma.chatbox.repository;

import com.tma.chatbox.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, UUID> {
}
