package com.dan.dome.fragment.base;

import android.os.Bundle;
import android.view.WindowManager;

import com.dan.dome.R;
import com.dan.library.util.StatusBarUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dan on 2019/2/19 13:47
 */
public class BaseFragmentActivity extends FragmentActivity {

    protected AlertDialog.Builder alertDialog;

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new AlertDialog.Builder(getApplicationContext());
        StatusBarUtils.setWindowStatusBarColor(this, R.color.head_background_back_all);
        //软件盘自动打开
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //绑定当前视图
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
