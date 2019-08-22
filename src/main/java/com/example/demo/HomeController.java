package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;


@Controller
public class HomeController {
    @Autowired
    MessagepostRepository messagepostRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String messageList(Model model) {
        model.addAttribute("messageposts", messagepostRepository.findAll());
        return "messagepost";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/add")
    public String messageForm(Model model) {
        model.addAttribute("messagepost", new Messagepost());
        return "messagepostform";
    }


    @PostMapping("/process")
    public String processForm(@ModelAttribute Messagepost messagepost, BindingResult result,
                              @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()){
            return  "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype","auto"));
            messagepost.setPic(uploadResult.get("url").toString());
            messagepostRepository.save(messagepost);
        }catch (IOException e){
            e.printStackTrace();
            return  "redirect:/add";}

        if (result.hasErrors()) {
            return "messagepostform";
        }
        messagepostRepository.save(messagepost);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("messagepost",messagepostRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, Model model){
        model.addAttribute("messagepost",messagepostRepository.findById(id).get());
        return "messagepostform";}

    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id){
        messagepostRepository.deleteById(id);
        return "redirect:/";
    }




}
