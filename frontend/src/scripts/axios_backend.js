import axios from "axios";

// url 공통화
const axios_backend = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  withCredentials:true
});

export default axios_backend;