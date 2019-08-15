package com.muheda.mdtabcontainerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.muheda.mdtabcontainerview.adapter.ExampleOneAdapter;
import com.muheda.mdtabcontainerview.adapter.ExampleTwoAdapter;
import com.muheda.mdtabcontainerview.fragment.AppFragment;
import com.muheda.mdtabcontainerview.fragment.MainFragment;
import com.muheda.mdtabcontainerview.fragment.MineFragment;
import com.muheda.mdtabcontainerview.fragment.WorkFragment;
import com.muheda.tabcontainerview.Model.DefaultDto;
import com.muheda.tabcontainerview.TabContainerView;
import com.muheda.tabcontainerview.adapter.DefaultAdapter;
import com.muheda.tabcontainerview.listener.OnTabSelectedListener;
import com.muheda.tabcontainerview.widget.AbsTab;


public class MainActivity extends AppCompatActivity {

    private TabContainerView tabContainerView;
    private int[] iconImageArray, selectedIconImageArray;
    private Fragment[] fragments, fragments1;

    private int[][] mIconArray;
    private DefaultDto defaultDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolBar();
    }

    private void initView() {
        mIconArray = new int[][]{{R.drawable.icon_main, R.drawable.icon_main_selected},
//                {R.drawable.icon_work, R.drawable.icon_work_selected},
                {R.drawable.icon_app, R.drawable.icon_app_selected}, {R.drawable.icon_mine, R.drawable.icon_mine_selected}};

        iconImageArray = new int[]{R.mipmap.icon_home, R.mipmap.icon_data, R.mipmap.icon_navigation, R.mipmap.icon_me};
        selectedIconImageArray = new int[]{R.mipmap.icon_home_selected, R.mipmap.icon_data_selected, R.mipmap.icon_navigation_selected, R.mipmap.icon_me_selected};

        fragments = new Fragment[]{new MainFragment(), new WorkFragment(), new AppFragment(), new MineFragment()};
        fragments1 = new Fragment[]{new MainFragment(), new AppFragment(), new MineFragment()};

        tabContainerView = (TabContainerView) findViewById(R.id.tab_containerview_main);

        defaultDto = new DefaultDto(this, fragments, getSupportFragmentManager(), getResources().getStringArray(R.array.titleArray), Color.BLACK,
                getResources().getColor(R.color.colorPrimary), iconImageArray, selectedIconImageArray);

        tabContainerView.setAdapter(new DefaultAdapter(defaultDto))
                .setCurrentMessageItem(1).setCurrentMessageItem(2, 4)
                .setOnTabSelectedListener(new OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(AbsTab tab, int prePosition) {
                        tabContainerView.clearMessageItem(tab.getTabIndex());
                        Toast.makeText(MainActivity.this, "当前页position" + tab.getTabIndex() + "前一页position:" + prePosition, Toast.LENGTH_SHORT).show();

                        Fragment fragment = fragments[tab.getTabIndex()];
                        Fragment fragmentPre = fragments[prePosition];
                        if (fragment != null) {
                            fragment.onHiddenChanged(false);
                        }
                        if (fragmentPre != null) {
                            fragmentPre.onHiddenChanged(true);
                        }
                    }
                });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("TabContainerView");
        toolbar.inflateMenu(R.menu.base_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_default) {
                    tabContainerView.setAdapter(new DefaultAdapter(defaultDto));
                }
                if (item.getItemId() == R.id.action_one) {
                    tabContainerView.setAdapter(new ExampleOneAdapter(MainActivity.this, fragments, getSupportFragmentManager(),
                            getResources().getStringArray(R.array.exampleOneArray)));
                }
                if (item.getItemId() == R.id.action_two) {
                    tabContainerView.setAdapter(new ExampleTwoAdapter(MainActivity.this, fragments1, getSupportFragmentManager(),
                            mIconArray));
                }
                return true;
            }
        });
    }
}
