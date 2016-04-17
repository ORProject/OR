package com.project.or.main.component;

import com.project.or.main.module.NoticeModule;
import com.project.or.main.view.NoticeFragment;

import dagger.Component;

@Component(modules = NoticeModule.class)
public interface NoticeComponent {
    void inject(NoticeFragment homeFragment);
}
