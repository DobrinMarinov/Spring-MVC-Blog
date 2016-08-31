package blog.controllers;

import blog.forms.CreatePostForm;
import blog.forms.LoginForm;
import blog.forms.RegisterForm;
import blog.models.Post;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class CreatePostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/posts/create")
    public String createPost(CreatePostForm createPostForm) {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String create(@Valid CreatePostForm createPostForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "posts/create";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userService.findByUsername(name);


        Post post = new Post();

        post.setTitle( createPostForm.getPostTitle());
        post.setBody( createPostForm.getPostText());
        post.setAuthor(user);
        post.setDate(new Date());

        String postTitle = post.getTitle();
        String postText = post.getBody();

        System.out.println( postTitle + " " + postText);

        postService.create(post);


        notifyService.addInfoMessage("Posting successful");
        return "redirect:/";
    }
}
