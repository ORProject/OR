package com.project.or.main.component;

import com.project.or.main.module.HomeModule;
import com.project.or.main.view.HomeFragment;

import dagger.Component;

/**
 * Created by INNO-09 on 2016-04-07.
 */

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
