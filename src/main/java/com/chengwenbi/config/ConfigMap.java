package com.chengwenbi.config;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Deception:
 * @author:chengwenbi
 * @Date:2017/12/7 23:16
 */
@Component
public class ConfigMap {

    public  List<ConfigMap1> configMap1List;

    public  List<ConfigMap1> getConfigMap1List() {
        return configMap1List;
    }

    public  void setConfigMap1List(List<ConfigMap1> configMap1List) {
        this.configMap1List = configMap1List;
    }

}
