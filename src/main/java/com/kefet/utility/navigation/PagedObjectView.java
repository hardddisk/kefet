package com.kefet.utility.navigation;

import java.util.List;

public class PagedObjectView<T> {

    private NavigationInfo navInfo = new NavigationInfo();
    private List<T> objects;
    
    
    public PagedObjectView(int maxIndices, int pageSize){
        this.navInfo = new NavigationInfo(maxIndices, pageSize);
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {

        this.objects = objects;
    }

    public void setNavInfo(NavigationInfo navInfo) {

        this.navInfo = navInfo;
    }

    public NavigationInfo getNavInfo() {

        return navInfo;
    }

    public T getObject(Integer index) {
        return (T) objects.get(index);
    }

    public void setObject(Integer index, T object) {
        this.objects.add(index, object);
    }
}
