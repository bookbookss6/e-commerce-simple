<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="(i,idx) in state.items" :key="idx">
          <img :src="i.img_path"/>
          <span class="name">{{i.title}}</span>
          <span class="name">{{numberWithCommas(i.price - i.price * i.discountPer / 100)}} ₩</span>
          <i class="fa fa-trash" @click="remove(i.id)"></i>
        </li>
      </ul>
      <RouterLink to="/order" class="btn btn-primary">구입하기</RouterLink>
    </div>
  </div>
</template>
<script setup>
import axios_backend from '@/scripts/axios_backend';
import { reactive } from 'vue';
import { numberWithCommas } from '../scripts/numberWithCommas';

const state = reactive({
  items:[]
});

const load = () =>{
  axios_backend.get("/api/cart/items")
  .then(({data})=>{
    console.log(data);
    state.items = data;
  })
}

const remove = (itemId) =>{
  axios_backend.delete(`/api/cart/items/${itemId}`).then(()=>{
    load();
  })
}

load();

</script>
<style scoped> 
.cart ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li{
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img{
  width: 150px;
  height: 150px;
}

.cart ul li .name
{
  margin-left: 25px;
}
.cart ul li .price
{
  margin-left: 25px;
}

.cart ul li i
{
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}
.cart .btn{
  width: 150px;
  display: block;
  margin: 10px auto;
  padding: 20px 20px;
  font-size: 20px;
}
</style>