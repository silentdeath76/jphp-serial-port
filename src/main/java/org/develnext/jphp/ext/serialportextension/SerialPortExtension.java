package org.develnext.jphp.ext.serialportextension;

import org.develnext.jphp.ext.serialportextension.classes.SerialPortWrapper;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class SerialPortExtension extends Extension {
    public static final String NS = "php\\io";
    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }

    @Override
    public void onRegister(CompileScope compileScope) {
        registerWrapperClass(compileScope, com.fazecast.jSerialComm.SerialPort.class, SerialPortWrapper.class);
    }
}
