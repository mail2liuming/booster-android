package booster.mingliu.boostertest.restapi;


import booster.mingliu.boostertest.models.SubmitDataModel;
import retrofit2.http.Body;
import rx.Observable;
import retrofit2.http.POST;

/**
 * Created by liuming on 17/1/12.
 */
public interface RestApis {
    @POST("submit")
    Observable<String> submitInfoWithScore(@Body SubmitDataModel data);
}
