package org.develnext.jphp.ext.serialportextension.classes;

import com.fazecast.jSerialComm.*;
import org.develnext.jphp.ext.serialportextension.SerialPortExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.invoke.Invoker;
import php.runtime.lang.BaseWrapper;
import php.runtime.memory.LongMemory;
import php.runtime.memory.StringMemory;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Reflection.Name("SerialPort")
@Reflection.Namespace(SerialPortExtension.NS)
public class SerialPortWrapper extends BaseWrapper<SerialPort> {

    @Reflection.Property
    @Reflection.Final
    public static final int EVEN_PARITY = 2;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_CTS_ENABLED = 16;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_DISABLED = 0;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_DSR_ENABLED = 256;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_DTR_ENABLED = 4096;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_RTS_ENABLED = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_XONXOFF_IN_ENABLED = 65536;

    @Reflection.Property
    @Reflection.Final
    public static final int FLOW_CONTROL_XONXOFF_OUT_ENABLED = 1048576;

    @Reflection.Property
    @Reflection.Final
    public static final int LISTENING_EVENT_DATA_AVAILABLE = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int LISTENING_EVENT_DATA_RECEIVED = 16;

    @Reflection.Property
    @Reflection.Final
    public static final int LISTENING_EVENT_DATA_WRITTEN = 256;

    @Reflection.Property
    @Reflection.Final
    public static final int MARK_PARITY = 3;

    @Reflection.Property
    @Reflection.Final
    public static final int NO_PARITY = 0;

    @Reflection.Property
    @Reflection.Final
    public static final int ODD_PARITY = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int ONE_POINT_FIVE_STOP_BITS = 2;

    @Reflection.Property
    @Reflection.Final
    public static final int ONE_STOP_BIT = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int SPACE_PARITY = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int TIMEOUT_NONBLOCKING = 0;

    @Reflection.Property
    @Reflection.Final
    public static final int TIMEOUT_READ_BLOCKING = 16;

    @Reflection.Property
    @Reflection.Final
    public static final int TIMEOUT_READ_SEMI_BLOCKING = 1;

    @Reflection.Property
    @Reflection.Final
    public static final int TIMEOUT_SCANNER = 4096;

    @Reflection.Property
    @Reflection.Final
    public static final int TIMEOUT_WRITE_BLOCKING = 256;

    @Reflection.Property
    @Reflection.Final
    public static final int TWO_STOP_BITS = 3;

    @Reflection.Signature
    public static SerialPortWrapper[] getPorts(Environment env) {
        SerialPortWrapper[] ports = new SerialPortWrapper[SerialPort.getCommPorts().length];

        int index = 0;

        for (SerialPort port : SerialPort.getCommPorts()) {
            ports[index++] = new SerialPortWrapper(env, SerialPort.getCommPort(port.getSystemPortName()));
        }

        return ports;
    }

    @Reflection.Signature
    public static SerialPortWrapper getPort(Environment env, String portName) {
        return new SerialPortWrapper(env, SerialPort.getCommPort(portName));
    }


    public SerialPortWrapper(Environment env, SerialPort __wrappedObject) {
        super(env, __wrappedObject);
    }


    @Reflection.Signature
    public InputStream getInputStream() {
        return __wrappedObject.getInputStream();
    }

    @Reflection.Signature
    public OutputStream getOutputStream() {
        return __wrappedObject.getOutputStream();
    }

    @Reflection.Signature
    public Memory open() {
        __wrappedObject.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 10);
        return __wrappedObject.openPort() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory addDataListener(int event, Invoker callback) {

        return __wrappedObject.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return event;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                // прочитанные данные из порта
                if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    byte[] newData = new byte[__wrappedObject.bytesAvailable()];
                    int numRead = __wrappedObject.readBytes(newData, newData.length);

                    callback.callAny(new String(newData, StandardCharsets.UTF_8));

                    // отправленные данные в порт
                } else if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
                    byte[] newData = event.getReceivedData();
                    callback.callAny(new String(newData, StandardCharsets.UTF_8), newData.length);

                    // срабаотает если успешно отправились данные
                } else if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN) {
                    callback.callAny(Memory.TRUE);
                }
            }
        }) ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory bytesAvailable() {
        return LongMemory.valueOf(__wrappedObject.bytesAvailable());
    }

    @Reflection.Signature
    public Memory bytesAwaitingWrite() {
        return LongMemory.valueOf(__wrappedObject.bytesAwaitingWrite());
    }

    @Reflection.Signature
    public Memory clearBreak() {
        return __wrappedObject.clearBreak() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory clearDTR() {
        return __wrappedObject.clearBreak() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory clearRTS() {
        return __wrappedObject.clearBreak() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public void disablePortConfiguration() {
        __wrappedObject.disablePortConfiguration();
    }

    @Reflection.Signature
    public Memory getParity() {
        return LongMemory.valueOf(__wrappedObject.getParity());
    }

    @Reflection.Signature
    public Memory getPortDescription() {
        return StringMemory.valueOf(__wrappedObject.getPortDescription());
    }

    @Reflection.Signature
    public Memory getReadTimeout() {
        return LongMemory.valueOf(__wrappedObject.getReadTimeout());
    }

    @Reflection.Signature
    public static Memory getVersion() {
        return StringMemory.valueOf(SerialPort.getVersion());
    }

    @Reflection.Signature
    public Memory getWriteTimeout() {
        return LongMemory.valueOf(__wrappedObject.getWriteTimeout());
    }

    @Reflection.Signature
    public Memory readBytes() {
        byte[] buffer = new byte[__wrappedObject.bytesAvailable()];
        __wrappedObject.readBytes(buffer, __wrappedObject.bytesAvailable());

        return StringMemory.valueOf(new String(buffer, StandardCharsets.UTF_8).trim());
    }

    @Reflection.Signature
    public void removeDataListener() {
        __wrappedObject.removeDataListener();
    }

    @Reflection.Signature
    public Memory setBreak() {
        return __wrappedObject.setBreak() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public void setComPortParameters(int newBaudRate, int newDataBits, int newStopBits, int newParity) {
        __wrappedObject.setComPortParameters(newBaudRate, newDataBits, newStopBits, newParity);
    }

    @Reflection.Signature
    public void setComPortParameters(int newBaudRate, int newDataBits, int newStopBits, int newParity, boolean useRS485Mode) {
        __wrappedObject.setComPortParameters(newBaudRate, newDataBits, newStopBits, newParity, useRS485Mode);
    }

    @Reflection.Signature
    public void setFlowControl(int newFlowControllSettings) {
        __wrappedObject.setFlowControl(newFlowControllSettings);
    }

    @Reflection.Signature
    public void setNumDataBits(int newDataBits) {
        __wrappedObject.setNumDataBits(newDataBits);
    }

    @Reflection.Signature
    public void setNumStopBits(int newStopBits) {
        __wrappedObject.setNumStopBits(newStopBits);
    }

    @Reflection.Signature
    public void setParity(int newParity) {
        __wrappedObject.setParity(newParity);
    }

    @Reflection.Signature
    public void setRs485ModeParameters(boolean useRS485Mode, boolean rs485RtsActiveHigh, int delayBeforeSendMicroseconds, int delayAfterSendMicroseconds) {
        __wrappedObject.setRs485ModeParameters(useRS485Mode, rs485RtsActiveHigh, delayBeforeSendMicroseconds, delayAfterSendMicroseconds);
    }

    @Reflection.Signature
    public Memory writeBytes(String data) {
        return LongMemory.valueOf(__wrappedObject.writeBytes(data.getBytes(), data.length()));
    }

    @Reflection.Signature
    public Memory getNumStopBits() {
        return LongMemory.valueOf(__wrappedObject.getNumStopBits());
    }

    @Reflection.Signature
    public Memory getNumDataBits() {
        return LongMemory.valueOf(__wrappedObject.getNumDataBits());
    }

    @Reflection.Signature
    public Memory getFlowControlSettings() {
        return LongMemory.valueOf(__wrappedObject.getFlowControlSettings());
    }

    @Reflection.Signature
    public void setComPortTimeouts(int newTimeoutMode, int newReadTimeout, int newWriteTimeout) {
        __wrappedObject.setComPortTimeouts(newTimeoutMode, newReadTimeout, newWriteTimeout);
    }

    @Reflection.Signature
    public Memory getBaudRate() {
        return LongMemory.valueOf(__wrappedObject.getBaudRate());
    }

    @Reflection.Signature
    public void setBaudRate(int baudRate) {
        __wrappedObject.setBaudRate(baudRate);
    }

    @Reflection.Signature
    public Memory getCTS() {
        return __wrappedObject.getCTS() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory getDCD() {
        return __wrappedObject.getDCD() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public String getDescriptivePortName() {
        return __wrappedObject.getDescriptivePortName();
    }

    @Reflection.Signature
    public String getSystemPortName() {
        return __wrappedObject.getSystemPortName();
    }

    @Reflection.Signature
    public Memory getDSR() {
        return __wrappedObject.getDSR() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory getDTR() {
        return __wrappedObject.getDTR() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory setDTR() {
        return __wrappedObject.setDTR() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory getRI() {
        return __wrappedObject.getRI() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory getRTS() {
        return __wrappedObject.getRTS() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory setRTS() {
        return __wrappedObject.setRTS() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory closePort() {
        return __wrappedObject.closePort() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory isOpen() {
        return __wrappedObject.isOpen() ? Memory.TRUE : Memory.FALSE;
    }

    @Reflection.Signature
    public Memory getDeviceReadBufferSize() {
        return LongMemory.valueOf(__wrappedObject.getDeviceReadBufferSize());
    }

    @Reflection.Signature
    public Memory getDeviceWriteBufferSize() {
        return LongMemory.valueOf(__wrappedObject.getDeviceWriteBufferSize());
    }

    @Reflection.Signature
    public void __destruct() {
        __wrappedObject.removeDataListener();
        __wrappedObject.closePort();
    }
}
