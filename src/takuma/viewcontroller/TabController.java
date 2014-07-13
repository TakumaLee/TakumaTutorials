package takuma.viewcontroller;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

public class TabController implements TabHost.OnTabChangeListener {
	
	private FragmentActivity mActivity;
    private TabHost mTabHost;
    private int mContainerId;
    private HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
    TabInfo mLastTab;
    
    public Menu menu;

    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;

        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    public TabController(FragmentActivity activity, TabHost tabHost, int containerId) {
        mActivity = activity;
        mTabHost = tabHost;
        mContainerId = containerId;
        mTabHost.setOnTabChangedListener(this);
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(mActivity));
        String tag = tabSpec.getTag();

        TabInfo info = new TabInfo(tag, clss, args);

        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (info.fragment != null && !info.fragment.isRemoving()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            ft.remove(info.fragment);
            ft.commitAllowingStateLoss();
        }

        mTabs.put(tag, info);
        mTabHost.addTab(tabSpec);
    }

    @Override
    public void onTabChanged(String tabId) {
     TabInfo newTab = mTabs.get(tabId);

        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.remove(mLastTab.fragment);
                }
            }

            if (newTab != null) {
                newTab.fragment = Fragment.instantiate(mActivity,
                        newTab.clss.getName(), newTab.args);
                ft.add(mContainerId, newTab.fragment, newTab.tag);
                if (newTab.fragment == null) {
                    ft.remove(mLastTab.fragment);
                } else {
                    mActivity.getSupportFragmentManager().popBackStack();
                    if (mLastTab != null)
                    	ft.remove(mLastTab.fragment);
//                    ft.replace(mContainerId, newTab.fragment);
//                    ft.attach(newTab.fragment);
                }
            }
            
            mLastTab = newTab;
            ft.commitAllowingStateLoss();
            newTab = null;
            mActivity.onCreateOptionsMenu(menu);
            System.gc();
//            if (mActivity != null && mActivity.getSupportFragmentManager() != null)
//            	mActivity.getSupportFragmentManager().executePendingTransactions();
        }
    
    }
    
    public Fragment getCurrentFragment() {
    	return mLastTab.fragment;
    }
    
    public String getCurrentTag() {
    	return mLastTab.tag;
    }
    
    public void onDestroy() {
    	mActivity = null;
    	mTabHost = null;
    	mTabs = null;
    	mLastTab = null;
    }

}
