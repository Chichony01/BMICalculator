package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)){
            val bmi = weight.toFloat()/ ((height.toFloat()/100)*height.toFloat()/100)   // since dividing results in float
            val bmi2digit = String.format("%.2f",bmi).toFloat()     // converting float into 2 digits after decimal
            displayResult(bmi2digit)
            }
        }
    }


    private fun displayResult(Bmi : Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.setText(Bmi.toString())     // or we can write resultIndex.text = Bmi.toString()
        info.setText("(Normal range is 18.5 - 24.9)")  // OR  info.text = "(Normal range is 18.5 - 24.9)"

        // now we create 2 variables for description(i.e underweight,overweight) and for the color
        var resultText = ""     // setting default value as empty string
        var color = 0           // again setting default value of color as 0

        // now define colors in colors.xml file then using "When" block to define color and description

        when {
            Bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }

            Bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }

            Bmi in 25.00..29.99 ->{
                resultText = "Overweight"
                color = R.color.over_weight
            }

            Bmi > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        // now we aoing to set this program of converting color and showing description to resultDescription
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }



/*      now we need a funtion to validate user inputs as user may keep empty input fields.
        The function will return true if input fields are not empty                                 */

    private fun validateInput(weight: String? , height:String?): Boolean{       // to avoid null point exception we use safe call operator "?"
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Enter your weight!" , Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Enter your height!" , Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    /* now we will pass weight and height to this function
    and if it returns true then it will proceed further to calculate and display bmi */
}


// Basically in this app we have done layout designing and specifying colors of bg and cards then we write code for calculation and showing the data
/* firstly we added colors by going to app -> res -> values -> colors.xml
after adding colors we searched background in attributes section under activity_main.xml file   */