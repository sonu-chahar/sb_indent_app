package com.chahar.indent.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;


@RemoteProxy(name="remoteFunctions1")
public class RemoteFunctions1 {
    @RemoteMethod
    public int calculateFoo() {
        return 42;
    }
}