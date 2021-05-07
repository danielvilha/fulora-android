package com.fulora.online.source.remote

import android.util.Log
import com.fulora.online.model.PlantingArea
import com.google.firebase.database.*
import kotlinx.coroutines.delay
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


private var TAG = RealtimeDatabaseManager::class.java.name
/**
 * Created by danielvilha on 05/05/21
 * https://github.com/danielvilha
 */
class RealtimeDatabaseManager {

    private var PLANTING_AREA = "planting_area"

    suspend fun addPlant(uid: String, planting: PlantingArea): Boolean {
        // Write a planting area to the database
        var maxValue = 0L

        FirebaseDatabase.getInstance().getReference(PLANTING_AREA).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                    maxValue = snapshot.childrenCount
            }

            override fun onCancelled(error: DatabaseError) {  }
        })


        val value = HashMap<String, Any>()
        value["name"] = planting.name.toString()
        value["picture"] = planting.picture.toString()
        value["userId"] = planting.userId.toString()
        value["dateTime"] = planting.dateTime.toString()

        val database = FirebaseDatabase.getInstance().getReference(PLANTING_AREA)
            .child(uid)
            .child((maxValue+1).toString())
            .setValue(value)

        delay(2000L)

        return database.isSuccessful
    }

    suspend fun getPlantings(uid: String): ArrayList<PlantingArea> {
        val plants = ArrayList<PlantingArea>()

        val database = FirebaseDatabase.getInstance().getReference(PLANTING_AREA).child(uid)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "snapshot:onDataChange: $snapshot")
                for (postSnapshot in snapshot.children) {
                    Log.d(TAG, "loadPost:onDataChange: $postSnapshot")
                    Log.d(TAG, "loadPostValue:onDataChange: ${postSnapshot.value.toString()}")

                    plants.add(
                        PlantingArea(
                            name = postSnapshot.child("name").value.toString(),
                            picture = postSnapshot.child("picture").value.toString(),
                            userId = postSnapshot.child("userId").value.toString(),
                            dateTime = postSnapshot.child("dateTime").value.toString()
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException())
            }
        })

        delay(2000L)
        return plants
    }
}