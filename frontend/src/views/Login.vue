<template>
  <div class="loginBox">
    <div class="loginBox_sub">
      <div class="loginBox_logoArea">
        <h1 class="loginBox_logoArea_logo mgT20">logo</h1>
      </div>
      <div class="loginBox_inputArea">
        <form @submit="loginUser">
          <div class="loginBox_inputArea_input-container">
            <label :class="data.formClass.id"
              >전화번호, 사용자 이름 또는 이메일</label
            >
            <input v-model="data.input.id" class="mgT4" />
          </div>
          <div class="loginBox_inputArea_input-container">
            <label :class="data.formClass.pw">비밀번호</label>
            <input v-model="data.input.pw" class="mgT4" type="password" />
          </div>
          <button
            class="mgT12 loginBox_inputArea_loginButton"
            type="submit"
            :disabled="data.disable.login"
          >
            로그인
          </button>
          <button class="mgT12 mgB12 loginBox_inputArea_loginButton">
            회원가입
          </button>
        </form>
        <hr class="mgT12 mgB12" />
        <button
          class="mgT12 loginBox_inputArea_kakaoButton"
          @click="kakaoLogin"
        ></button>
        <button
          class="mgT12 loginBox_inputArea_naverButton"
          @click="naverLogin"
        ></button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { watch, reactive } from "vue";
import { inject } from "vue";

const $router = inject("$router");

const data = reactive({
  input: {
    id: "",
    pw: ""
  },
  disable: {
    login: true
  },
  formClass: {
    id: "loginBox_inputArea_label",
    pw: "loginBox_inputArea_label"
  }
});
watch(
  () => data.input,
  (newValues, prevValues) => {
    if (newValues.id.length > 0) {
      data.formClass.id = "loginBox_inputArea_smallLabel";
    } else {
      data.formClass.id = "loginBox_inputArea_label";
    }
    if (newValues.pw.length > 0) {
      data.formClass.pw = "loginBox_inputArea_smallLabel";
    } else {
      data.formClass.pw = "loginBox_inputArea_label";
    }
    if (validPw(newValues.pw) && validId(newValues.id)) {
      data.disable.login = false;
    }
  },
  { deep: true }
);
const loginUser = () => {
  $router.push("/main");
};

const validId = (id) => {
  return id.length > 0;
};
const validPw = (pw) => {
  return pw.length > 0;
};
const kakaoLogin = () => {
  console.log(window.location);
  window.location.href = "http://127.0.0.1:1117/oauth2/authorization/kakao";
  // window.location.replace("/oauth2/authorization/kakao");
};
const naverLogin = () => {
  window.location.href = "http://127.0.0.1:1117/oauth2/authorization/naver";
};
</script>
