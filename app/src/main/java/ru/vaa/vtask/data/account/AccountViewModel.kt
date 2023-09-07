package ru.vaa.vtask.data.account

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.vaa.vtask.data.signup.Profile
import ru.vaa.vtask.navigation.PostVTaskRouter
import ru.vaa.vtask.navigation.Screen

open class AccountViewModel : ViewModel() {

    private val TAG = AccountViewModel::class.simpleName

    val emailId: MutableLiveData<String> = MutableLiveData()
    val fullName = mutableStateOf("Unknown")

    init {
        getUserData()
    }

    private fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            Log.d(TAG, "user email: ${it.email}")
            it.email?.also { email -> emailId.value = email }
            if (it.uid.isNotEmpty()) {
                FirebaseDatabase.getInstance().getReference("Profile")
                    .child(it.uid)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(Profile::class.java)
                            Log.d(TAG, "Get data result: $user")
                            fullName.value = "${user?.firstName} ${user?.lastName}"
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.d(TAG, "Fail get user data, error: $error")
                        }
                    })
            }
        }
    }

    fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                PostVTaskRouter.navigateTo(Screen.LogInScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }
}