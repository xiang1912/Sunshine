package com.example.tonjies.templateproject.app;

/**
 * App基础配置
 */
public class AppConfig {
    private static AppConfig instance;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * 初始化
     */
    public void init() {

    }
}