<template>
  <div class="container">
    <div class="order">
      <main>
        <div class="py-5 text-center">
          <h2>주문하기</h2>
        </div>
        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">장바구니</span
              ><span class="badge bg-primary rounded-pill">
                {{ state.items.length }}
              </span>
            </h4>
            <ul class="list-group mb-3">
              <li
                class="list-group-item d-flex justify-content-between lh-sm"
                v-for="(i, idx) in state.items"
                :key="idx"
              >
                <div>
                  <h6 class="my-0">{{ i.title }}</h6>
                  <small class="text-muted">설명서</small>
                </div>
                <span class="text-muted">
                  {{
                    numberWithCommas(i.price - (i.price * i.discountPer) / 100)
                  }}
                  ₩
                </span>
              </li>
            </ul>
            <h2 class="text-center total price">
              {{ numberWithCommas(totalPrice) }} ₩
            </h2>
          </div>
          <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">주문 정보</h4>
            <div class="needs-validation" novalidate="">
              <div class="row g-3">
                <div class="col-12">
                  <label for="username" class="form-label">이름</label>
                  <div class="input-group has-validation">
                    <input
                      type="text"
                      class="form-control"
                      id="username"
                      placeholder="이름"
                      v-model="state.form.title"
                    />
                    <div class="invalid-feedback">이름을 입력하세요.</div>
                  </div>
                </div>
                <div class="col-12">
                  <label for="address" class="form-label">주소</label
                  ><input
                    type="text"
                    class="form-control"
                    id="address"
                    placeholder="주소"
                    v-model="state.form.address"
                  />
                  <div class="invalid-feedback">주소를 입력하세요</div>
                </div>
              </div>
              <hr class="my-4" />
              <hr class="my-4" />
              <h4 class="mb-3">결제수단</h4>
              <div class="my-3">
                <div class="form-check">
                  <input
                    id="card"
                    name="paymentMethod"
                    type="radio"
                    class="form-check-input"
                    value="card"
                    v-model="state.form.payment"
                  /><label class="form-check-label" for="credit"
                    >신용카드</label
                  >
                </div>
                <div class="form-check">
                  <input
                    id="bank"
                    name="paymentMethod"
                    type="radio"
                    class="form-check-input"
                    value="bank"
                    v-model="state.form.payment"
                  /><label class="form-check-label" for="debit"
                    >무통장입금</label
                  >
                </div>
              </div>
              <div class="row gy-3">
                <label for="cc-number" class="form-label">카드번호</label
                ><input
                  type="text"
                  class="form-control"
                  id="cc-number"
                  v-model="state.form.cardNumber"
                />
                <div class="invalid-feedback">카드번호를 입력하세요</div>
              </div>
              <hr class="my-4" />
              <button
                class="w-100 btn btn-primary btn-lg"
                @click.prevent="submit()"
              >
                결제하기
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>
<script setup>
import { reactive, computed } from "vue";
import { numberWithCommas } from "@/scripts/numberWithCommas";
import router from "@/scripts/router";
import axios_backend from "@/scripts/axios_backend";

const state = reactive({
  items: [],
  form: {
    name: "",
    address: "",
    payment: "",
    cardNumber: "",
    items: "",
  },
});
const load = () => {
  axios_backend.get("/api/cart/items").then(({ data }) => {
    console.log(data);
    state.items = data;
  });
};
const submit = () => {
  const args = JSON.parse(JSON.stringify(state.form));
  args.items = JSON.stringify(state.items);
  axios_backend.post("/api/orders", args).then(() => {
    alert("주문 완료하였습니다.");
    router.push({ path: "/orders" });
  }).catch((reason)=>{
      alert(`주문 정보란을 채우세요.`);
  });
};
const totalPrice = computed(() => {
  let result = 0;
  for (let i of state.items) {
    result += i.price - (i.price * i.discountPer) / 100;
  }
  return result;
});
load();
</script>
<style scoped></style>
