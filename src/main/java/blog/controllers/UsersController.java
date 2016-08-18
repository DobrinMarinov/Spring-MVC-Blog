package blog.controllers;

import blog.models.Post;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/users")
    public String home(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);

        return "users";

    }

    @RequestMapping("/users/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);

        Set<Post> allUserPosts = user.getPosts();

        model.addAttribute("allUserPosts", allUserPosts);

        return "posts/allUserPosts";
    }
}
