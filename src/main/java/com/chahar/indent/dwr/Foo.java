package com.chahar.indent.dwr;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteProperty;

@DataTransferObject(type = "hibernate5",
        params = @Param(name = "exclude", value = "propertyToExclude"))
public class Foo {
    @RemoteProperty
    private int foo;

    public int getFoo() {
        return foo;
    }

    @RemoteProperty
    public int getBar() {
        return foo * 42;
    }
}