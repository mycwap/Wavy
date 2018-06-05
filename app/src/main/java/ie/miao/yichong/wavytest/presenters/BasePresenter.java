package ie.miao.yichong.wavytest.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yichongmiao on 01/05/2017.
 */

public class BasePresenter {
    //Addd all Subscription to CompositeSubscription. And unsubscribe all when destroy
    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    //unsubscribe
    public void unsubcription() {

        if (mCompositeSubscription != null) {

            mCompositeSubscription.unsubscribe();
        }
    }
}
