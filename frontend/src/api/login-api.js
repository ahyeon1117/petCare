import axios from "axios";

class LoginService {
  kakao() {
    return axios.get(`/oauth2/authorization/kakao`).then((response) => {
      if (response.data.err) {
        throw new Error(response.data.err);
      } else {
        return response.data;
      }
    });
  }
  naver() {
    return axios.get(`/oauth2/authorization/naver`).then((response) => {
      if (response.data.err) {
        throw new Error(response.data.err);
      } else {
        return response.data;
      }
    });
  }
  pricipal(user) {
    return axios
      .post(`/api/login`, {
        id: user.loginId,
        password: user.loginPw
      })
      .then((response) => {
        if (response.data.err) {
          throw new Error(response.data.err);
        } else {
          return response.data;
        }
      });
  }
}
export default LoginService;
