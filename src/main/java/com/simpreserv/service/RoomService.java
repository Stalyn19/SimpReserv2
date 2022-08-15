package com.simpreserv.service;

import com.simpreserv.model.NotFoundException;
import com.simpreserv.model.Room;
import com.simpreserv.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

  @Autowired private RoomRepository roomRepository;

  public List<Room> listAll(String keyword){
    if (keyword!=null){
      return roomRepository.findAll();
    }
    return roomRepository.findAll();
  }

  public void save(Room room) {
    roomRepository.save(room);
  }

  public Room get(Integer id) throws NotFoundException {
    Optional<Room> result = roomRepository.findById(id);
    if (result.isPresent()){
      return result.get();
    }
    throw new NotFoundException("Could not find any Rooms with ID " + id);
  }

  public void delete(Integer roomId) throws NotFoundException {
    Long count = roomRepository.countByRoomId(roomId);
    if (count==null || count==0){
      throw new NotFoundException("Could not find any Rooms with ID " + roomId);
    }
    roomRepository.deleteById(roomId);
  }

}
