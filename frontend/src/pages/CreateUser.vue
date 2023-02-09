<template>
<main class="form-signup w-100 m-auto">
  <form>
    <h1 class="h3 mb-3 fw-normal">계정 생성하기</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="이메일" v-model="user.email">
      <label for="floatingInput">이메일 주소</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호" v-model="user.password">
      <label for="floatingPassword">비밀번호</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit" @click.prevent="submit()">계정 생성</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
  </form>
</main>
</template>

<script setup>
import router from '@/scripts/router';
import axios from 'axios';
import { reactive } from 'vue';

const user = reactive({
        email:"",
        password:""
});

const submit = ()=>{
    

axios.post("/api/account/create-user",user).
    then(()=>{
        alert('계정 생성완료.')
        router.push({path:"/"})
    }).
    catch((reason)=>{
        alert(`계정 생성 실패. ${reason}`)
    })
}
</script>
<style>
.form-signup {
  max-width: 330px;
  padding: 15px;
}

.form-signup .form-floating:focus-within {
  z-index: 2;
}

.form-signup input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signup input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>