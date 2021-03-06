package Interface;

import org.w3c.dom.Comment;

import java.util.List;

import Model.Album;
import Model.Comments1;
import Model.Post;
import Model.Todo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
@GET("posts")
Call<List<Post>> getPosts();

    @GET("comments")
    Call<List<Comments1>> getComments();

    @GET("albums")
    Call<List<Album>> getAlbums();

    @GET("todos")
    Call<List<Todo>> getTodos();

}
