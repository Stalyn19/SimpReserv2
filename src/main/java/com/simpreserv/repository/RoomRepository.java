package com.simpreserv.repository;

import com.simpreserv.model.Employee;
import com.simpreserv.model.Room;
import com.simpreserv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

  Long countByRoomId(Integer roomId);

  @Query("SELECT e FROM Room e WHERE e.roomNumber = ?1")
  Room findByRoomNumber(String roomNumber);

  @Query("SELECT r FROM Room r WHERE CONCAT(r.roomNumber, r.floor, r.roomPrice, r.roomStatus) LIKE %?1%")
  public List<Room> findAll(String keyword);
}
