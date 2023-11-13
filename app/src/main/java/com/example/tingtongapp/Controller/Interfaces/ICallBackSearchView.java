package com.example.tingtongapp.Controller.Interfaces;

import com.example.tingtongapp.ClassOther.myFilter;

public interface ICallBackSearchView {
    public void addFilter(myFilter filter);
    public void replaceFilter(myFilter filter);
    public void removeFilter(myFilter filter);
}
