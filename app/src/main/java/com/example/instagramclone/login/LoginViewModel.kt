package com.example.instagramclone.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.instagramclone.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginViewModel(application: Application) : AndroidViewModel(application)  {
    var auth = FirebaseAuth.getInstance()
    var id: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")

    // 로그인 성공했을 때 InputNumberActivity로 넘어가는 함수
    var showInputNumberActivity : MutableLiveData<Boolean> = MutableLiveData(false)

    // 비밀번호 찾기
    var showFindIdActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    val context = getApplication<Application>().applicationContext

    var googleSignInClient : GoogleSignInClient
    init {
       var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
           .requestEmail()
           .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }
    fun loginWithSignupEmail() {
        auth.createUserWithEmailAndPassword(id.value.toString(), password.value.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                showInputNumberActivity.value = true
            } else {
                // 아이디가 있을 경우

            }
        }
    }

    fun loginGoogle(view : View) {
        var i = googleSignInClient.signInIntent
        (view.context as? LoginActivity)?.googleLoginResult?.launch(i)
    }

    fun firebaseAuthWithGoogle(idToken : String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showInputNumberActivity.value = true
            } else {
                // 아이디가 있을 경우
            }
        }
    }
}