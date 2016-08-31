package blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreatePostForm {
    @Size(min = 2, max = 300, message = "Username size should be in the range [2...50]")
    private String postTitle;

    @NotNull
    @Size(min = 1, max = 5000)
    private String postText;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}