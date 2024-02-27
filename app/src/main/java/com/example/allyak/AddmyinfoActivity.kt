package com.example.allyak

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase

class AddmyinfoActivity : AppCompatActivity() {
    private lateinit var userId :String
    //현재 복용 중인 약
    lateinit var medinow : ToggleButton
    //부작용이 있는 약
    lateinit var medinot : ToggleButton
    //약 이름
    lateinit var mediname : EditText
    //메모
    lateinit var memo : EditText
    //확인 버튼
    lateinit var ok : Button
    //취소 버튼
    lateinit var cancel1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmyinfo)
        var tb: Toolbar = findViewById(R.id.toolbar)
        tb.setOnClickListener{
            finish()
        }
        // 버튼 및 토글 버튼 초기화
        ok = findViewById(R.id.oK)
        cancel1 = findViewById(R.id.cancel1)
        medinow = findViewById(R.id.medinow)
        medinot = findViewById(R.id.medinot)
        mediname = findViewById(R.id.mediname)
        memo = findViewById(R.id.medimemo)

        ok.setOnClickListener {
            //확인 버튼 클릭 시 동작
            //데이터 파이어 베이스에 저장 후 액티비티 종료
            //토글정보, 약이름, 메모 파이어베이스에 저장
            userId = "testtesttest223stetesttesttesttest223stetest"
            saveStore()
            finish()
        }
        cancel1.setOnClickListener {
            //취소 버튼 클릭 시 동작
            //액티비티 종료
            finish()
        }
    }
    private fun saveStore() {
        //firebase데이터베이스 레퍼런스
        val database = FirebaseDatabase.getInstance()
        val InfoRef = database.getReference("myinfo")

        //파이어베이스에 저장
        val togglestate1 = if (medinow.isChecked) "복용중" else "복용중 아님"
        val togglestate2 = if (medinot.isChecked) "부작용" else "부작용 없음"
        val mediname = mediname.text.toString()
        val memo = memo.text.toString()

        //데이터 베이스에 저장할 데이터 맵생성
        val myinfo = hashMapOf(
            "userId" to userId,
            "medinow" to togglestate1,
            "medinot" to togglestate2,
            "mediname" to mediname,
            "memo" to memo
        )

        //데이터 베이스에 데이터 쓰기
        InfoRef.push().setValue(myinfo)
            .addOnSuccessListener {
                Log.d("myinfo", "데이터베이스에 저장 성공")
            }
            .addOnFailureListener {
                Log.d("myinfo", "데이터베이스에 저장 실패")
            }

    }
}