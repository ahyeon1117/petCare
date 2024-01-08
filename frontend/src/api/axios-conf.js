import axios from "axios";

const instance = axios.create({
  baseURL: `${process.env.VUE_APP_CONTEXTPATH}`, // API의 기본 URL로 변경
  timeout: 5000, // 타임아웃 설정
  headers: {
    "Content-Type": "application/json"
  }
});

export default instance;
