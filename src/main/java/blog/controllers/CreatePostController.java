package blog.controllers;

import blog.forms.CreatePostForm;
import blog.forms.LoginForm;
import blog.forms.RegisterForm;
import blog.models.Post;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CreatePostController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/posts/create")
    public String createPost(LoginForm createPost) {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String create(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "posts/create";
        }

        Post post = new Post();
        String postTitle = post.getTitle();
        String postText = post.getBody();

        System.out.println( postTitle + " " + postText);

        postService.create(post);


        notifyService.addInfoMessage("Posting successful");
        return "redirect:/";
    }
}
