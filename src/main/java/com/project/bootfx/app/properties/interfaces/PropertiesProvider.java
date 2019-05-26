package com.project.bootfx.app.properties.interfaces;

import javafx.beans.property.StringProperty;

import java.util.Map;

public interface PropertiesProvider {
    Map<String, StringProperty> provideProperties();
}
