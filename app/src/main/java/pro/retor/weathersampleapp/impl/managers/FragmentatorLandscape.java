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
public class FragmentatorLandscape extends BaseFragmentsManager {
    private int container;

    public FragmentatorLandscape(int container) {
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
            transaction.replace(container, DetailFragment.newInstance(arg), tag);
    }

    private void mainList(String tag, Bundle arg, FragmentTransaction transaction) {
        if (!ifFragmentInStack(tag))
            transaction.replace(container, ListFragment.newInstance(arg), tag);
    }
}
