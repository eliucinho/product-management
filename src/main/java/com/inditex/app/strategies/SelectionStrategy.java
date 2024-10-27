package com.inditex.app.strategies;

public interface SelectionStrategy<T,R>{
    R process(T request);
}