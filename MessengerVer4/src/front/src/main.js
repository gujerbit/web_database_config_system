import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8090';

const APP = createApp(App);
APP.config.globalProperties.axios = axios;
APP.use(router).mount('#app');
