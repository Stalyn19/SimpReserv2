package com.simpreserv.controller;

import com.simpreserv.model.Employee;
import com.simpreserv.model.NotFoundException;
import com.simpreserv.model.Room;
import com.simpreserv.service.EmployeeService;
import com.simpreserv.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RoomController {

  @Autowired
  private RoomService roomService;

  @GetMapping("/rooms")
  public String showEmployeesList(Model model, @Param("keyword") String keyword){
    List<Room> listEmployees = roomService.listAll(keyword);
    model.addAttribute("listRooms", listEmployees);
    model.addAttribute("keyword", keyword);
    return "rooms";
  }

  @GetMapping("/rooms/new")
  public String showNewForm(Model model){
    model.addAttribute("room", new Employee());
    model.addAttribute("pageTitle", "Add New Room");
    return "room_form";
  }

  @PostMapping("/rooms/save")
  public String saveEmployee(Room room, RedirectAttributes ra){
    roomService.save(room);
    ra.addFlashAttribute("message","The room has been saved successfully!!");
    return "redirect:/rooms";
  }

  @GetMapping("/room/edit/{id}")
  public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
    try {
      Room room = roomService.get(id);
      model.addAttribute("room", room);
      model.addAttribute("pageTitle", "Edit Room (ID: "+ id +")");
      ra.addFlashAttribute("message","The Room ID " + id + " has been updated");
      return "room_form";
    } catch (NotFoundException e) {
      ra.addFlashAttribute("message",e.getMessage());
      return "redirect:/room";
    }
  }

  @GetMapping("/room/delete/{id}")
  public String deleteRoom(@PathVariable("id") Integer id, RedirectAttributes ra){
    try {
      roomService.delete(id);
      ra.addFlashAttribute("message","The Room ID " + id + " has been eliminated");
    } catch (NotFoundException e) {
      ra.addFlashAttribute("message",e.getMessage());
    }
    return "redirect:/rooms";
  }
}
