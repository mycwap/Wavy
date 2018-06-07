package ie.miao.yichong.wavy.presenters;

import ie.miao.yichong.wavy.interfaces.UserActivityInterface;
import ie.miao.yichong.wavy.models.User;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static ie.miao.yichong.wavy.utils.ExceptionHandler.reportException;
import static ie.miao.yichong.wavy.utils.RetrofitInterface.getRESTApiInterface;

/**
 * Author:   yichongmiao
 * CreateAt: 05/06/2018.
 */

public class UserActivityPresenter extends BasePresenter {
    private UserActivityInterface userActivityInterface;


    public UserActivityPresenter( UserActivityInterface userActivityInterface) {
        this.userActivityInterface = userActivityInterface;
    }



    /**
     * Author:   yichongmiao
     * CreateAt: 05/06/2018.
     *
     * Get user Information
     *
     * */
    public void getUserInfo(){
        Subscription subscription = getRESTApiInterface()
                .getAllUserInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        //report failed
                        reportException(e);
                        userActivityInterface.dataLoaded(false);
                    }

                    @Override
                    public void onNext(User user) {
                        // get data
                        userActivityInterface.displayUser(user);
                        userActivityInterface.dataLoaded(true);
                    }
                });
        addSubscription(subscription);
    }

    /**
     * Author:   yichongmiao
     * CreateAt: 05/06/2018.
     *
     * Delete user phone number
     *
     * */
    public void deleteUser(String id){
        Subscription subscription = getRESTApiInterface()
                .deleteUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        userActivityInterface.deletePhoneNumber(false);


                        //report failed
                        reportException(e);

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        userActivityInterface.deletePhoneNumber(true);
                    }
                });
        addSubscription(subscription);
    }

}
