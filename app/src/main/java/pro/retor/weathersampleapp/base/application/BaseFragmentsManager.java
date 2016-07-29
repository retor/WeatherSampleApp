package pro.retor.weathersampleapp.base.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by retor on 06.03.2016.
 */
public abstract class BaseFragmentsManager {
    private FragmentManager fragmentManager;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    protected boolean ifFragmentInStack(String tag){
        return getFragmentManager().findFragmentByTag(tag)!=null;
    }

    protected Fragment fragmentFromStack(String tag){
        return getFragmentManager().findFragmentByTag(tag);
    }

    public void switchFragment(String tag){
        switchFragment(tag, null);
    }

    public void switchFragment(String tag, Bundle arg){
        if (getFragmentManager()!=null) {
            addFragment(tag, arg, getFragmentManager().beginTransaction()).commit();
        }else {
            throw new NullPointerException("Set FragmentManager at first");
        }
    }

    public void switchFragmentWithTransitions(String tag, Bundle arg, Map<String, View> elements){
        FragmentTransaction transaction;
        if (getFragmentManager()!=null) {
            transaction = addFragment(tag, arg, getFragmentManager().beginTransaction());
        }else {
            throw new NullPointerException("Set FragmentManager at first");
        }
        if (elements!=null && !elements.isEmpty()){
            Iterator<String> iterator = elements.keySet().iterator();
            while (iterator.hasNext()){
                transaction.addSharedElement(elements.get(iterator.next()),iterator.next());
            }
        }
        transaction.commit();
    }

    protected abstract FragmentTransaction addFragment(String tag, Bundle arg, FragmentTransaction transaction);

}
