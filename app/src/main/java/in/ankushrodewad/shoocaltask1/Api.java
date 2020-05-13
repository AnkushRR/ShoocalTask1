package in.ankushrodewad.shoocaltask1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.github.com/repositories/19438/";

    @GET("issues")
    Call<List<RepoObject>> getRepoObjects();
}