<template>
<div class="form-signin w-100 m-auto">
  <form>
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
             @keyup.enter="submit()" v-model="state.form.email">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" @keyup.enter="submit()" v-model="state.form.password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit" @click.prevent="submit()">로그인</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
    <RouterLink to="create-user" class="w-100 btn btn-lg btn-success">계정 생성.</RouterLink>
  </form>
</div>

</template>
<script setup>
import router from '@/scripts/router';
import axios from 'axios';
import { reactive } from 'vue';
import store from '../scripts/store';

const state = reactive({
    form:{
        email:"",
        password:""
    }
});

const submit = () =>{
  axios.post("/api/account/login" ,state.form)
      .then(({data})=>{
          store.commit('setAccount',data);
          sessionStorage.setItem("id",data);
          alert('로그인 완료');
          router.push({path:"/"});
      })
      .catch(()=>{
          alert('로그인 실패');
      });
}

</script>
<style scoped> 
.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>