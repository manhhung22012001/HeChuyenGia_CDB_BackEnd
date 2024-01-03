/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.config;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author tranm
 */
@Component // Spring component, cho phép quản lý các bean của ứng dụng.
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppConfig {
     @NotNull
    private String appUrl;

    public String getAppUrl() {
        return appUrl;
    }
}