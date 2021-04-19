<?php
namespace php\io;

class SerialPort {
    /* */
    const EVEN_PARITY = 2;
    const FLOW_CONTROL_CTS_ENABLED = 16;
    const FLOW_CONTROL_DISABLED = 0;
    const FLOW_CONTROL_DSR_ENABLED = 256;
    const FLOW_CONTROL_DTR_ENABLED = 4096;
    const FLOW_CONTROL_RTS_ENABLED = 1;
    const FLOW_CONTROL_XONXOFF_IN_ENABLED = 65536;
    const FLOW_CONTROL_XONXOFF_OUT_ENABLED = 1048576;
    const LISTENING_EVENT_DATA_AVAILABLE = 1;
    const LISTENING_EVENT_DATA_RECEIVED = 16;
    const LISTENING_EVENT_DATA_WRITTEN = 256;
    const MARK_PARITY = 3;
    const NO_PARITY = 0;
    const ODD_PARITY = 1;
    const ONE_POINT_FIVE_STOP_BITS = 2;
    const ONE_STOP_BIT = 1;
    const SPACE_PARITY = 1;
    const TIMEOUT_NONBLOCKING = 0;
    const TIMEOUT_READ_BLOCKING = 16;
    const TIMEOUT_READ_SEMI_BLOCKING = 1;
    const TIMEOUT_SCANNER = 4096;
    const TIMEOUT_WRITE_BLOCKING = 256;
    const TWO_STOP_BITS = 3;

    public function addDataListener(int $event, $callback) {}

    public function removeDataListener() {}

    public function bytesAvailable() {}

    public function bytesAwaitingWrite() {}

    public function clearDTR() {}

    public function clearRTS() {}

    public function disablePortConfiguration() {}

    public function getPortDescription() {}

    public function getReadTimeout() {}

    public static function getVersion() {}

    public function getWriteTimeout() {}

    public function readBytes() {}

    public function setBreak() {}
    public function clearBreak() {}

    public function setComPortParameters(int $newBaudRate, int $newDataBits, int $newStopBits, int $newParity, bool $useRS485Mode = false) {}


	public function getParity() {}
    public function setParity(int $newParity) {}

    public function setRs485ModeParameters(boolean $useRS485Mode, bool $rs485RtsActiveHigh, int $delayBeforeSendMicroseconds, int $delayAfterSendMicroseconds) {}

    public function writeBytes(String $data) {}

    public function getNumStopBits() {}
    public function setNumStopBits(int $newStopBits) {}

    public function getNumDataBits() {}
    public function setNumDataBits(int $newDataBits) {}

    public function getFlowControlSettings() {}
    public function setFlowControl(int $newFlowControllSettings) {}

    public function setComPortTimeouts(int $newTimeoutMode, int $newReadTimeout, int $newWriteTimeout) {}

    /**
     * @return SerialPortWrapper[]
     */
    public static function getPorts() {}

    /**
     * @param $portName String example COM3
     */
    public static function getPort($portName) {}

    /**
     * @return MiscStream
     */
    public function getInputStream() {}

    /**
     * @return MiscStream
     */
    public function getOutputStream() {}

    /**
     * @return bool
     */
    public function open() {}

    /**
     * @return int
     */
    public function getBaudRate() {}

    /**
     * @param $baudRate int
     * @return void
     */
    public function setBaudRate(int $baudRate) {}

    /**
     * @return bool
     */
    public function getCTS() {}

    /**
     * @return bool
     */
    public function getDCD() {}

    /**
     * @return string
     */
    public function getDescriptivePortName() {}

    /**
     * @return string
     */
    public function getSystemPortName() {}

    /**
     * @return bool
     */
    public function getDSR() {}

    /**
     * @return bool
     */
    public function getDTR() {}

    /**
     * @return bool
     */
    public function setDTR() {}

    /**
     * @return bool
     */
    public function getRI() {}

    /**
     * @return bool
     */
    public function getRTS() {}

    /**
     * @return bool
     */
    public function setRTS() {}

    /**
     * @return bool
     */
    public function closePort() {}

    /**
     * @return int
     */
    public function getDeviceReadBufferSize() {}
    /**
     * @return int
     */
    public function getDeviceWriteBufferSize() {}

    /**
     * @return bool
     */
    public function isOpen() {}
}