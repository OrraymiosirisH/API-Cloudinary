package guevara.alvarez.demo.controllers;

import com.cloudinary.utils.ObjectUtils;
import guevara.alvarez.demo.configs.CloudinaryConfig;
import guevara.alvarez.demo.models.Actor;
import guevara.alvarez.demo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
@Autowired
    ActorRepository actorrepo;
@Autowired
    CloudinaryConfig cloudc;

@RequestMapping("/")
    public String listActors(Model model){
    model.addAttribute("actors", actorrepo.findAll());
    return "list";

}

   @GetMapping("/add")
   public String newActor(Model model){
        model.addAttribute("actor", new Actor());
        return "form";
   }


    @PostMapping("/add")
    public String processActor(@ModelAttribute Actor actor, @RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return "redirect;/add";
        }
    try{
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
    actor.setHeadshot(uploadResult.get("url").toString());
    actorrepo.save(actor);
    } catch (IOException e){
        e.printStackTrace();
        return "redirect:/add";
        }
    return "redirect:/";

    }
}
