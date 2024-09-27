//unit2 アプリUI作成 Kotlinの基礎のオブジェクト指向
package sampledata
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
open class SmartDevice(val name: String, val category: String){//inheritable class

    var deviceStatus = "online"
        /*protected set(value){//省略可能
            field = value
        }*/
        protected set

    open val deviceType = "unknown" // can override value

    open fun turnOn(){// can override function
        deviceStatus = "on"
    }
    open fun turnOff(){// can override function
        deviceStatus = "off"
    }

    constructor(name: String, category: String, statusCode: Int) : this(name, category){
        deviceStatus = when(statusCode){
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory){//IS-A関係, SmartDevice型

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume(){
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel(){
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    override fun turnOn(){
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )

    }
    override fun turnOff(){
        super.turnOff()
        println("$name turned off")

    }

}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory){

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness(){
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }
    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
    // HAS-A関係
){

    var deviceTurnOnCount = 0
        private set //省略可能

    fun turnOnTv(){
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv(){
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }
    fun increaseTvVolume(){
        smartTvDevice.increaseSpeakerVolume()
    }
    fun changeTvChannelToNext(){
        smartTvDevice.nextChannel()
    }
    fun turnOnLight(){
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight(){
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrightness(){
        smartLightDevice.increaseBrightness()
    }
    fun turnOffAllDevices(){
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int>{

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int{
        return fieldData
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int){
        if (value in minValue..maxValue){
            fieldData = value
        }

    }

}

fun main() {
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()// polymorphism

    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()// polymorphism
}
