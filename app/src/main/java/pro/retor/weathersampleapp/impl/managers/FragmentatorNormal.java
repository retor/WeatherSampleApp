package pro.retor.weathersampleapp.impl.managers;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import pro.retor.weathersampleapp.base.application.BaseFragmentsManager;
import pro.retor.weathersampleapp.impl.ui.detail.DetailFragment;
import pro.retor.weathersampleapp.impl.ui.list.ListFragment;

import static pro.retor.weathersampleapp.FragmentConstants.DETAIL_FRAGMENT;
import static pro.retor.weathersampleapp.FragmentConstants.LIST_FRAGMENT;

/**
 * Created by retor on 23.06.2016.
 */
public class FragmentatorNormal extends BaseFragmentsManager {
    private int container;

    public FragmentatorNormal(int container) {
        this.container = container;
    }

    @Override
    protected FragmentTransaction addFragment(String tag, Bundle arg, FragmentTransaction transaction) {
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        switch (tag) {
            case LIST_FRAGMENT:
                mainList(tag, arg, transaction);
                break;
            case DETAIL_FRAGMENT:
                detailFragment(tag, arg, transaction);
                break;
            default:
                mainList(LIST_FRAGMENT, null, transaction);
        }
        return transaction;
    }

    private void detailFragment(String tag, Bundle arg, FragmentTransaction transaction) {
        if (!ifFragmentInStack(tag))
            transaction.replace(container, DetailFragment.newInstance(arg), tag).addToBackStack(tag);
    }

    private void mainList(String tag, Bundle arg, FragmentTransaction transaction) {
        if (!ifFragmentInStack(tag))
            transaction.replace(container, ListFragment.newInstance(arg), tag);
    }
/*
    private void profileAddCardFragment(String tag, FragmentTransaction transaction) {
        if (ifFragmentInStack(tag)) {
            transaction.remove(fragmentFromStack(tag));
        }
        Fragment addCard = AddCardFragment.newInstance();
        transaction
                .replace(containerId, addCard, tag).addToBackStack(null);
    }

    private void profileRegMailFragment(String tag, Bundle arg, FragmentTransaction transaction) {
        if (ifFragmentInStack(tag)) {
            transaction.remove(fragmentFromStack(tag));
        }
        Fragment regMail = ProfileRegMailFragment.newInstance();
        if (arg != null)
            regMail.setArguments(arg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            regMail.setSharedElementEnterTransition(new AutoTransition());
            regMail.setEnterTransition(new Fade());
            regMail.setExitTransition(new Fade());
            regMail.setSharedElementReturnTransition(new AutoTransition());
        }
        transaction.replace(containerId, regMail, tag).addToBackStack(null);
    }

    private void authorizationFragment(String tag, Bundle arg, FragmentTransaction transaction) {
        Fragment fragment = fragmentFromStack(tag);
*//*        if (ifFragmentInStack(tag)) {
            transaction.remove(fragmentFromStack(tag));
        }*//*
        if (fragment == null) {
            fragment = ProfileAuthFragment.newInstance();
            if (arg != null)
                fragment.setArguments(arg);
        }
        transaction.replace(containerId, fragment, tag);
    }

    private void registrationFragment(String tag, Bundle arg, FragmentTransaction transaction) {
        Fragment profileRegistrationFragment = fragmentFromStack(tag);
        if (profileRegistrationFragment == null) {
            profileRegistrationFragment = ProfileRegistrationFragment.newInstance();
            if (arg != null)
                profileRegistrationFragment.setArguments(arg);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                profileRegistrationFragment.setSharedElementEnterTransition(new AutoTransition());
                profileRegistrationFragment.setEnterTransition(new Fade());
                profileRegistrationFragment.setExitTransition(new Fade());
                profileRegistrationFragment.setSharedElementReturnTransition(new AutoTransition());
            }
        }
        transaction.replace(containerId, profileRegistrationFragment, tag);

*//*        if (ifFragmentInStack(tag)) {
//                    profileRegistrationFragment = getFragmentManager().findFragmentByTag(tag);
//                    transaction.remove(fragmentFromStack(tag));
//                    transaction.show(profileRegistrationFragment);
        } else {


        }*//*
    }

    private void mainProfile(String tag, Bundle arg, FragmentTransaction transaction) {
        if (ifFragmentInStack(PROFILE_REGISTRATION_FRAGMENT)) {
            transaction.detach(fragmentFromStack(PROFILE_REGISTRATION_FRAGMENT)).remove(fragmentFromStack(PROFILE_REGISTRATION_FRAGMENT));
        }
        if (ifFragmentInStack(tag)) {
//            transaction.remove(fragmentFromStack(tag));
            transaction.replace(containerId, fragmentFromStack(tag), tag);
        } else {
            Fragment fragment = ProfileMainFragment.newInstance();
            if (arg != null)
                fragment.setArguments(arg);
            transaction.replace(containerId, fragment, tag);
        }
    }

    private void reviewsList(String tag, Bundle arg, FragmentTransaction transaction) {
        if (ifFragmentInStack(tag)) {
            transaction.remove(fragmentFromStack(tag));
        }
*//*        if (ifFragmentInStack(COURIER_ABOUT_FRAGMENT)) {
            transaction.detach(fragmentFromStack(COURIER_ABOUT_FRAGMENT));
        }*//*
        transaction
                .replace(containerId, ReviewsFragment.newInstance(arg), tag).addToBackStack(null);
    }*/
}
