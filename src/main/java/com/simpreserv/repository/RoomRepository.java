package com.simpreserv.repository;

import com.simpreserv.model.Employee;
import com.simpreserv.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

  Long countByRoomId(Integer roomId);

  @Query("SELECT e FROM Room e WHERE e.roomNumber = ?1")
  Room findByRoomNumber(String roomNumber);

}
