const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  devServer: {
    proxy: {
      "^/oauth2": {
        target: "http://127.0.0.1:1117", // 실제 API 서버의 주소
        changeOrigin: true
      }
    }
  }
});
