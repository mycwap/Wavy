package ie.miao.yichong.wavy.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author:   yichongmiao
 * CreateAt: 06/06/2018.
 * */

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
