package com.inditex.app.demo.strategies;

public interface SelectionStrategy<T,R>{
    R process(T request);
}