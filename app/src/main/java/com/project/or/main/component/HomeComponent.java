package com.project.or.main.component;

import com.project.or.main.module.HomeModule;
import com.project.or.main.view.HomeFragment;

import dagger.Component;

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
