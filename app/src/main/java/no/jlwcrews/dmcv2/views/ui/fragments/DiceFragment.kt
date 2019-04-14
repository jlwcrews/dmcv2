package no.jlwcrews.dmcv2.views.ui.fragments


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dice.*
import java.util.*


class DiceFragment : Fragment(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private val SHAKE_THRESHOLD = 3
    private lateinit var radioGroup: RadioGroup
    private var numberOfDice: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(no.jlwcrews.dmcv2.R.layout.fragment_dice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        radioGroup = getView()!!.findViewById(no.jlwcrews.dmcv2.R.id.diceGroup)

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {

            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                numberOfDice = when (checkedId) {
                    no.jlwcrews.dmcv2.R.id.radioDice1 -> 1
                    no.jlwcrews.dmcv2.R.id.radioDice2 -> 2
                    no.jlwcrews.dmcv2.R.id.radioDice3 -> 3
                    no.jlwcrews.dmcv2.R.id.radioDice4 -> 4
                    no.jlwcrews.dmcv2.R.id.radioDice5 -> 5
                    no.jlwcrews.dmcv2.R.id.radioDice6 -> 6
                    else -> 1
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }


    private fun generateRandomNumber() {
        var resultList: MutableList<Int> = mutableListOf()
        println("Generating $numberOfDice of random numbers")
        for (i in 1..numberOfDice){
            val randomGenerator = Random()
            val randomNum = randomGenerator.nextInt(6) + 1
            resultList.add(randomNum)
        }
        resultDiceView.text = resultList.toString()


    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat() - SensorManager.GRAVITY_EARTH

        if (acceleration > SHAKE_THRESHOLD) {
            generateRandomNumber()
        }
    }


}
