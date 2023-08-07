package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate){
            calculate()
        }
    }

    // função que verifica a validação dos dados: se são nulos os campos
    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                //agora verifica se o autonomy recebe o valor 0, pois não pode dividir com o valor 0
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            val totalValueStr = "R$ ${"%.2f".format(totalValue)}"

            binding.textTotal.text = totalValueStr
        } else {
            Toast.makeText(this, R.string.validation, Toast.LENGTH_SHORT).show()
        }

    }
}