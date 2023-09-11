import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

axios.defaults.headers.post["Content-type"] = "application/json";

createApp(App)
    .use(router)
    .mount("#app");
