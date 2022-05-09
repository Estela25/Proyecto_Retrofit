package Interface;

import org.w3c.dom.Comment;

import java.util.List;

import Model.Comments1;
import Model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
@GET("posts")
Call<List<Post>> getPosts();

    @GET("comments")
    Call<List<Comments1>> getComments();

}
