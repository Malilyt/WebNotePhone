package mesh.e.web_note_phone.controllers;

import mesh.e.web_note_phone.models.Phone;
import mesh.e.web_note_phone.models.User;
import mesh.e.web_note_phone.repo.PhoneRepository;
import mesh.e.web_note_phone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepo userRepo;



    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user,
                       Map<String, Object> model) {

        List<Phone> search = phoneRepository.findByUserId(user);
        Collections.reverse(search);

        for(int i = search.size()-1; i >= 5 ; i--){
            search.remove(i);
        }

        model.put("search", search);
        return "home";
    }

    @GetMapping("/search")
    public String search(Map<String, Object> model) {

        Iterable<Phone> search = null;
        model.put("title", "Note Phone");
        model.put("search", search);
        return "search";
    }

    @PostMapping("/filter")
    public String searchFilter(@RequestParam String addname, Map<String, Object> model) {
        Iterable<Phone> search = null;

       if(addname != null && !addname.isEmpty()){
            search = phoneRepository.findByName(addname);
       }

        model.put("title", "Note Phone");
        model.put("search", search);
        return "search";
    }


    @GetMapping("/add")
    public String add(Map<String, Object> model) {
        return "add";
    }

    @PostMapping("/add")
    public String postAdd(
            @AuthenticationPrincipal User user,
            @RequestParam String addname,
            @RequestParam Long addnumber,
            Map<String, Object> model) {

        Phone phone = new Phone(addname, addnumber, user);
        phoneRepository.save(phone);
        return "redirect:add";
    }


    @GetMapping("user-contact")
    public String userContact(@AuthenticationPrincipal User user,
                       Map<String, Object> model) {

        List<Phone> search = phoneRepository.findByUserId(user);
        Collections.reverse(search);


        model.put("search", search);
        return "user-contact";
    }

    @PostMapping("/user-contact-filter")
    public String userСontactFilter(@AuthenticationPrincipal User user, @RequestParam String addname, Map<String, Object> model) {
        Iterable<Phone> search = phoneRepository.findByUserIdAndName(user, addname);


        model.put("title", "Note Phone");
        model.put("search", search);
        return "user-contact";
    }

    @PostMapping("/user-contact/delete/{id}")
    public String userСontDelete(@PathVariable(value = "id") long id,  Map<String, Object> model) throws ClassCastException{

        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new ClassCastException());
        phoneRepository.delete(phone);

        return "redirect:/user-contact";

    }

    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(User user, Map<String, Object>model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message", "Такой пользователь существует");
            return "/login";
        }
        return "redirect:/home";
    }

}
