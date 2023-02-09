<template>
  <div class="card shadow-sm">
    <span class="img" :style="{backgroundImage: `url(${props.item.img_path})`}"></span>
    <div class="card-body">
      <p class="card-text">
        <span>
          {{props.item.title}} &nbsp;
        </span>
        <span class="discount badge bg-danger">
          {{props.item.discountPer}} %
        </span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary" @click="addToCart(item.id)">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>   
        </button>
        <small class="price text-muted">
          {{numberWithCommas(props.item.price)}} ₩
        </small>
        <small class="real text-danger">
          {{numberWithCommas(props.item.price - (props.item.price * props.item.discountPer / 100)) }} ₩
        </small>
      </div>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios';
import { defineProps } from 'vue';
import {numberWithCommas} from '../scripts/numberWithCommas';
import store from '@/scripts/store';

const props = defineProps({
  item: String
});

const addToCart = (itemId) =>{
  if(store.state.account.id)
  {

    axios.post(`/api/cart/items/${itemId}`)
      .then(()=>{
        alert("장바구니에 추가되었습니다.");
      });
  }
  else
  {
    alert("로그인을 하세요.");
  }
} 

</script>
<style scoped>
  .card .img{
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }

  .card .card-body .price{
    text-decoration: line-through;
  }
</style>