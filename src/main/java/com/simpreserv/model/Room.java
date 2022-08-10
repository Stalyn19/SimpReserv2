package com.simpreserv.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    @Getter @Setter
    private Long roomId;

    @Column(name = "number")
    @Getter @Setter
    private int roomNumber;

    @Column(name = "floor")
    @Getter @Setter
    private int floor;

    @Column(name = "description")
    @Getter @Setter
    private String description;


    @Column(name = "room_price")
    @Getter @Setter
    private double roomPrice;

    @Column(name = "type")
    @Getter @Setter
    private String roomType;

    @Column(name = "available")
    @Getter @Setter
    private boolean roomStatus;

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", description='" + description + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType='" + roomType + '\'' +
                ", roomStatus=" + roomStatus +
                '}';
    }
}
