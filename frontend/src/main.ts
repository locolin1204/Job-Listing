import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import Cookies from "js-cookie";
import { store } from './store';

axios.defaults.headers.post["Content-type"] = "application/json";

axios.interceptors.request.use((config) => {
    const token = Cookies.get("jwt_token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

createApp(App)
    .use(store)
    .use(router)
    .mount("#app");
