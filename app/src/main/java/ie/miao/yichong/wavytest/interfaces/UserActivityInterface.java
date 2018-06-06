package ie.miao.yichong.wavytest.interfaces;

import ie.miao.yichong.wavytest.models.User;

/**
 * Author:   yichongmiao
 * CreateAt: 05/06/2018.
 *
 * User Activity Interface
 */

public interface UserActivityInterface {

    /**
     * display user after data loaded
     * @param user
     *
     * */
    void displayUser(User user);


    /**
     * call after phone delete action
     * @param success if delete success
     *
     * */
    void deletePhoneNumber(boolean success);



    /**
     *
     * call after user load action
     * @param success if load success
     *
     * */
    void dataLoaded(boolean success);

}
