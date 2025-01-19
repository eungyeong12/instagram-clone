package com.example.instagramclone.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel()  {
    var id: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")

    // 로그인 성공했을 때 InputNumberActivity로 넘어가는 함수
    var showInputNumberActivity : MutableLiveData<Boolean> = MutableLiveData(false)

    // 비밀번호 찾기
    var showFindIdActivity: MutableLiveData<Boolean> = MutableLiveData(false)
}