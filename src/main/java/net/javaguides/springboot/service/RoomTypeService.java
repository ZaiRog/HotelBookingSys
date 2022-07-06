package net.javaguides.springboot.service;

import net.javaguides.springboot.model.RoomType;

import java.util.List;

public interface RoomTypeService {

    List<RoomType> allTypeOfRooms();
    void getTypeOfRoomsById(long id);
    RoomType getTypeOfRoomsByType(String typeOfRooms);
}
