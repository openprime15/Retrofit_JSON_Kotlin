package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get 부분
        getButton.setOnClickListener {
//            alert("ok go"){}.show()
            val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:3000")
                                                        // winddow localhost를 폰으로 접근하기 때문에 이렇게 사용
                .addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(RetroInterface::class.java)
            service.getUser()?.enqueue(object :Callback<OKResult>{
                override fun onFailure(call: Call<OKResult>?, t: Throwable) {
                    alert("no"){}.show()
                }
                override fun onResponse(call: Call<OKResult>, response: Response<OKResult>) {
                    Log.d("Response :: ", response?.body().toString())
//                    alert("ok"){}.show()
                    Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG).show()

                    println(response.body()?.result.toString())
                }})
        }

        //post부분
        postButton.setOnClickListener {
            val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(RetroInterface::class.java)
            service.postUser(OKResult("abc"))?.enqueue(
                object:Callback<OKResult>{
                    override fun onFailure(call: Call<OKResult>, t: Throwable) {
                        alert("no"){}.show()
                    }
                    override fun onResponse(call: Call<OKResult>, response: Response<OKResult>) {
                        Log.d("Response :: ", response?.body().toString())
//                        alert("ok"){}.show()
                        Toast.makeText(this@MainActivity, response.body().toString(), Toast.LENGTH_LONG).show()
                        println(response.body()?.result.toString())
                    }
                }
            )

        }


    }


}
