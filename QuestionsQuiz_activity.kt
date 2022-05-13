package com.example.quiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuestionsQuizActivity : AppCompatActivity(), View.OnClickListener {
    private var progressBar: ProgressBar = findViewById(R.id.progressBar)
    private var tvProgress: TextView = findViewById(R.id.tv_progress)
    private var tvQuestion: TextView = findViewById(R.id.tv_question)
    private var tvOptionOne: TextView = findViewById(R.id.tv_option_one)
    private var tvOptionTwo: TextView = findViewById(R.id.tv_option_two)
    private var tvOptionThree: TextView = findViewById(R.id.tv_option_three)
    private var tvOptionFour: TextView = findViewById(R.id.tv_option_four)
    private var myCurrentPosition:Int = 1
    private var myQuestionList: ArrayList<Question>? = null
    private var mySelectedOptionPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        myQuestionList = Constants.getQuestions()

        setQuestion()
        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
    }

    private fun setQuestion(){
        myCurrentPosition = 1
        val question = myQuestionList!![myCurrentPosition - 1]
        defaultOptionsView()
        progressBar.progress = myCurrentPosition
        tvProgress.text = "$myCurrentPosition" + "/" + progressBar.max
        tvQuestion.text = question!!.question
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tvOptionFour, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg)
    }
}
