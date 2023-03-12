package com.ubaya.adv160420039week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random


class GameFragment : Fragment() {
    var number1 = Random.nextInt(0, 100)
    var number2 = Random.nextInt(0, 100)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        txtNum1.text = number1.toString()
        txtNum2.text = number2.toString()

        Global.result = number1 + number2
        txtScore.text = Global.score.toString()

        var answer = txtAnswer.text

        btnSubmit.setOnClickListener {
            if(answer.toString() == Global.result.toString()){
                Global.score+=1
                Toast.makeText(activity, "Your answer is correct", Toast.LENGTH_SHORT).show()

                val action = GameFragmentDirections.actionGameFragmentSelf(Global.playerName)
                Navigation.findNavController(it).navigate(action)
            }
            else
            {
                val action = GameFragmentDirections.actionResultFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }
    }


}

