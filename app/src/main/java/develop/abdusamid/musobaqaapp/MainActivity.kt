package develop.abdusamid.musobaqaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import tyrantgit.explosionfield.ExplosionField
import develop.abdusamid.musobaqaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var explosionField: ExplosionField
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("animation")
        explosionField = ExplosionField.attach2Window(this)
        myListener()
    }

    private fun myListener() {
        binding.btnStart.setOnClickListener {
            databaseReference.setValue("start")

        }
        binding.btnStart.setOnLongClickListener {
            databaseReference.setValue("1")
            true
        }
        binding.btnFinish.setOnLongClickListener {
            databaseReference.setValue("2")
            true
        }
        binding.btnFinish.setOnClickListener {
            databaseReference.setValue("end")
        }
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                when (snapshot.value.toString()) {
                    "start" -> myStartFun()
                    "end" -> myFinishFun()
                    "1" -> funPlus()
                    "2" -> funMinus()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun myStartFun() {
        binding.apply {
            lottie1.playAnimation()
            lottie2.playAnimation()
            lottie1.speed += 1F
            lottie2.speed += 1F
        }
    }

    private fun myFinishFun() {
        binding.apply {
            lottie1.pauseAnimation()
            lottie2.pauseAnimation()
            lottie1.speed -= 1F
            lottie2.speed -= 1F
        }
    }

    private fun funPlus() {
        binding.lottie1.speed += 1F
        binding.lottie2.speed += 1F
    }

    private fun funMinus() {
        binding.lottie1.speed -= 1F
        binding.lottie2.speed -= 1F
    }
}