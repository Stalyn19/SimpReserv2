package com.simpreserv.service;

import com.simpreserv.model.NotFoundException;
import com.simpreserv.model.Room;
import com.simpreserv.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> listAll(String keyword){
        if (keyword != null){
            return roomRepository.findAll(keyword);
        }
        return roomRepository.findAll();
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public Room get(int id) throws NotFoundException {
        Optional<Room> result = roomRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any room with ID " + id);
    }

    public void delete(int roomId) throws NotFoundException {
        Long count = roomRepository.countByRoomId(roomId);
        if (count==null || count==0){
            throw new NotFoundException("Could not find any room with ID " + roomId);
        }
        roomRepository.deleteById(roomId);
    }



}
