package com.simpreserv.repository;

import com.simpreserv.model.Employee;
import com.simpreserv.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Long countByRoomId(Integer RoomId);

    @Query("SELECT e FROM Room e WHERE e.number = ?1")
    Employee findByRoomNumber(String room);

    @Query("SELECT e FROM Room e WHERE CONCAT(e.number, e.floor, e.room_price) LIKE %?1%")
    public List<Room> findAll(String keyword);
}
