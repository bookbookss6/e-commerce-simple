<template>
  <div class="header">
    <div class="collapse bg-dark" id="navbarHeader">
      <div class="container">
        <div class="row">
          <div class="col-sm-4 py-4">
            <h4 class="text-white">홈페이지</h4>
            <ul class="list-unstyled">
                <li>
                  <RouterLink to="/" class="text-white">메인 화면</RouterLink>
                </li>
                <li v-if="store.state.account.id">
                  <RouterLink to="/order" class="text-white">주문 내역</RouterLink>
                </li>
                <li>
                  <RouterLink to="/login" class="text-white" v-if="!store.state.account.id">로그인</RouterLink>
                  <a href="/" class="text-white" @click="logout()" v-else>로그아웃</a>
                </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
      <div class="container">
        <RouterLink to="/" class="navbar-brand d-flex align-items-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="none"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            aria-hidden="true"
            class="me-2"
            viewBox="0 0 24 24"
          >
            <path
              d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"
            />
            <circle cx="12" cy="13" r="4" />
          </svg>
          <strong>상점</strong>
        </RouterLink>
        <RouterLink to="/cart" class="cart btn">
          <i class="fa fa-shopping-cart" aria-hidden="true" v-show="store.state.account.id"></i>         
        </RouterLink>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarHeader"
          aria-controls="navbarHeader"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import store from '../scripts/store';
import router from '../scripts/router';
import axios_backend from '@/scripts/axios_backend';

const logout = ()=>{
  store.commit('setAccount',0);
  axios_backend.post("/api/account/logout")
    .then(()=>{
      alert("로그아웃됨.");
    })
  router.push({path:"/"});
};

</script>

<style scoped>
.header .navbar .cart{
  margin-left: auto;
  color: white;
}
</style>
