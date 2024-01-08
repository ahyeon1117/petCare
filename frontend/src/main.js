import "./assets/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "@/App.vue";
import router from "@/router/index";
import axios from "axios";

const app = createApp(App);
app.provide("$axios", axios);
app.provide("$router", router);

app.use(createPinia());
app.use(router);

app.mount("#app");
